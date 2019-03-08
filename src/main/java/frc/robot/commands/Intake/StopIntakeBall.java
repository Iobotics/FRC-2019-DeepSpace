/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.Shooter.StopShooter;

public class StopIntakeBall extends CommandGroup {
  /**
   * Add your docs here.
   */
  public StopIntakeBall() {
    addSequential(new StopChassisIntake());
    addParallel(new HoldIntakePosition(Constants.intakeArmHome));
    addSequential(new StopShooter());
    addSequential(new WaitCommand(1.5));
    addSequential(new StopChassisIntake());
  }
}
