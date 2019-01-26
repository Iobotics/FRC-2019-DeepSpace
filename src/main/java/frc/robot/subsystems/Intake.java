/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalGlitchFilter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  
  TalonSRX leftLift;
  TalonSRX rightLift;

  DigitalInput proximitySensor;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void init()
  {
    //leftLift = new TalonSRX(RobotMap.leftLift);
    //rightLift = new TalonSRX(RobotMap.rightLift);

    proximitySensor = new DigitalInput(RobotMap.proximitySensor);
  }

  public void setPower(double power)
  {
    leftLift.set(ControlMode.PercentOutput, power);
    rightLift.set(ControlMode.PercentOutput, -power);
  }
  
  public boolean isBallIn(){
    return !proximitySensor.get();
  }
}
