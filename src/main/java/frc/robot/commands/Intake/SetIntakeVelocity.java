/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CommandBase;

public class SetIntakeVelocity extends CommandBase {

  private static double time = 0;
  private static Timer timer; //Returns time in seconds
  private static final double ENDTIME = 8;
  private static final double VELOCITY = 25;

  public SetIntakeVelocity() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(chassisIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //timer = new Timer();
    //timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    chassisIntake.setArmVelocity(VELOCITY);
    SmartDashboard.putNumber("velocity", chassisIntake.getArmVelocity());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    /*if(timer.get() >= ENDTIME)
    {
      return true;
    }*/
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    chassisIntake.setArmVelocity(0);
    //timer.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
