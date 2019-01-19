/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.OperateMecanumDrive;
import frc.util.SparkWrapper;

public class Drivetrain extends Subsystem {

  private CANSparkMax _frontLeftMain;
  private CANSparkMax _frontRightMain;
  private CANSparkMax _backLeftMain;
  private CANSparkMax _backRightMain;
  private CANPIDController _canController;

  private static double _kPDrive = 0.5;
  private static double _kIDrive = 0;
  private static double _kIZoneDrive = 0;
  private static double _kDDrive = 0;
  private static double _kFFDrive = 0;

  private double _kPTurn = 0;
  private double _kITurn = 0;
  private double _kDTurn = 0;

  public static final double INCHES_PER_ROTATION = 4 * Math.PI;

  private MecanumDrive _drive;

  public Drivetrain(){

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
   
    _drive = new MecanumDrive(
      _frontLeftMain, 
      _backLeftMain, 
      _frontRightMain,
      _backRightMain
    );

    _frontLeftMain.setIdleMode(IdleMode.kBrake);
    _frontRightMain.setIdleMode(IdleMode.kBrake);
    _backLeftMain.setIdleMode(IdleMode.kBrake);
    _backRightMain.setIdleMode(IdleMode.kBrake);

    _canController = _frontLeftMain.getPIDController();
    _canController.setP(_kPDrive);
    _canController.setI(_kIDrive);
    _canController.setIZone(_kIZoneDrive);
    _canController.setD(_kDDrive);
    _canController.setFF(_kFFDrive);
  }

  public void setSetPoint(double target){
    _canController.setReference(target / INCHES_PER_ROTATION, ControlType.kPosition);
  }

 

  public void setTank(double left, double right){
    _frontLeftMain.set(left);
    _backLeftMain.set(left);
    _frontRightMain.set(right);
    _backRightMain.set(right);
  }

  public void setMecanum(double x, double y, double rotation, double gyroAngle){
    _drive.driveCartesian(x, y, rotation, gyroAngle);
  }

  public void driveToTarget(){
    _frontRightMain.set(_frontLeftMain.get());
    _backLeftMain.set(_frontLeftMain.get());
    _backRightMain.set(_frontRightMain.get());
  }

  public double getP(){
    return _kPDrive;
  }

  public void setP(double p){
    _kPDrive = p;
  }
  
  public double getI(){
    return _kIDrive;
  }

  public void setI(double i){
    _kIDrive = i;
  }

  public double getD(){
    return _kDDrive;
  }

  public void setD(double d){
    _kDDrive = d;
  }

  public double getFF(){
    return _kFFDrive;
  }

  public void setFF(double ff){
    _kFFDrive = ff;
  }

  public double getIzone(){
    return _kIZoneDrive;
  }

  public void setIzone(double Izone){
    _kIZoneDrive = Izone;
  }

  public double get_kPTurn() {
    return _kPTurn;
  }

  public double get_kITurn() {
    return _kITurn;
  }

  public double get_kDTurn() {
    return _kDTurn;
  }
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new OperateMecanumDrive());
  }
}
