/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.CameraServo;
import frc.robot.subsystems.ChassisIntake;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HabThreeLift;
import frc.robot.subsystems.HatchCollector;
import frc.robot.subsystems.LEDStrip;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.LimelightServo;
import frc.robot.subsystems.NavSensor;
import frc.robot.subsystems.Shooter;

public abstract class CommandBase extends Command {

  public static OI oi;
  public static HatchCollector hatchCollector = new HatchCollector();
  public static Drivetrain drivetrain = new Drivetrain();
  public static NavSensor navSensor = new NavSensor();
  //public static Lift lift = new Lift();
  public static Shooter shooter = new Shooter();
  public static HabThreeLift habThree = new HabThreeLift();
  public static LimeLight limelight = new LimeLight();
  public static ChassisIntake chassisIntake = new ChassisIntake();
  public static LEDStrip ledStrip = new LEDStrip();
  public static LimelightServo limelightservo = new LimelightServo();
  public static CameraServo cameraservo = new CameraServo();

  public static void init() {
    navSensor.init();
    hatchCollector.init();
    drivetrain.init();
    //lift.init();
    ledStrip.init();
    shooter.init();
    habThree.init();
    limelight.init();
    chassisIntake.init();
    limelightservo.init();
    
    oi = new OI();
  }

  public CommandBase(String name) {
    super(name);
  }

  public CommandBase() {
    super();
  }
  
}
