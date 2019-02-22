/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

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
  private DoubleSolenoid midWheel;
  private DoubleSolenoid backWheel;

  public void init() {
    midWheel = new DoubleSolenoid(RobotMap.zoneTwoFrontForward, RobotMap.zoneTwoFrontReverse);
    backWheel = new DoubleSolenoid(RobotMap.zoneTwoBackForward, RobotMap.zoneTwoBackReverse);

    midWheel.set(DoubleSolenoid.Value.kReverse);
    backWheel.set(DoubleSolenoid.Value.kReverse);
  }

  public void deployMidWheels(){
    midWheel.set(DoubleSolenoid.Value.kForward);
  }

  public void deployBackWheels(){
    backWheel.set(DoubleSolenoid.Value.kForward);
  }

  public void retractMidWheels(){
    midWheel.set(DoubleSolenoid.Value.kReverse);
  }

  public void retractBackWheels(){
    backWheel.set(DoubleSolenoid.Value.kReverse);
  }

  public boolean frontWheelDown(){
    return midWheel.get() == Value.kForward;
  }

  public boolean backWheelDown(){
    return backWheel.get() == Value.kForward;
  }

  @Override
  public void initDefaultCommand() {

  }
}
