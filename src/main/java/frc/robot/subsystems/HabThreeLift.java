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
public class HabThreeLift extends Subsystem {
  
  private DoubleSolenoid _backWheel;

  private Compressor _compressor;

  // Should be called in the robot init
  public void init() {
    _backWheel = new DoubleSolenoid(RobotMap.zoneTwoBackForward, RobotMap.zoneTwoBackReverse);
    _backWheel.set(DoubleSolenoid.Value.kForward);
    _compressor = new Compressor();
    _compressor.start();
  }

  // extends the cylinders on the back of the robot
  public void deployBackWheels(){
    _backWheel.set(DoubleSolenoid.Value.kReverse);
  }

  // retracts the cylinders on the back of the robot
  public void retractBackWheels(){
    _backWheel.set(DoubleSolenoid.Value.kForward);
  }

  // returns whether or not the back wheel is extended
  public boolean backWheelDown(){
    return _backWheel.get() == Value.kReverse; // before forward
  }

  public void disableCompressor(){
    _compressor.stop();
  }

  public void startCompressor(){
    _compressor.start();
  }

  @Override
  public void initDefaultCommand() {

  }
}
