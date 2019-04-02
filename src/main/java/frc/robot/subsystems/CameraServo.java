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

private Servo _servo;

  public void init(){
    _servo = new Servo(1);
    _servo.set(1.0);
  }
    
  public double getServoPosition(){
    return _servo.get();
  }

  public void turnCamera(){
    _servo.get();
    if(_servo.get() == 0.0){
      _servo.set(1.0);
    }else if(_servo.get() == 1.0){
      _servo.set(0.0);
    }
    
  }

  public void safetyCancel(){
    _servo.set(0.0);
  }

  @Override
  public void initDefaultCommand() {
  }
}

