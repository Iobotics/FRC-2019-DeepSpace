/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.LimelightAutoTurn;
import frc.robot.commands.SetLEDPattern;

public class ShootBall extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ShootBall() {
    addParallel(new RunShooter(0.5));
    addSequential(new WaitCommand(0.25));   
    addSequential(new SetLEDPattern(.72)); 
    addSequential(new RunShooter(-0.45));

  }
}
