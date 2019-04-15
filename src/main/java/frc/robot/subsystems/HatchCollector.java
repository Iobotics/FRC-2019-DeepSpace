 /*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchCollector extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private DoubleSolenoid _hook;
  private Solenoid _extender;
  private DigitalInput _hatchDetector;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }

  public void init() {
    _hook = new DoubleSolenoid(RobotMap.hookSolenoidForward, RobotMap.hookSolenoidReverse);
    _extender = new Solenoid(RobotMap.extendHatch);
    _hatchDetector = new DigitalInput(1);

    _extender.set(false);
    _hook.set(Value.kForward);
  }

  public boolean getHatchSensor(){
    return _hatchDetector.get();
  }
  
  public void openHook() {
    _hook.set(Value.kForward);
  }

  public void closeHook() {
    _hook.set(Value.kReverse);
  }

  public void toggleHook(){
    if(_hook.get() == Value.kReverse){
      _hook.set(Value.kForward);
    }
    else _hook.set(Value.kReverse);
  }
  public boolean getExtended(){
    return _hook.get() == Value.kReverse;
  }

  public void toggleExtension() {
    if(_extender.get()){
      _extender.set(false);
    }
    else _extender.set(true);
  }
  
  public void extendHatch() {
    _extender.set(true);
  }

  public void retractHatch(){
    _extender.set(false);
  }
}
