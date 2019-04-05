/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.GetBallIn;
import frc.robot.commands.LimelightAutoTurn;
/**
 * Add your docs here.
 */
public class CameraServo extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static Servo servo;

  public void init()
  {
    servo = new Servo(2); //Get actual channel
    servo.set(0);
  }

  public double getServoPosition(){
    return servo.get();
  }

  public void turnCamera(){
    if(servo.get() == 0.0){ //when servo is at position 0, facing the cargo, if it is 1 its facing hatch.  
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
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new LimelightAutoTurn());
    
  }
}
