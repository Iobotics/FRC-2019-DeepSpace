/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.ChassisIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchCollector;
import frc.robot.subsystems.LevelTwoLift;
import frc.robot.subsystems.NavSensor;
import frc.robot.subsystems.Shooter;

public abstract class CommandBase extends Command {

  public static OI oi;
  public static Drivetrain drivetrain = new Drivetrain();
  public static NavSensor navSensor = new NavSensor();
  public static HatchCollector hatchCollector = new HatchCollector();
  public static Shooter shooter = new Shooter();
  public static LevelTwoLift levelTwo = new LevelTwoLift();
  public static ChassisIntake chassisIntake = new ChassisIntake();

  public static void init() {
    navSensor.init();
    drivetrain.init();
    hatchCollector.init();
    shooter.init();
    levelTwo.init();
    chassisIntake.init();
    oi = new OI();
  }

  public CommandBase(String name) {
    super(name);
  }

  public CommandBase() {
    super();
  }
  
}
