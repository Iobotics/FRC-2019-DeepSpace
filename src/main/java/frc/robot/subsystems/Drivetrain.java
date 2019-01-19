/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.OperateMecanumDrive;
import frc.robot.commands.OperateTankDrive;
import frc.util.SparkWrapper;
import frc.util.SparkWrapper;

public class Drivetrain extends Subsystem {

  private CANSparkMax _frontLeftMain;
  private CANSparkMax _frontRightMain;
  private CANSparkMax _backLeftMain;
  private CANSparkMax _backRightMain;

  private double kP = 0;
  private double kI = 0;
  private double kD = 0;
  private double kIZone = 0;
  private double kFF = 0;
  private final int PID_TURN = 0;


  private DoubleSolenoid _solenoid;

  private MecanumDrive drive;
  
  public void init() {
    _frontLeftMain = new CANSparkMax(RobotMap.frontLeftMain, MotorType.kBrushless);
    _frontLeftMain.setInverted(true);
    //_frontLeftMain.getPIDController().setP(kP, PID_TURN);

    _frontRightMain =  new CANSparkMax(RobotMap.frontRightMain, MotorType.kBrushless);
    _frontRightMain.setInverted(true);

    _backLeftMain = new CANSparkMax(RobotMap.backLeftMain, MotorType.kBrushless);
    _backLeftMain.setInverted(true);
    
    _backRightMain = new CANSparkMax(RobotMap.backRightMain, MotorType.kBrushless);
    _backRightMain.setInverted(true);
   

    drive = new MecanumDrive(
      new SparkWrapper(_frontLeftMain), 
      new SparkWrapper(_backLeftMain), 
      new SparkWrapper(_frontRightMain),
      new SparkWrapper(_backRightMain)
    );

    _solenoid = new DoubleSolenoid(0, 1);
  }

  public void setTank(double left, double right) {
    _frontLeftMain.set(left);
    _backLeftMain.set(left);
    _frontRightMain.set(right);
    _backRightMain.set(right);
  }

  public void setMecanum(double x, double y, double rotation) {
    drive.driveCartesian(x, y, rotation);
  }

  public void setTurn(double target, double gyroAngle) {
    _frontLeftMain.pidWrite(gyroAngle / 180);
    _frontLeftMain.getPIDController().setReference(target * 180, ControlType.kDutyCycle, PID_TURN);

    _backLeftMain.set(_frontLeftMain.get());
    _backRightMain.set(-_frontLeftMain.get());
    _frontRightMain.set(-_frontLeftMain.get());
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new OperateTankDrive());
  }
}
