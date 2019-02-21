/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.Intake.StopChassisIntake;
import frc.robot.commands.Shooter.SetShooterPos;
import frc.robot.commands.Shooter.StopShooter;
import frc.robot.commands.Shooter.StopShooterArm;

public class StopCargoCarriage extends CommandGroup {
  /**
   * Add your docs here.
   */
  public StopCargoCarriage() {
    addParallel(new SetShooterPos(820));
    addSequential(new StopChassisIntake());
    addSequential(new StopShooter());
    addSequential(new WaitCommand(1.5));
    addSequential(new StopShooterArm());
  }
}
