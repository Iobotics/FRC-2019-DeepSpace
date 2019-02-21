/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.LiftMotorTest;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  
  private TalonSRX _leftLift;
  private TalonSRX _leftLiftSlave;
  private TalonSRX _rightLift;
  private TalonSRX _rightLiftSlave;

  private double sensorRange  = 1024;
  private double kP = 2.4;
  private double kI;
  private double kD;
  private double kFF  = 1023 / ((sensorRange * 2) /10);
  private int kIZone;
  private int cruiseSpeed = 100;
  private int rampRate = 300;
  private final int TIMEOUT = 200;
  private final int SLOT = 0;


  public void init(){
    _leftLift = new TalonSRX(RobotMap.leftLift);
    _leftLiftSlave = new TalonSRX(RobotMap.leftLiftSlave);
    _leftLiftSlave.follow(_leftLift);

    _rightLift = new TalonSRX(RobotMap.rightLift);
    _rightLiftSlave = new TalonSRX(RobotMap.rightLiftSlave);
    _leftLiftSlave.follow(_rightLift);

    _leftLift.configFactoryDefault();
    _rightLift.configFactoryDefault();

    _leftLift.enableCurrentLimit(true);
    _rightLift.enableCurrentLimit(true);
    _leftLift.configPeakCurrentLimit(40);
    _leftLift.configContinuousCurrentLimit(40);
    _rightLift.configPeakCurrentLimit(40);
    _rightLift.configContinuousCurrentLimit(40);

    _leftLift.setInverted(false);
    _leftLift.setSensorPhase(true);
    _rightLift.setInverted(true);
    _rightLift.setSensorPhase(true);

    _leftLift.setNeutralMode(NeutralMode.Brake);
    _rightLift.setNeutralMode(NeutralMode.Brake);


    _leftLift.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    _leftLift.configFeedbackNotContinuous(true, TIMEOUT);
    _rightLift.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    _rightLift.configFeedbackNotContinuous(true, TIMEOUT);

    _leftLift.configNominalOutputForward(0);
    _leftLift.configNominalOutputReverse(0);
    _leftLift.configPeakOutputForward(1);
    _leftLift.configPeakOutputReverse(-1);
 
    _rightLift.configNominalOutputForward(0);
    _rightLift.configNominalOutputReverse(0);
    _rightLift.configPeakOutputForward(1);
    _rightLift.configPeakOutputReverse(-1);

    _leftLift.selectProfileSlot(SLOT, 0);
    _leftLift.config_kP(SLOT, kP, TIMEOUT);
    _leftLift.config_kI(SLOT, kI, TIMEOUT);
    _leftLift.config_kD(SLOT, kD, TIMEOUT);
    _leftLift.config_kF(SLOT, kFF, TIMEOUT);
    _leftLift.config_IntegralZone(kIZone, TIMEOUT);
    _leftLift.configMotionCruiseVelocity(cruiseSpeed, TIMEOUT);
    _leftLift.configMotionAcceleration(rampRate, TIMEOUT);

    _rightLift.selectProfileSlot(SLOT, 0);
    _rightLift.config_kP(SLOT, kP, TIMEOUT);
    _rightLift.config_kI(SLOT, kI, TIMEOUT);
    _rightLift.config_kD(SLOT, kD, TIMEOUT);
    _rightLift.config_kF(SLOT, kFF, TIMEOUT);
    _rightLift.config_IntegralZone(kIZone, TIMEOUT);
    _rightLift.configMotionCruiseVelocity(cruiseSpeed, TIMEOUT);
    _rightLift.configMotionAcceleration(rampRate, TIMEOUT);
  }

  public void setLiftPosition(double position){
    _leftLift.set(ControlMode.MotionMagic, position);
    _rightLift.set(ControlMode.MotionMagic, position);
  }

  public void setLeftSpeed(double speed){
    _leftLift.set(ControlMode.PercentOutput, speed);
  }

  public void setRightSpeed(double speed){
    _rightLift.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftMotorTest());
  }
}
 