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

  private DoubleSolenoid hook;
  private Solenoid extender;
  private DigitalInput hatchDetector;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }

  public void init() {
    hook = new DoubleSolenoid(RobotMap.hookSolenoidForward, RobotMap.hookSolenoidReverse);
    extender = new Solenoid(RobotMap.extendHatch);
    hatchDetector = new DigitalInput(1);

    extender.set(false);
    hook.set(Value.kForward);
  }

  public boolean getHatchSensor(){
    return hatchDetector.get();
  }
  
  public void openHook() {
    hook.set(Value.kForward);
  }

  public void closeHook() {
    hook.set(Value.kReverse);
  }

  public void toggleHook(){
    if(hook.get() == Value.kReverse){
      hook.set(Value.kForward);
    }
    else hook.set(Value.kReverse);
  }

  public void toggleExtension() {
    if(extender.get()){
      extender.set(false);
    }
    else extender.set(true);
  }
  
  public void extendHatch() {
    extender.set(true);
  }

  public void retractHatch(){
    extender.set(false);
  }
}
