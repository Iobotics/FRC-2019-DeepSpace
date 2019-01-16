/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.OperateMecanumDrive;
import frc.robot.commands.OperateTankDrive;
import frc.util.SparkWrapper;
import frc.util.SparkWrapper;

public class Drivetrain extends PIDSubsystem {

  private CANSparkMax _frontLeftMain;
  private CANSparkMax _frontRightMain;
  private CANSparkMax _backLeftMain;
  private CANSparkMax _backRightMain;

  public static double kP = 0.5;
  public static double kI = 0;
  public static double kD = 0;
  public static double kFF = 0;

  private final double INCHES_PER_ROTATION = 4 * Math.PI;

  private MecanumDrive drive;

  public Drivetrain(){
    super("DrveTrain", kP, kI, kD, kFF);
  }
  
  public void init(){
    _frontLeftMain = new CANSparkMax(RobotMap.frontLeftMain, MotorType.kBrushless);
    _frontLeftMain.setInverted(true);

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
  }

  public void setTank(double left, double right){
    _frontLeftMain.set(left);
    _backLeftMain.set(left);
    _frontRightMain.set(right);
    _backRightMain.set(right);
  }

  public void setMecanum(double x, double y, double rotation, double gyroAngle){
    drive.driveCartesian(x, y, rotation, gyroAngle);
  }

  public void setDrive(double setPoint){
    this.setSetpointRelative(setPoint / INCHES_PER_ROTATION);
  }

  public void driveToTarget(){
    _frontRightMain.set(_frontLeftMain.get());
    _backLeftMain.set(_frontLeftMain.get());
    _backRightMain.set(_frontRightMain.get());
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new OperateMecanumDrive());
  }

  @Override
  protected double returnPIDInput() {
    return _frontLeftMain.getEncoder().getPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    _frontLeftMain.pidWrite(output);
  }
}
