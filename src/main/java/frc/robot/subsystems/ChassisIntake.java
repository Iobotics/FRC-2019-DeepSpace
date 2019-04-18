/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.Intake.ManualOperateIntake;

/**
 * Chassis intake Written by Darren Kohara The subsystem uses talon motor sto
 * run a horizontal intake parallel to the ground. The subsystem also extends by
 * pneumatic cyinders controlled by a single activation solenoid.
 */
public class ChassisIntake extends PIDSubsystem {

  /**
   *
   */

  
  private final int idPosition = 0;
  private final double kPPosition = 15;
  private final double kIPosition = 0.005; 
  private final double kDPosition = 120;

  private final int idVelocity = 1;
  private final double kFVelocity = 1024.0/(410.0) * 10;
  private final double kPVelocity = 60; // Before 40 //To counter against load // 15 velocity about 10
  private final double kIVelocity = 0;
  private final double kDVelocity = 0;
  private final double TONATIVEUNITS = (102.4*3)/(2*Math.PI*1000);
  private final int FORWARDLIMIT = -227; // more positive is forward
  private final int REVERSELIMIT = Constants.intakeArmHome + 2;



  private TalonSRX _chassisIntakeLeft;
  private TalonSRX _chassisIntakeRight;
  private TalonSRX _leftArm;
  private TalonSRX _rightArm;
  
  public ChassisIntake(){
    super(0,0,0);
  }

  //Should be called in the robot init
  public void init(){

    _chassisIntakeLeft = new TalonSRX(RobotMap.chassisIntakeLeft);
    _chassisIntakeLeft.setInverted(false);
    _chassisIntakeLeft.setNeutralMode(NeutralMode.Brake);

    _chassisIntakeRight = new TalonSRX(RobotMap.chassisIntakeRight);
    _chassisIntakeRight.setInverted(true);
    _chassisIntakeRight.setNeutralMode(NeutralMode.Brake);
    
    _leftArm = new TalonSRX(RobotMap.leftIntakeArm);
    _leftArm.configFactoryDefault();
    _leftArm.config_kP(idPosition, kPPosition);
    _leftArm.config_kI(idPosition, kIPosition);
    _leftArm.config_kD(idPosition, kDPosition);
    
    _leftArm.config_kF(idVelocity, kFVelocity);
    _leftArm.config_kP(idVelocity, kPVelocity);
    //_leftArm.config_kI(idVelocity, kIVelocity);
    //_leftArm.config_kD(idVelocity, kDVelocity);
    _leftArm.setNeutralMode(NeutralMode.Brake);
    _leftArm.setInverted(true);
    _leftArm.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    _leftArm.configFeedbackNotContinuous(true, 20);
    
    _rightArm = new TalonSRX(RobotMap.rightIntakeArm);
    _rightArm.configFactoryDefault();
    _rightArm.setInverted(false);
    _rightArm.setNeutralMode(NeutralMode.Brake);
    
    //_leftArm.set(ControlMode.Position, 198);
    //_rightArm.set(ControlMode.Follower, RobotMap.leftIntakeArm);

    _leftArm.configForwardSoftLimitThreshold(Constants.intakeArmForwardLimit); 
    _leftArm.configForwardSoftLimitEnable(true);
    _leftArm.configReverseSoftLimitThreshold(Constants.intakeArmReverseLimit);
    _leftArm.configReverseSoftLimitEnable(true);

  }

  //Set intake motor power to a percentage between -1 and 1
  public void setPower(double power){
    _chassisIntakeLeft.set(ControlMode.PercentOutput, power);
    _chassisIntakeRight.set(ControlMode.Follower, RobotMap.chassisIntakeLeft);
  }

  public double getPower()
  {
    return _chassisIntakeLeft.getMotorOutputPercent();
  }

  public void setIntakeArm(double power){
    _leftArm.set(ControlMode.PercentOutput, power);
    _rightArm.set(ControlMode.Follower, RobotMap.leftIntakeArm);
  }
  
  public void setArmPosition(double position){
    _leftArm.selectProfileSlot(idPosition, 0);
    _leftArm.set(ControlMode.Position, position);
    _rightArm.set(ControlMode.Follower, RobotMap.leftIntakeArm);
  }

  public void setArmVelocity(double velocity)
  {
    _leftArm.selectProfileSlot(idVelocity, 0);
    //_leftArm.set(ControlMode.Velocity, velocity*TONATIVEUNITS); // From radians per second
    _leftArm.set(ControlMode.Velocity, velocity); 
    _rightArm.set(ControlMode.Follower, RobotMap.leftIntakeArm);
  }

  public double getArmVelocity()
  {
    return _leftArm.getSelectedSensorVelocity();
  }

  public double getArmPosition(){
    return _leftArm.getSelectedSensorPosition();
  }
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualOperateIntake());
  }

  @Override
  protected double returnPIDInput() {
    return _leftArm.getSelectedSensorPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    _rightArm.set(ControlMode.PercentOutput, output);
    _leftArm.set(ControlMode.PercentOutput, output);
  }
  
}
