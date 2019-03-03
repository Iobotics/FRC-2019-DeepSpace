/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import frc.robot.commands.CommandBase;

public class SetShooterPos extends CommandBase {

  int pos;

  //Either Takes an Enum for position or just Raw Encoder Value


  public SetShooterPos(int pos){
    this.pos = pos;
  }

  @Override
  protected void initialize() {
    shooter.setShooterPosition(pos);
  }

  //if the value is Home then just turn off the motor, only use when at the bottom
  @Override
  protected void execute() {
    shooter.setShooterPosition(pos);

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}