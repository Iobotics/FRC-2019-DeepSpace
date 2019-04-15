/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.LimelightAutoTurn;

public class StopShooterAndRotateLimelight extends CommandGroup {
  /**
   * Add your docs here.
   */
  public StopShooterAndRotateLimelight() {
    addSequential(new StopShooter());
    addSequential(new LimelightAutoTurn());
  }
}
