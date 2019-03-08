/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Subsystem Handles the Shooter / Carriage
 */
public class Shooter extends Subsystem {
  TalonSRX leftShooter;
  TalonSRX rightShooter;
  TalonSRX shooterArm;
  
  Solenoid intakeOut;

  DigitalInput proximitySensor;

  private boolean isBallIn = false;

  private int slotID = 0;
  private double kFFEmpty =  0.25;
  private double kFFBall = 0.35;
  private double kP = 8;
  private double kI = 0;
  private double kD = 400;

  @Override
  public void initDefaultCommand() {
  }

  public void init()
  {
    leftShooter = new TalonSRX(RobotMap.leftShooter);
    leftShooter.configFactoryDefault();
    leftShooter.setInverted(true);
    leftShooter.setNeutralMode(NeutralMode.Brake);

    rightShooter = new TalonSRX(RobotMap.rightShooter);
    rightShooter.setInverted(false);
    rightShooter.configFactoryDefault();
    rightShooter.setNeutralMode(NeutralMode.Brake);

    shooterArm = new TalonSRX(RobotMap.shooterArm);
    shooterArm.configFactoryDefault();

    shooterArm.enableCurrentLimit(true);
    shooterArm.configContinuousCurrentLimit(30);

    //Shooter Arm Requires PID to stay up 
    //TODO: find correct values for PID and FF for both the empty carriage, and the one with the ball
    shooterArm.setInverted(true);
    shooterArm.setNeutralMode(NeutralMode.Brake);
    shooterArm.configSelectedFeedbackSensor(FeedbackDevice.Analog, slotID, 20);
    shooterArm.configFeedbackNotContinuous(true, 20);
    shooterArm.setSensorPhase(true);
    shooterArm.selectProfileSlot(slotID, 0);
    shooterArm.config_kP(slotID, kP);
    shooterArm.config_kI(slotID, kI);
    shooterArm.config_kD(slotID, kD);

    proximitySensor = new DigitalInput(RobotMap.proximitySensor);
  }

  public void setShooter(double speed){
    rightShooter.set(ControlMode.PercentOutput, speed);
    leftShooter.set(ControlMode.PercentOutput, speed);
  }

  public void setShooterArm(double power){
    shooterArm.set(ControlMode.PercentOutput, power);
  }

  //returns the angle of the arm in radians from the horizontal
  private double getArmAngle(double position){
    return (Math.PI / 180)*(double)(((position - Constants.shooterArmCenter)*9)/7);
  }

  public void setShooterPosition(double position){
    if(isBallIn){
      shooterArm.set(ControlMode.Position, position, 
      DemandType.ArbitraryFeedForward, kFFBall * Math.cos(getArmAngle(shooterArm.getSelectedSensorPosition())));
      //Gives and Feed Forward based on the CoSine of the angle of the arm with the horizontal
    }
    else {
      shooterArm.set(ControlMode.Position, position, 
      DemandType.ArbitraryFeedForward, kFFEmpty * Math.cos(getArmAngle(shooterArm.getSelectedSensorPosition())));
      //Gives and Feed Forward based on the CoSine of the angle of the arm with the horizontal
    }
    
  }
 
  public boolean isBallIn(){
    return !proximitySensor.get();
  }

  public boolean getIsBallIn(){
    return isBallIn;
  }

  public void setIsBallIn(boolean isBallIn){
    this.isBallIn = isBallIn;
  }

  public void toggleSolenoid(){
    intakeOut.set(!intakeOut.get());
  }

  public int getArm(){
    return shooterArm.getSelectedSensorPosition();
  }

}
