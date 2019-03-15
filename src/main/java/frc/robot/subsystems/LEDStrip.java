/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ShowLeds;
import edu.wpi.first.wpilibj.Timer;

/**
 * Add your docs here.
 */
public class LEDStrip extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark ledStrip;

  public double resetTimer = Timer.getFPGATimestamp();

  public double pwmCode;

  public void init(){
    ledStrip = new Spark(RobotMap.ledStrip);
    pwmCode = -0.39;
  }

  public void setPattern(double value){
    pwmCode = value;
  }

  public void setPattern(double value, Boolean time){
    pwmCode = value;
    if(time){
      resetTimer = Timer.getFPGATimestamp();
    }
  }

  public void show(){
    ledStrip.set(pwmCode);
    if(Timer.getFPGATimestamp() - resetTimer - 4 == 0){
      pwmCode = -0.39;
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ShowLeds());
  }
}
