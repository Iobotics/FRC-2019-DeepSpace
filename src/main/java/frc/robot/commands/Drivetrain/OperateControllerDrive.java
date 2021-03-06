/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CommandBase;

public class OperateControllerDrive extends CommandBase {

  public static final double DEADBAND = 0.2;
  
  public OperateControllerDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(drivetrain);
    requires(navSensor);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() { }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   // double x = Math.abs(oi.getRightControllerX()) < DEADBAND ? 0 : oi.getRightControllerX();
    //double y = Math.abs(oi.getRightControllerY()) < DEADBAND ? 0 : oi.getRightControllerY();
   // double rotation = Math.abs(oi.getLeftControllerX()) < DEADBAND ? 0 : -oi.getLeftControllerX();

    SmartDashboard.putNumber("Gyro: ", navSensor.getAngle());
    SmartDashboard.putNumber("Motor Temperature", drivetrain.getTemperature());
    //drivetrain.setMecanum(x, y, rotation);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drivetrain.setMecanum(0, 0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    drivetrain.setMecanum(0, 0, 0, 0);
  }

}
