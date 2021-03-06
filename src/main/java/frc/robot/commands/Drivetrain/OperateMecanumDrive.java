/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CommandBase;

public class OperateMecanumDrive extends CommandBase {

  public static final double DEADBAND = 0.3; // .2 before
  
  public OperateMecanumDrive() {
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
    double x = Math.abs(oi.getRightStickX()) < DEADBAND ? 0 : Math.pow(oi.getRightStickX(), 3);
    double y = Math.abs(oi.getRightStickY()) < DEADBAND ? 0 : -Math.pow(oi.getRightStickY(), 3);
    double rotation = Math.abs(oi.getLeftStickX()) < DEADBAND ? 0 : oi.getLeftStickX();

    SmartDashboard.putNumber("Gyro: ", navSensor.getAngle());
    SmartDashboard.putNumber("Motor Temperature", drivetrain.getTemperature());
    drivetrain.setMecanum(-x, y, rotation);
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
