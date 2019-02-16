/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunShooter extends CommandBase {

  double power;
  boolean ballIsIn = false;

  public RunShooter (double power) {
    requires(intake);
    this.power = power;
    ballIsIn = false;
  }

  @Override
  protected void initialize() {
    ballIsIn = false;
    intake.setShooter(0);
  }

  @Override
  protected void execute() {
    
    //If the ball is in then ball is in becomes true
    if(intake.isBallIn() && power > 0){
      ballIsIn = true;
    }
    
    else intake.setShooter(power);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  //if a vall is in then gives the shooter a nominal voltage to hold the ball
  @Override
  protected void end() {  
    if(ballIsIn){
      intake.setShooter(0.05);
    }
    else intake.setShooter(0);
    ballIsIn = false;
  }

  @Override
  protected void interrupted() {
    end();
  }
}
