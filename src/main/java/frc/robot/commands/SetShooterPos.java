/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetShooterPos extends CommandBase {

  //TODO: Find Correct Angles

  public enum ShooterArmPosition{
    Home(820), Cargo(900), LevelOne(900), LevelTwo(900), LevelThree(900);

    public final double angle;

    private ShooterArmPosition(final double angle){
      this.angle = angle;
    }

    public double angle(){
      return angle;
    }
  }

  int pos;

  public SetShooterPos(int position) {
    requires(intake);
    pos = position;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    intake.setShooterPosition(pos);
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
  }
}
