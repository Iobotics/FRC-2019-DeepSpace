/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.hal.ConstantsJNI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.ball.FromShipToHome;
import frc.robot.commands.Shooter.HoldShooterPos;
import frc.robot.commands.Shooter.RunShooter;
import frc.robot.commands.Shooter.SetShooterPos;

public class IntakeBall extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IntakeBall() {
      addSequential(new SetShooterPos(Constants.shooterIntake));
      addSequential(new SetIntakePosition(Constants.intakeArmIntake));
      addParallel(new RunChassisIntake());
      addParallel(new RunShooter(0.5));
  }
}
