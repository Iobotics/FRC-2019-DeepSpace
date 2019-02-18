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
import com.revrobotics.CANSparkMaxFrames.SetpointOut;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ChassisIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private TalonSRX chassisIntake;
  public Solenoid extender;

  public void init(){
    chassisIntake = new TalonSRX(RobotMap.chassisIntake);
    chassisIntake.setNeutralMode(NeutralMode.Brake);
    extender = new Solenoid(RobotMap.intakeExtender);
  }

  public void setPower(double power){
    chassisIntake.set(ControlMode.PercentOutput, power);
  }

  public void extendIntake(){
    extender.set(true);
  }
  
  public void retractIntake(){
    extender.set(false);
  }

  public void toggleIntake(){
    if(extender.get()){
      extender.set(false);
    }
    else extender.set(true);
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
