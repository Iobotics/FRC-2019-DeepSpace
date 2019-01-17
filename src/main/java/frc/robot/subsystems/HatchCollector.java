/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchCollector extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Solenoid _hook;
  private DoubleSolenoid _extenderOne;
  private DoubleSolenoid _extenderTwo;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void init() {
    //_hook = new Solenoid(RobotMap.hookSolenoid);
    //_extenderOne = new DoubleSolenoid(RobotMap.extendOneSolenoidForward, RobotMap.extendOneSolenoidReverse);
    _extenderTwo = new DoubleSolenoid(RobotMap.extendTwoSolenoidForward, RobotMap.extendTwoSolenoidReverse);
    
    
  }
  
  public void openHook() {
    _hook.set(true);
  }

  public void closeHook() {
    _hook.set(false);
  }

  public void extend() {
    _extenderOne.set(Value.kForward);
  }

  public void pop() {
    _extenderTwo.set(Value.kForward);
  }

  public void retract() {
    _extenderOne.set(Value.kReverse);
    _extenderTwo.set(Value.kReverse);
  }
}
