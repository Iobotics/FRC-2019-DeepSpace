/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class LevelTwoLift extends Subsystem {
  private DoubleSolenoid midWheel;
  private DoubleSolenoid backWheel;

  public void init() {
    midWheel = new DoubleSolenoid(1, 2);
    backWheel = new DoubleSolenoid(3,4);

    midWheel.set(DoubleSolenoid.Value.kOff);
    backWheel.set(DoubleSolenoid.Value.kOff);
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

  @Override
  public void initDefaultCommand() {

  }
}
