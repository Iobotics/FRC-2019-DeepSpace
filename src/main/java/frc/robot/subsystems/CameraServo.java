/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class CameraServo extends Subsystem {

  private Servo servo;

  private boolean servoRotated = false;

  public void init()
  {
    servo = new Servo(1);
    servo.set(0.0);
  }

  public double getServoPosition(){
    return servo.get();
  }

  public void turnCamera(){ //at 0 the servo faces the cargo, at 1 the servo faces the hatches
    if(servoRotated){
      servo.set(1.0);
      servoRotated = false;
    }else if(!servoRotated){
      servo.set(0.0);
      servoRotated = true;
    }
  }


  public void safetyCancel(){
    servo.set(servo.get());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
