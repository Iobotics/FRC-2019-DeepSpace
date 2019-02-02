/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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
  TalonSRX leftIntake;
  TalonSRX rightIntake;
  TalonSRX outsideIntake;
  TalonSRX intakeRaise;

  DigitalInput proximitySensor;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void init()
  {
    leftIntake = new TalonSRX(RobotMap.leftIntake);
    leftIntake.setInverted(true);
    leftIntake.setNeutralMode(NeutralMode.Coast);

    rightIntake = new TalonSRX(RobotMap.rightIntake);
    rightIntake.setInverted(false);
    rightIntake.setNeutralMode(NeutralMode.Coast);

    outsideIntake = new TalonSRX(RobotMap.outsideIntake);

    intakeRaise = new TalonSRX(RobotMap.intakeRaise);
    intakeRaise.setNeutralMode(NeutralMode.Brake);
    
    proximitySensor = new DigitalInput(RobotMap.proximitySensor);
  }

  public void setPower(double power)
  {
    leftIntake.set(ControlMode.PercentOutput, power);
    rightIntake.set(ControlMode.PercentOutput, -power);
    if(power > 0){
      outsideIntake.set(ControlMode.PercentOutput, power);
    }
  }
  
  public void moveToPos(int position){
    double power = 0;
    while(intakeRaise.getSelectedSensorPosition() < position - 5 || intakeRaise.getSelectedSensorPosition() > position + 5){
      if(power < .7){
        power += .001;
      }
      intakeRaise.set(ControlMode.PercentOutput, power * (position - intakeRaise.getSelectedSensorPosition()) / Math.abs((position - intakeRaise.getSelectedSensorPosition())));
    }
    intakeRaise.set(ControlMode.PercentOutput, 0);
  }

  public boolean isBallIn(){
    return !proximitySensor.get();
  }
}
