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

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Subsystem of Lift and PID control for it height
 */
public class Lift extends Subsystem {
  
  // Each side of the lift has a master and slave motor
  private TalonSRX leftLift;
  private TalonSRX leftLiftSlave;
  private TalonSRX rightLift;
  private TalonSRX rightLiftSlave;

  // Full range of the pot, used to calculate FF and for soft Limit
  private double sensorRange  = 1024;
  /*
   * PID values for precision lift control
   * TODO: Find Values experimentally
   */
  private double kP = 2.4;
  private double kI;
  private double kD;
  private double kFF  = 1023 / ((sensorRange * 2) /10);
  private int kIZone;
  /*
   * Maximum Velocity and Acceleration Allowed
   * Units are in Sensor Units per 100ms
   */
  private int cruiseSpeed = 100;
  private int rampRate = 300;
  private final int TIMEOUT = 200;
  private final int SLOT = 0;


  public void init(){
    leftLift = new TalonSRX(RobotMap.leftLift);
    leftLiftSlave = new TalonSRX(RobotMap.leftLiftSlave);
    leftLiftSlave.follow(leftLift);

    rightLift = new TalonSRX(RobotMap.rightLift);
    rightLiftSlave = new TalonSRX(RobotMap.rightLiftSlave);
    rightLiftSlave.follow(rightLift);

    leftLift.configFactoryDefault();
    rightLift.configFactoryDefault();

    // Current Limiting to prevent magic smoke from escaping, remove if more power required
    leftLift.enableCurrentLimit(true);
    rightLift.enableCurrentLimit(true);
    leftLift.configPeakCurrentLimit(40);
    leftLift.configContinuousCurrentLimit(40);
    rightLift.configPeakCurrentLimit(40);
    rightLift.configContinuousCurrentLimit(40);

    // Polarity of motors and sensor, if changed will cause burnout
    leftLift.setInverted(false);
    leftLift.setSensorPhase(true);
    rightLift.setInverted(true);
    rightLift.setSensorPhase(true);

    leftLift.setNeutralMode(NeutralMode.Brake);
    rightLift.setNeutralMode(NeutralMode.Brake);

    leftLift.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    leftLift.configFeedbackNotContinuous(true, TIMEOUT);
    rightLift.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    rightLift.configFeedbackNotContinuous(true, TIMEOUT);

    leftLift.configNominalOutputForward(0);
    leftLift.configNominalOutputReverse(0);
    leftLift.configPeakOutputForward(1);
    leftLift.configPeakOutputReverse(-1);
 
    rightLift.configNominalOutputForward(0);
    rightLift.configNominalOutputReverse(0);
    rightLift.configPeakOutputForward(1);
    rightLift.configPeakOutputReverse(-1);

    leftLift.selectProfileSlot(SLOT, 0);
    leftLift.config_kP(SLOT, kP, TIMEOUT);
    leftLift.config_kI(SLOT, kI, TIMEOUT);
    leftLift.config_kD(SLOT, kD, TIMEOUT);
    leftLift.config_kF(SLOT, kFF, TIMEOUT);
    leftLift.config_IntegralZone(kIZone, TIMEOUT);
    leftLift.configMotionCruiseVelocity(cruiseSpeed, TIMEOUT);
    leftLift.configMotionAcceleration(rampRate, TIMEOUT);

    rightLift.selectProfileSlot(SLOT, 0);
    rightLift.config_kP(SLOT, kP, TIMEOUT);
    rightLift.config_kI(SLOT, kI, TIMEOUT);
    rightLift.config_kD(SLOT, kD, TIMEOUT);
    rightLift.config_kF(SLOT, kFF, TIMEOUT);
    rightLift.config_IntegralZone(kIZone, TIMEOUT);
    rightLift.configMotionCruiseVelocity(cruiseSpeed, TIMEOUT);
    rightLift.configMotionAcceleration(rampRate, TIMEOUT);
  }

  public void setLiftPosition(double position){
    leftLift.set(ControlMode.MotionMagic, position);
    rightLift.set(ControlMode.MotionMagic, position);
  }

  public void setLeftSpeed(double speed){
    leftLift.set(ControlMode.PercentOutput, speed);
  }

  public void setRightSpeed(double speed){
    rightLift.set(ControlMode.PercentOutput, speed);
  }

  // Stops motors from using positional control, robot stops powering them
  public void StopLift(){
    leftLift.set(ControlMode.PercentOutput, 0);
    rightLift.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void initDefaultCommand() {
   
  }
}
 