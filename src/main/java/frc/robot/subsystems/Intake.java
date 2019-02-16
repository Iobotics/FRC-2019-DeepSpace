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

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalGlitchFilter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Subsystem Handles the Shooter, Intake, and Hatch
 */
public class Intake extends Subsystem {
  TalonSRX leftShooter;
  TalonSRX rightShooter;
  TalonSRX intake;
  TalonSRX shooterArm;
  
  Solenoid intakeOut;

  DigitalInput proximitySensor;

  private int slotID = 0;
  private double kFF =  0.25;
  private double kP = 10;
  private double kI = 0;
  private double kD = 0;

  @Override
  public void initDefaultCommand() {

  }

  public void init()
  {
    leftShooter = new TalonSRX(RobotMap.leftShooter);
    leftShooter.setInverted(true);
    leftShooter.setNeutralMode(NeutralMode.Brake);

    rightShooter = new TalonSRX(RobotMap.rightShooter);
    rightShooter.setInverted(false);
    rightShooter.setNeutralMode(NeutralMode.Brake);

    intake = new TalonSRX(RobotMap.intake);

    shooterArm = new TalonSRX(RobotMap.shooterArm);
    shooterArm.configFactoryDefault();

    shooterArm.setInverted(true);
    shooterArm.setNeutralMode(NeutralMode.Brake);
    shooterArm.configSelectedFeedbackSensor(FeedbackDevice.Analog, slotID, 20);
    shooterArm.configFeedbackNotContinuous(true, 20);
    shooterArm.setSensorPhase(true);
    shooterArm.selectProfileSlot(slotID, 0);
    shooterArm.config_kP(slotID, kP);
    shooterArm.config_kI(slotID, kI);
    shooterArm.config_kD(slotID, kD);

    
    intakeOut = new Solenoid(RobotMap.intakeSolenoid);
    intakeOut.set(false);

    proximitySensor = new DigitalInput(RobotMap.proximitySensor);
  }

  public void setIntake(double speed){
    intake.set(ControlMode.PercentOutput, speed);
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
    shooterArm.set(ControlMode.Position, position, 
    DemandType.ArbitraryFeedForward, kFF * Math.cos(getArmAngle(shooterArm.getSelectedSensorPosition())));
    //Gives and Feed Forward based on the CoSine of the angle of the arm with the horizontal
  }
 
  public boolean isBallIn(){
    return !proximitySensor.get();
  }

  public void toggleSolenoid(){
    intakeOut.set(!intakeOut.get());
  }

  public int getArm(){
    return shooterArm.getSelectedSensorPosition();
  }

}
