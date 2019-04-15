/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.LimelightAutoTurn;

/**
 * Add your docs here.
 */
public class LimelightServo extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static Servo servo;

  private boolean servoCargoSide = false;

  public void init()
  {
    servo = new Servo(1); //Get actual channel
    servo.set(1.0);
  }

  public boolean getServoPosition(){
    return servoCargoSide;
  }

  public void turnLimelight(){ //at 0 the servo faces the cargo, at 1 the servo faces the hatches
    if(servoCargoSide){
      servo.set(1.0);
      servoCargoSide = false;
    }else if(!servoCargoSide){
      servo.set(0.0);
      servoCargoSide = true;
    }
  }

  public void setCameraCargo(){
    servo.set(0.0);
    servoCargoSide = true;
  }

  public void setCameraHatch(){
    servo.set(1.0);
    servoCargoSide = false;
  }


  public void safetyCancel(){
    servo.set(servo.get());
  }

  public boolean isOnCargoSide()
  {
    if(servoCargoSide)
    {
      return true;
    }
    return false;
  }

  public double onCargoSideMultiplier() 
  {
    if(servoCargoSide)
    {
      return 1;
    }
    return -1;
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new LimelightAutoTurn());
  }
}
