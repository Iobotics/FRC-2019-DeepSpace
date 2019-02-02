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
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  
  private TalonSRX _leftLift;
  private TalonSRX _rightLift;

  private double kP;
  private double kI;
  private double kD;
  private double kFF;
  private int kIZone;
  private int cruiseSpeed;
  private int rampRate;
  private final int TIMEOUT = 20;
  private final int SLOT = 0;


  public void init(){
    _leftLift = new TalonSRX(RobotMap.leftLift);
    _leftLift.setInverted(true);

    _rightLift = new TalonSRX(RobotMap.rightLift);
    _rightLift.setInverted(false);

    _leftLift.configFactoryDefault();
    _rightLift.configFactoryDefault();

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

  public void setPosition(double position){
    _leftLift.set(ControlMode.MotionMagic, position);
    _rightLift.set(ControlMode.MotionMagic, position);
  }

  @Override
  public void initDefaultCommand() {
    
  }
}
