
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


public class RunChassisIntake extends CommandBase {
  public RunChassisIntake() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(chassisIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    chassisIntake.extendIntake();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    chassisIntake.setPower(.7);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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