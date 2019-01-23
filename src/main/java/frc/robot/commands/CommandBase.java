/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.Drivetrain;
//import frc.robot.subsystems.NavSensor;
import frc.robot.subsystems.Zone3Lift;

public abstract class CommandBase extends Command {

  public static OI oi;

  public static final Drivetrain drivetrain = new Drivetrain();
  //public static final NavSensor navSensor = new NavSensor();
  public static final Zone3Lift zone3lift = new Zone3Lift();

  public static void init(){
    oi = new OI();

    drivetrain.init();
    //navSensor.init();
    zone3lift.init();
  }

  public CommandBase(String name) {
    super(name);
  }

  public CommandBase() {
    super();
  }
  
}
