/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ZoneTwo;

import frc.robot.commands.CommandBase;

public class ToggleZoneTwoFront extends CommandBase {
  public ToggleZoneTwoFront() {

    requires(levelTwo);
  }

  //Toggles front piston, if they are up they deploy, if they are down they retract
  @Override
  protected void initialize() {
    if(!levelTwo.midWheelDown()){
      levelTwo.deployMidWheels();
    } else {
      levelTwo.retractMidWheels();
    }
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}
