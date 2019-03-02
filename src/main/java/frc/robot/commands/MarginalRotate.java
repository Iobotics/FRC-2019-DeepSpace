/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MarginalRotate extends CommandBase {

  double position = .5;

  public MarginalRotate() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(rotater);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    rotater.turnCamera(position);
    if(oi.getControllerA())
    {
      position += .01;
    }
    else if(oi.getControllerB())
    {
      position -= .01;
    }

    SmartDashboard.putBoolean("A button", oi.getControllerA());
    SmartDashboard.putNumber("Position", rotater.getServoPosition());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
