/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ball;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.Shooter.RunShooter;
import frc.robot.commands.Shooter.SetShooterPos;
import frc.robot.commands.Shooter.StopShooter;
import frc.robot.commands.Shooter.StopShooterArm;

public class ShootCargoShip extends CommandGroup {
  /**
   *  Automatically Shoots for Cargo Ship
   */
  public ShootCargoShip() {
    addSequential(new SetShooterPos(Constants.cargoShipAngle));
    addSequential(new WaitCommand(0.5));
    addParallel(new RunShooter(-1));
    addSequential(new WaitCommand(0.5));
    addSequential(new StopShooterArm());
    addSequential(new StopShooter());
  }
}
