/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.OperateMecanumDrive;

public class Drivetrain extends Subsystem {

  private CANSparkMax _frontLeftMain;
  private CANSparkMax _frontRightMain;
  private CANSparkMax _backLeftMain;
  private CANSparkMax _backRightMain;

  private double kP = 0;
  private double kI = 0;
  private double kD = 0;
  private double kIZone = 0;
  private double kF = 0;
  private final int PID_TURN_SLOT = 0;

  private DoubleSolenoid _solenoid;

  private MecanumDrive drive;
  
  public void init(){
    _frontLeftMain = new CANSparkMax(RobotMap.frontLeftMain, MotorType.kBrushless);
    _frontLeftMain.setInverted(true);
    _frontLeftMain.getPIDController().setP(kP, PID_TURN_SLOT);

    _frontRightMain =  new CANSparkMax(RobotMap.frontRightMain, MotorType.kBrushless);
    _frontRightMain.setInverted(true);

    _backLeftMain = new CANSparkMax(RobotMap.backLeftMain, MotorType.kBrushless);
    _backLeftMain.setInverted(true);
    
    _backRightMain = new CANSparkMax(RobotMap.backRightMain, MotorType.kBrushless);
    _backRightMain.setInverted(true);
   
    drive = new MecanumDrive(_frontLeftMain, _backLeftMain, _frontRightMain, _backRightMain);
    
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

  public void setMecanum(double x, double y, double rotation, double gyroAngle) {
    drive.driveCartesian(x, y, rotation, gyroAngle);
  }

  public void setTurn(double target, double gyroAngle){
    _frontLeftMain.pidWrite(gyroAngle / 180);
    _frontLeftMain.getPIDController().setReference(target * 180, ControlType.kDutyCycle, PID_TURN_SLOT);

    _backLeftMain.set(_frontLeftMain.get());
    _backRightMain.set(-_frontLeftMain.get());
    _frontRightMain.set(-_frontLeftMain.get());
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new OperateMecanumDrive());
    setDefaultCommand(null);
  }

}
