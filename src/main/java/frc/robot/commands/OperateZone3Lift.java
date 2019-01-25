/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OperateZone3Lift extends CommandBase {

  double power = 0;

  public OperateZone3Lift() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(zone3lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Initialized Lift Command!!!");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("x");
    zone3lift.liftPower(power);
    if(oi.getController().getXButtonPressed())
    {
      power = .01 + power;
    }
    if(oi.getController().getBButtonPressed())
    {
      power = -.01 + power;
    }
    SmartDashboard.putNumber("power", power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    zone3lift.liftPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
