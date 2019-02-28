/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
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
  public Solenoid _extender;
  
  private int slotID = 0;
  private double kFF =  0;
  private double kP = 7;
  private double kI = 0.0;
  private double kD = 300;

  //Should be called in the robot init
  public void init(){

    _chassisIntake = new TalonSRX(RobotMap.chassisIntake);
    _chassisIntake.setNeutralMode(NeutralMode.Brake);
    
    _rightArm = new TalonSRX(RobotMap.rightIntakeArm);
    _rightArm.setNeutralMode(NeutralMode.Brake);

    _leftArm = new TalonSRX(RobotMap.leftIntakeArm);
    _leftArm.setNeutralMode(NeutralMode.Brake);
    _leftArm.setInverted(true);
    _leftArm.follow(_rightArm);

    _extender = new Solenoid(RobotMap.intakeExtender);
    
  }

  //Set intake motor power to a percentage between -1 and 1
  public void setPower(double power){
    _chassisIntake.set(ControlMode.PercentOutput, power);
  }

  //Extends the intake cylinder  
  public void extendIntake(){
    _extender.set(true);
  }
  
  //Retracts the intake cylinder
  public void retractIntake(){
    _extender.set(false);
  }

  //Toggles the intake cylinder
  public void toggleIntake(){
    if(_extender.get()){
      _extender.set(false);
    }
    else _extender.set(true);
  }
  
  @Override
  public void initDefaultCommand() {
   
  }
}
