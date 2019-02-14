/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OperateMecanumDrive extends CommandBase {

  double test = 0;

  public OperateMecanumDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("test", test);

    double x = -oi.getLeftStickX()*.3;
    double y = oi.getLeftStickY()*.3;
    //double rotation = oi.getRightStickX();
    double rotation = 0;
    if(oi.getXButton())
    {
      rotation = -.2;
    }
    if(oi.getYButton())
    {
      rotation = .2;
    }
    if(oi.getAButton())
    {
      test++;
    }
    drivetrain.setMecanum(-x, -y, rotation);
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
  }
}
