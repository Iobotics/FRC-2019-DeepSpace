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
    requires(shooter);
    this.power = power;
    ballIsIn = false;
  }

  @Override
  protected void initialize() {
    ballIsIn = false;
    shooter.setShooter(0);
  }

  @Override
  protected void execute() {
    
    //If the ball is in then ball is in becomes true
    if(shooter.isBallIn() && power > 0){
      ballIsIn = true;
    }
    
    else shooter.setShooter(power);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  //if a vall is in then gives the shooter a nominal voltage to hold the balls
  @Override
  protected void end() {  
    if(ballIsIn){
      shooter.setShooter(0.05);
    }
    else shooter.setShooter(0);
    ballIsIn = false;
  }

  @Override
  protected void interrupted() {
    end();
  }
}
