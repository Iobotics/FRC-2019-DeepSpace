/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.CameraServo;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchCollector;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.NavSensor;

public abstract class CommandBase extends Command {

  public static OI oi;
  public static Drivetrain drivetrain = new Drivetrain();
  public static NavSensor navSensor = new NavSensor();
  public static HatchCollector hatchCollector = new HatchCollector();
  public static Intake intake = new Intake();
  public static CameraServo rotater = new CameraServo();

  public static void init() {
    navSensor.init();
    drivetrain.init();
    hatchCollector.init();
    intake.init();
    rotater.init();
    oi = new OI();
  }

  public CommandBase(String name) {
    super(name);
  }

  public CommandBase() {
    super();
  }
  
}
