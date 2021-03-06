/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drivetrain;


import frc.robot.Constants;
import frc.robot.commands.CommandBase;

public class AutoDrive extends CommandBase {

  private double frontLeftSetPoint, frontRightSetPoint, backLeftSetPoint, backRightSetPoint;
  private double target;
  private boolean _onTarget = false;
  private double _onTargetTime;

  public AutoDrive(double target) {
    requires(drivetrain);
    this.target = target / Constants.rotationsToInches;
  }

  private boolean isOnTarget(){
    return Constants.toleranceRange >= Math.abs(frontLeftSetPoint - drivetrain.getFrontLeftPosition());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
   frontLeftSetPoint = drivetrain.getFrontLeftPosition() - target;
   frontRightSetPoint = drivetrain.getFrontRightPosition() + target;
   backLeftSetPoint = drivetrain.getBackLeftPosition() - target;
   backRightSetPoint = drivetrain.getBackRightPosition() + target;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drivetrain.setSetPoint(frontLeftSetPoint, frontRightSetPoint, backLeftSetPoint, backRightSetPoint);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(!isOnTarget()){
      _onTarget = false;
      _onTargetTime = Double.MAX_VALUE;
      return false;
    }

    else if (isOnTarget() && !_onTarget){
      _onTarget = true;
      _onTargetTime = this.timeSinceInitialized() + 1;
      return false;
    }

    else if (isOnTarget() && _onTarget && this.timeSinceInitialized() <= _onTargetTime){
      return false;
    }

  return true;
  }
  
  @Override
  protected void end() {
    drivetrain.setTank(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
