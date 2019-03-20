/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import frc.robot.commands.CommandBase;


public class RunChassisIntake extends CommandBase {
  
  private final static double NUMBER = -0.6; // Before -.5
  
  public RunChassisIntake() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(chassisIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    chassisIntake.setPower(NUMBER); // -.5 Before
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    chassisIntake.setPower(NUMBER);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //chassisIntake.setArmPosition(198);
    chassisIntake.setPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}