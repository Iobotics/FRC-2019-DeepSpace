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

  private static final double kP = 10;
  private TalonSRX _chassisIntake;
  private TalonSRX _leftArm;
  private TalonSRX _rightArm;


  
  public ChassisIntake(){
    super(kP,0,0);
  }

  //Should be called in the robot init
  public void init(){

    _chassisIntake = new TalonSRX(RobotMap.chassisIntake);
    _chassisIntake.setInverted(false);
    _chassisIntake.setNeutralMode(NeutralMode.Brake);
    
    _leftArm = new TalonSRX(RobotMap.leftIntakeArm);
    _leftArm.configFactoryDefault();
    _leftArm.config_kP(0, kP);
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
    _leftArm.set(ControlMode.Position, position);
    _rightArm.set(ControlMode.Follower, RobotMap.leftIntakeArm);
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
