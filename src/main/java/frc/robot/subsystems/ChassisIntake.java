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

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Chassis intake
 * Written by Darren Kohara
 * The subsystem uses talon motor sto run a horizontal intake parallel to the ground.
 * The subsystem also extends by pneumatic cyinders controlled by a single activation solenoid.
 */
public class ChassisIntake extends Subsystem {
  
  private TalonSRX _chassisIntake;
  private TalonSRX _leftArm;
  private TalonSRX _rightArm;
  
  //TODO: Find correct PID values
  private int slotID = 0;
  private double kFF =  0;
  private double kP = .1;
  private double kI = 0.0;
  private double kD = 300;

  //Should be called in the robot init
  public void init(){

    _chassisIntake = new TalonSRX(RobotMap.chassisIntake);
    _chassisIntake.setNeutralMode(NeutralMode.Brake);
    
    _rightArm = new TalonSRX(RobotMap.rightIntakeArm);
    _rightArm.setNeutralMode(NeutralMode.Brake);
    _rightArm.configSelectedFeedbackSensor(FeedbackDevice.Analog, slotID, 20);
    _rightArm.configFeedbackNotContinuous(true, 20);
    _rightArm.setSensorPhase(true);
    _rightArm.selectProfileSlot(slotID, 0);
    _rightArm.config_kP(slotID, kP);
    _rightArm.config_kI(slotID, kI);
    _rightArm.config_kD(slotID, kD);

    _leftArm = new TalonSRX(RobotMap.leftIntakeArm);
    _leftArm.setNeutralMode(NeutralMode.Brake);
    _leftArm.setInverted(true);
    _leftArm.follow(_rightArm);

  }

  //Set intake motor power to a percentage between -1 and 1
  public void setPower(double power){
    _chassisIntake.set(ControlMode.PercentOutput, power);
  }

  public void setIntakeArm(double power){
    _rightArm.set(ControlMode.PercentOutput, power);
  }
  
  //returns the angle of the arm in radians from the horizontal
  private double getArmAngle(double position){
    return (Math.PI / 180)*(double)(((position - Constants.shooterArmCenter)*9)/7);
  }

  public void setArmPosition(double position){

    _rightArm.set(ControlMode.Position, position, 
    DemandType.ArbitraryFeedForward, kFF * Math.cos(getArmAngle(_rightArm.getSelectedSensorPosition())));
    //Gives and Feed Forward based on the CoSine of the angle of the arm with the horizontal
  
  }
  
  @Override
  public void initDefaultCommand() {
   
  }
}
