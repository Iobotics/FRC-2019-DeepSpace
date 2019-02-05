/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalGlitchFilter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  TalonSRX leftShooter;
  TalonSRX rightShooter;
  TalonSRX intake;
  TalonSRX shooterArm;
  
  Solenoid intakeOut;

  DigitalInput proximitySensor;

  private int slotID = 0;
  private double kP;
  private double kI;
  private double kD;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void init()
  {
    leftShooter = new TalonSRX(RobotMap.leftShooter);
    leftShooter.setInverted(true);
    leftShooter.setNeutralMode(NeutralMode.Coast);

    rightShooter = new TalonSRX(RobotMap.rightShooter);
    rightShooter.setInverted(false);
    rightShooter.setNeutralMode(NeutralMode.Coast);

    intake = new TalonSRX(RobotMap.intake);

    shooterArm = new TalonSRX(RobotMap.shooterArm);
    shooterArm.setNeutralMode(NeutralMode.Brake);
    shooterArm.configFactoryDefault();
    shooterArm.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    shooterArm.configFeedbackNotContinuous(true, 20);
    shooterArm.setSensorPhase(true);
    shooterArm.config_kP(slotID, kP);
    shooterArm.config_kI(slotID, kI);
    shooterArm.config_kP(slotID, kD);

    
    intakeOut = new Solenoid(RobotMap.intakeSolenoid);
    intakeOut.set(false);

    proximitySensor = new DigitalInput(RobotMap.proximitySensor);
  }

  public void setIntake(double speed){
    intake.set(ControlMode.PercentOutput, speed);
  }

  public void setShooter(double speed){
    rightShooter.set(ControlMode.PercentOutput, speed);
    leftShooter.set(ControlMode.PercentOutput, speed);
  }

  public void setShooterPosition(double position){
    shooterArm.set(ControlMode.Position, position);
  }
 
  public boolean isBallIn(){
    return !proximitySensor.get();
  }

  public void toggleSolenoid(){
    intakeOut.set(!intakeOut.get());
  }
}
