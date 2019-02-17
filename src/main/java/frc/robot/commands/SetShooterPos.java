/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.ShooterArmPosition;

public class SetShooterPos extends CommandBase {

  int pos;
  boolean isHome = false;

  //Either Takes an Enum for position or just Raw Encoder Value
  public SetShooterPos(ShooterArmPosition armPosition) {
    requires(shooter);
    this.pos = armPosition.angle();
    this.isHome = armPosition.isHome();
  }

  public SetShooterPos(int pos){
    this.pos = pos;
  }

  @Override
  protected void initialize() {
  }

  //if the value is Home then just turn off the motor, only use when at the bottom
  @Override
  protected void execute() {
    if(isHome){
      shooter.setShooterArm(0);
    }
    else shooter.setShooterPosition(pos);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    shooter.setShooterArm(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}