/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoAlighnment;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CameraAssist extends CommandGroup {
  /**
   * Add your docs here.
   */
  private boolean isRocket = false;

  public CameraAssist(boolean isRocket) {
    //addSequential(new SetLimelightLED(true));
    addSequential(new TurnToTarget(isRocket));
    addSequential(new CameraAssistStrafe());
    addSequential(new TurnToTarget(isRocket));
    addSequential(new CameraAssistStrafe());
    addSequential(new CameraAssistDistance());
    addSequential(new TurnToTarget(isRocket));
    //addSequential(new SetLimelightLED(false));
  }
}
