/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.GetDistance;

/**
 * Add your docs here.
 */
public class DistanceSensor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

   AnalogInput distanceSensor;
   private static final double VOLTS_PER_INCH = 5.0/512.0;

  public void init()
  {
    distanceSensor = new AnalogInput(0);
    
  }

  public double getDistance() {
    return distanceSensor.getVoltage()/VOLTS_PER_INCH;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new GetDistance());
  }
}
