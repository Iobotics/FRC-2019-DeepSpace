/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.MarginalRotate;
import frc.robot.commands.RotateCamera;

/**
 * Add your docs here.
 */
public class CameraServo extends Subsystem {

private Servo servo;

  public void init(){
    servo = new Servo(1);
    servo.set(0.0);
  }
    
  public double getServoPosition(){
    return servo.get();
  }

  public void turnCamera(){
   if(servo.get() == 0.0){
    servo.set(1.0);
   }else if (servo.get() == 1.0){
    servo.set(0.0);
   }else{
    servo.set(0.0);
   }
}

public void turnCamera(double position)
{
  servo.set(position);
}

  public void safetyCancel(){
    servo.set(servo.get());
  }

  @Override
  public void initDefaultCommand() {
   //setDefaultCommand(new RotateCamera());
   //setDefaultCommand(new MarginalRotate());
  }
}

