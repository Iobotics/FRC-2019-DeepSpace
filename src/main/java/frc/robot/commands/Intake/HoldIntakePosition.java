/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import frc.robot.commands.CommandBase;

public class HoldIntakePosition extends CommandBase {


  private double position;

  public HoldIntakePosition(double position) {
    requires(chassisIntake);
    this.position = position;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    chassisIntake.setArmPosition(position);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    chassisIntake.setArmPosition(position);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    chassisIntake.setIntakeArm(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
