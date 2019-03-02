/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Shooter.RunShooter;

public class IntakeBall extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IntakeBall() {
    addSequential(new RunChassisIntake());
    addParallel(new HoldIntakePosition(Constants.intakeArmIntake));
    addParallel(new RunShooter(0.4));
  }
}
