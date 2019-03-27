/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Level Two Elevator
 * Written by Victus Chang
 * The elevator system uses two solenoids to push the bot off the ground.
 */
public class LevelTwoLift extends Subsystem {
  
  private DoubleSolenoid backWheel;

  private Compressor compressor;

  // Should be called in the robot init
  public void init() {
    backWheel = new DoubleSolenoid(RobotMap.zoneTwoBackForward, RobotMap.zoneTwoBackReverse);
    backWheel.set(DoubleSolenoid.Value.kForward);
    compressor = new Compressor();
    compressor.start();
  }

  // extends the cylinders on the back of the robot
  public void deployBackWheels(){
    backWheel.set(DoubleSolenoid.Value.kReverse);
  }

  // retracts the cylinders on the back of the robot
  public void retractBackWheels(){
    backWheel.set(DoubleSolenoid.Value.kForward);
  }

  // returns whether or not the back wheel is extended
  public boolean backWheelDown(){
    return backWheel.get() == Value.kReverse; // before forward
  }

  public void disableCompressor(){
    compressor.stop();
  }

  @Override
  public void initDefaultCommand() {

  }
}
