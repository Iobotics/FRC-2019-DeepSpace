/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import frc.robot.commands.CommandBase;

public class RunShooter extends CommandBase {

  double power;

  public RunShooter (double power) {
    requires(shooter);
    this.power = power;
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    
    //If the ball is detected while the command is running then ball is in becomes true
    if(shooter.isBallIn() && power > 0){
      shooter.setIsBallIn(true);
      shooter.setShooter(power);
    }

    else if (shooter.isBallIn() && power < 0){
      shooter.setIsBallIn(false);
      shooter.setShooter(power);
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
   
  }

  @Override
  protected void interrupted() {
    end();
  }
}
