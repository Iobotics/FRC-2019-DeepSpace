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

  public void init()
  {
    servo = new Servo(2); //Get actual channel
    servo.set(0.0);
  }

  public double getServoPosition(){
    return servo.getAngle();
  }

  public void turnLimelight(){ //at 0 the servo faces the cargo, at 1 the servo faces the hatches
    if(servo.get() == 0.0){
      servo.set(1.0);
    }else if(servo.get() == 1.0){
      servo.set(0.0);
    }
  }

  public void setCameraCargo(){
    servo.set(0.0);
  }

  public void setCameraHatch(){
    servo.set(1.0);
  }


  public void safetyCancel(){
    servo.set(servo.get());
  }

  public boolean isOnCargoSide()
  {
    if(servo.get() == 0.0)
    {
      return true;
    }
    return false;
  }

  public double onCargoSideMultiplier() 
  {
    if(servo.get() == 0.0)
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
