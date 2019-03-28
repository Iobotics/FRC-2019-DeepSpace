/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GoToHab3 extends CommandBase {

  // Uncomment if calculations are figured out

  //private static final double extendTime = 3; // Units: Seconds
  //private static final double radiusIntake = 16.092; // Units: Inches
  //private static final double strokeLength = 22; // Units: Inches
  //private static final double angularVelocityIntake = strokeLength / (extendTime * radiusIntake); // Units: Radians per second
  private static final double INTAKEVELOCITY = 6; // Before 10
  private static final double CHASSISPOWER = -1;

  public GoToHab3() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(chassisIntake);
    requires(levelTwo);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    levelTwo.deployBackWheels();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    chassisIntake.setArmVelocity(INTAKEVELOCITY);
    if(oi.getAButton())
    {
      chassisIntake.setPower(CHASSISPOWER);
      levelTwo.disableCompressor();
    }
    else
    {
      chassisIntake.setPower(0);
      levelTwo.startCompressor();
    }
    //SmartDashboard.putNumber("chassis wheels power", chassisIntake.getPower());
    SmartDashboard.putNumber("intake velocity", chassisIntake.getArmVelocity());
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
