/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class LimitSwitches extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DigitalInput hallEffect;
  DigitalInput mechanical;

  public void init()
  {
    hallEffect = new DigitalInput(0);
    //return true not pressed, about .75 inches away magnet from the sensor is the minimum
    //Plug into talon or code?
    
    mechanical = new DigitalInput(1);
  }

  public boolean getHall()
  {
    return !hallEffect.get();
  }

  public boolean getMechanical()
  {
    return mechanical.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
