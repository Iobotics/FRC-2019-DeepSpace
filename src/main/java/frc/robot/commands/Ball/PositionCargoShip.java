/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Ball;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Intake.HoldIntakePosition;
import frc.robot.commands.Shooter.HoldShooterPos;
import frc.robot.commands.Shooter.SetShooterPos;

public class PositionCargoShip extends CommandGroup {
  /**
   * Add your docs here.
   */
  public PositionCargoShip() {
    addParallel(new SetShooterPos(Constants.cargoShipAngle));
    addParallel(new HoldIntakePosition(Constants.intakeArmIntake));
  }
}
