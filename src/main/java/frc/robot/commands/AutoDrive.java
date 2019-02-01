/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDrive extends CommandBase {

  double frontLeftSetPoint, frontRightSetPoint, backLeftSetPoint, backRightSetPoint;
  double target;

  public AutoDrive(double target) {
    requires(drivetrain);
    this.target = target;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
   frontLeftSetPoint = drivetrain.getFrontLeftPosition() + target;
   frontRightSetPoint = drivetrain.getFrontRightPosition() - target;
   backLeftSetPoint = drivetrain.getBackLeftPosition() + target;
   backRightSetPoint = drivetrain.getBackRightPosition() - target;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drivetrain.setSetPoint(frontLeftSetPoint, frontRightSetPoint, backLeftSetPoint, backRightSetPoint);
    SmartDashboard.putNumber("frontRightPos", drivetrain.getFrontRightPosition());
    SmartDashboard.putNumber("FrontLEftPos", drivetrain.getFrontLeftPosition());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (drivetrain.getFrontLeftPosition() >= frontLeftSetPoint);
  }
  
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
