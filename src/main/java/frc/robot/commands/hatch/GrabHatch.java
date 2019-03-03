/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hatch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.CommandBase;

public class GrabHatch extends CommandBase {

  private boolean isHatchIn = false;

  public GrabHatch() {
    requires(hatchCollector);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    hatchCollector.extendHatch();
    hatchCollector.openHook();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(hatchCollector.getHatchSensor()){
      isHatchIn = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isHatchIn;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    hatchCollector.retractHatch();
    hatchCollector.closeHook();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}