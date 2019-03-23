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
import frc.robot.Constants;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import frc.robot.RobotMap;
import frc.robot.commands.Intake.ManualOperateIntake;
import frc.robot.commands.Intake.SetIntakeVelocity;

/**
 * Chassis intake
 * Written by Darren Kohara
 * The subsystem uses talon motor sto run a horizontal intake parallel to the ground.
 * The subsystem also extends by pneumatic cyinders controlled by a single activation solenoid.
 */
public class ChassisIntake extends PIDSubsystem {
  
  /**
   *
   */

  
  private static final int idPosition = 0;
  private static final double kPPosition = 15;
  private static final double kIPosition = 0.005; 
  private static final double kDPosition = 120;

  private static final int idVelocity = 1;
  private static final double kFVelocity = 1024.0/(410.0);
  private static final double kPVelocity = 20; //To counter against load
  private static final double kIVelocity = 0;
  private static final double kDVelocity = 0;


  private TalonSRX _chassisIntake;
  private TalonSRX _leftArm;
  private TalonSRX _rightArm;


  
  public ChassisIntake(){
    super(kPPosition,0,0);
  }

  //Should be called in the robot init
  public void init(){

    _chassisIntake = new TalonSRX(RobotMap.chassisIntake);
    _chassisIntake.setInverted(false);
    _chassisIntake.setNeutralMode(NeutralMode.Brake);
    
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

    _leftArm.configForwardSoftLimitEnable(true);
    _leftArm.configForwardSoftLimitThreshold(-502); 
    _leftArm.configReverseSoftLimitThreshold(-650);
  }

  //Set intake motor power to a percentage between -1 and 1
  public void setPower(double power){
    _chassisIntake.set(ControlMode.PercentOutput, power);
  }

  public void setIntakeArm(double power){
    _rightArm.set(ControlMode.PercentOutput, power);
    _leftArm.set(ControlMode.PercentOutput, power);
  }
  
  public void setArmPosition(double position){
    _leftArm.selectProfileSlot(idPosition, 0);
    _leftArm.set(ControlMode.Position, position);
    _rightArm.set(ControlMode.Follower, RobotMap.leftIntakeArm);
  }

  public void setArmVelocity(double velocity)
  {
    _leftArm.selectProfileSlot(idVelocity, 0);
    //_leftArm.set(ControlMode.Velocity, velocity*((103*4)/1000)); // To rotations per second
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
