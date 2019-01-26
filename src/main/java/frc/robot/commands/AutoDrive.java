/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDrive extends CommandBase {

  double setPoint;
  double target;

  public AutoDrive(double target) {
    requires(drivetrain);
    this.target = target;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drivetrain.setSetPoint(target);
    setPoint = drivetrain.getPosition() + target  / drivetrain.INCHES_PER_ROTATION;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drivetrain.driveToTarget();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (drivetrain.getPosition() == setPoint);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
