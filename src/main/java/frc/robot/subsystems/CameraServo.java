/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.RotateCamera;

/**
 * Add your docs here.
 */
public class camServo extends Subsystem {

private Servo servo;

  public void init(){
    servo = new Servo(1);
  }

  public void turnCamera(){
    servo.setAngle(180);
  }

  public void safetyCancel(){
    servo.setAngle(0);
  }

  @Override
  public void initDefaultCommand() {
   setDefaultCommand(new RotateCamera());
  }
}
}
