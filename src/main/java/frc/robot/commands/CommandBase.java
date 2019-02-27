/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.DrivetrainChair;
import frc.robot.subsystems.LimeLight;

public abstract class CommandBase extends Command {

  public static OI oi;
  public static LimeLight limelight = new LimeLight();
  public static DrivetrainChair chair = new DrivetrainChair();

  public static void init() {
    limelight.init();
    chair.init();
    
    oi = new OI();
  }

  public CommandBase(String name) {
    super(name);
  }

  public CommandBase() {
    super();
  }
  
}