/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Shooter.SetShooterPos;
import frc.robot.commands.Shooter.RunShooter;
import frc.robot.commands.Intake.RunChassisIntake;

public class RunCargoCarriage extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RunCargoCarriage() {

    addParallel(new RunChassisIntake());
    addParallel(new RunShooter(0.5));
    addSequential(new SetShooterPos(780));
  }
}
