/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.HabThree.LevelReset;
import frc.robot.commands.Intake.HoldIntakePosition;
import frc.robot.commands.Intake.SetIntakePosition;
import frc.robot.commands.Intake.StopChassisIntake;

public class ExitHab3 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ExitHab3() {
    addParallel(new SetIntakePosition(Constants.intakeArmIntake));
    addParallel(new LevelReset());
    addParallel(new StopChassisIntake());
  }
}
