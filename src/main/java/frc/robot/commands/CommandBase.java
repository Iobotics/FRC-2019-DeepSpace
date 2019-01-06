/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public abstract class CommandBase extends Command {

  public static OI oi;
  public static Drivetrain drivetrain = new Drivetrain();

  public static void init(){

    drivetrain.init();
    oi = new OI();
  }

  public CommandBase(String name) {
    super(name);
  }

  public CommandBase() {
    super();
  }
}
