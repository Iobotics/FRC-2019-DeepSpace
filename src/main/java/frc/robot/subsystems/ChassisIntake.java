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
import frc.robot.RobotMap;
import frc.robot.commands.Intake.ManualOperateIntake;

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


  private TalonSRX chassisIntake;
  private TalonSRX leftArm;
  private TalonSRX rightArm;


  
  public ChassisIntake(){
    super(kPPosition,0,0);
  }

  //Should be called in the robot init
  public void init(){

    chassisIntake = new TalonSRX(RobotMap.chassisIntake);
    chassisIntake.setInverted(false);
    chassisIntake.setNeutralMode(NeutralMode.Brake);
    
    leftArm = new TalonSRX(RobotMap.leftIntakeArm);
    leftArm.configFactoryDefault();
    leftArm.config_kP(idPosition, kPPosition);
    leftArm.config_kI(idPosition, kIPosition);
    leftArm.config_kD(idPosition, kDPosition);
    
    leftArm.config_kF(idVelocity, kFVelocity);
    leftArm.config_kP(idVelocity, kPVelocity);
    leftArm.config_kI(idVelocity, kIVelocity);
    leftArm.config_kD(idVelocity, kDVelocity);
    
    leftArm.setNeutralMode(NeutralMode.Brake);
    leftArm.setInverted(true);
    leftArm.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    leftArm.configFeedbackNotContinuous(true, 20);
    
    rightArm = new TalonSRX(RobotMap.rightIntakeArm);
    rightArm.configFactoryDefault();
    rightArm.setInverted(false);
    rightArm.setNeutralMode(NeutralMode.Brake);
    //rightArm.follow(leftArm, FollowerType.PercentOutput);
    
    //leftArm.set(ControlMode.Position, 198);
    //rightArm.set(ControlMode.Follower, RobotMap.leftIntakeArm);

    leftArm.configForwardSoftLimitThreshold(-502); // -650
    leftArm.configForwardSoftLimitEnable(true);
    leftArm.configReverseSoftLimitThreshold(-650); // -502
    leftArm.configReverseSoftLimitEnable(true);

  }

  //Set intake motor power to a percentage between -1 and 1
  public void setPower(double power){
    chassisIntake.set(ControlMode.PercentOutput, power);
  }

  public void setIntakeArm(double power){
    //rightArm.set(ControlMode.PercentOutput, power);
    leftArm.set(ControlMode.PercentOutput, power);
    rightArm.set(ControlMode.Follower, RobotMap.leftIntakeArm);
  }
  
  public void setArmPosition(double position){
    leftArm.selectProfileSlot(idPosition, 0);
    leftArm.set(ControlMode.Position, position);
    rightArm.set(ControlMode.Follower, RobotMap.leftIntakeArm);
  }

  public void setArmVelocity(double velocity)
  {
    leftArm.selectProfileSlot(idVelocity, 0);
    //leftArm.set(ControlMode.Velocity, velocity*((103*4)/1000)); // To rotations per second
    leftArm.set(ControlMode.Velocity, velocity);
    rightArm.set(ControlMode.Follower, RobotMap.leftIntakeArm);
  }

  public double getArmVelocity()
  {
    return leftArm.getSelectedSensorVelocity();
  }

  public double getArmPosition(){
    return leftArm.getSelectedSensorPosition();
  }
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualOperateIntake());
  }

  @Override
  protected double returnPIDInput() {
    return leftArm.getSelectedSensorPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    rightArm.set(ControlMode.PercentOutput, output);
    leftArm.set(ControlMode.PercentOutput, output);
  }
  
}
