/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.Intake.SetIntakePosition;
import frc.robot.commands.Intake.StopChassisIntake;
import frc.robot.commands.Shooter.SetShooterPos;

public class ReturnHome extends CommandGroup {
  /**
   * Add your docs here.
   */

  

  public ReturnHome() {
    addSequential(new SetShooterPos(Constants.shooterHome));
    addParallel(new SetIntakePosition(Constants.intakeArmHome));
    addSequential(new WaitCommand(1.5));
    addSequential(new StopChassisIntake());
  }
}
