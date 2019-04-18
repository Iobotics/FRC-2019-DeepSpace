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
import frc.robot.commands.Shooter.ManualShooter;

/**
 * Subsystem Handles the Shooter / Carriage
 */
public class Shooter extends Subsystem {
  TalonSRX _leftShooter;
  TalonSRX _rightShooter;
  TalonSRX _shooterArm;
  
  Solenoid _intakeOut;

  DigitalInput _proximitySensor;

  private boolean isBallIn = false;
  private boolean isReverse = false;

  private int slotID = 0;
  private double kFFEmpty = 0;
  private double kFFBall = 0; //Positive feedforward when going down
  private double kFFEmptyReverse = .1;
  private double kFFBallReverse = .1;
  private double kP = 10; // Before 8
  private double kI = 0;
  private double kD = 350; // Before 350

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualShooter());
  }

  public void init()
  {
    _leftShooter = new TalonSRX(RobotMap.leftShooter);
    _leftShooter.configFactoryDefault();
    _leftShooter.setInverted(true);
    _leftShooter.setNeutralMode(NeutralMode.Brake);

    _rightShooter = new TalonSRX(RobotMap.rightShooter);
    _rightShooter.setInverted(false);
    _rightShooter.configFactoryDefault();
    _rightShooter.setNeutralMode(NeutralMode.Brake);

    _shooterArm = new TalonSRX(RobotMap.shooterArm);
    _shooterArm.configFactoryDefault();

    _shooterArm.enableCurrentLimit(true);
    _shooterArm.configContinuousCurrentLimit(40);
    _shooterArm.setInverted(true);
    _shooterArm.setSensorPhase(true);
    _shooterArm.setNeutralMode(NeutralMode.Brake);
    _shooterArm.configSelectedFeedbackSensor(FeedbackDevice.Analog, slotID, 20);
    _shooterArm.configFeedbackNotContinuous(true, 20);
    _shooterArm.selectProfileSlot(slotID, 0);
    _shooterArm.config_kP(slotID, kP);
    _shooterArm.config_kI(slotID, kI);
    _shooterArm.config_kD(slotID, kD);

    _proximitySensor = new DigitalInput(RobotMap.proximitySensor);
  }

  public void setShooter(double speed){
    _rightShooter.set(ControlMode.PercentOutput, speed);
    _leftShooter.set(ControlMode.PercentOutput, speed);
  }

  public void setShooterArm(double power){
    _shooterArm.set(ControlMode.PercentOutput, power);
  }

  //returns the angle of the arm in radians from the horizontal
  private double getArmAngle(double position){
    return (Math.PI / 180)*(double)(((position - Constants.shooterArmCenter)*9)/7);
  }

  public void setShooterPosition(double position){
    if(this.getArm() - position < 0) //Going up
    {
      isReverse = false;
    }
    else
    {
      isReverse = true;
    }

    if(isBallIn && !isReverse){
      _shooterArm.set(ControlMode.Position, position, 
      DemandType.ArbitraryFeedForward, kFFBall * Math.cos(getArmAngle(_shooterArm.getSelectedSensorPosition())));
      //Gives and Feed Forward based on the CoSine of the angle of the arm with the horizontal
    }
    else if(!isReverse) {
      _shooterArm.set(ControlMode.Position, position, 
      DemandType.ArbitraryFeedForward, kFFEmpty * Math.cos(getArmAngle(_shooterArm.getSelectedSensorPosition())));
      //Gives and Feed Forward based on the CoSine of the angle of the arm with the horizontal
    }

    if(isBallIn && isReverse){
      _shooterArm.set(ControlMode.Position, position, 
      DemandType.ArbitraryFeedForward, kFFBallReverse * Math.cos(getArmAngle(_shooterArm.getSelectedSensorPosition())));
      //Gives and Feed Forward based on the CoSine of the angle of the arm with the horizontal
    }
    else if(isReverse) {
      _shooterArm.set(ControlMode.Position, position, 
      DemandType.ArbitraryFeedForward, kFFEmptyReverse * Math.cos(getArmAngle(_shooterArm.getSelectedSensorPosition())));
      //Gives and Feed Forward based on the CoSine of the angle of the arm with the horizontal
    }
    
  }
 
  public boolean getBallSensor(){
    return !_proximitySensor.get();
  }

  public boolean getIsBallIn(){
    return isBallIn;
  }

  public void setIsBallIn(boolean isBallIn){
    this.isBallIn = isBallIn;
  }

  public void toggleSolenoid(){
    _intakeOut.set(!_intakeOut.get());
  }

  public int getArm(){
    return _shooterArm.getSelectedSensorPosition();
  }

}
