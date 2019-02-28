/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.Drivetrain.OperateControllerDrive;
import frc.robot.commands.Drivetrain.OperateMecanumDrive;

/**
 * Drivetrain
 * Written by Noah Taniguchi
 * The drivetrain runs using mecanum or tank drive.
 * The motors used in the drivetrain are spark maxes.
 */
public class Drivetrain extends Subsystem {

  private CANSparkMax _frontLeftMain;
  private CANSparkMax _frontRightMain;
  private CANSparkMax _backLeftMain;
  private CANSparkMax _backRightMain;
  private CANPIDController _frontLeftCanController;
  private CANPIDController _frontRightCanController;
  private CANPIDController _backLeftCanController;
  private CANPIDController _backRightCanController;
  private CANEncoder _frontLeftCanEncoder;
  private CANEncoder _frontRightCanEncoder;
  private CANEncoder _backLeftCanEncoder;
  private CANEncoder _backRightCanEncoder;

  private static double _kPDrive =  5e-5;
  private static double _kIDrive = 1e-6;
  private static double _kIZoneDrive = 0;
  private static double _kDDrive = 0;
  private static double _kFFDrive = 0.000156;

  private double _kPTurn = 0.005;
  private double _kITurn = 0;
  private double _kDTurn = 0;

  private double maxPower = 1;

  private MecanumDrive _drive;
  
  //Should be called in the robot init
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

    _drive.setSafetyEnabled(false);


    _frontLeftMain.setIdleMode(IdleMode.kBrake);
    _frontRightMain.setIdleMode(IdleMode.kBrake);
    _backLeftMain.setIdleMode(IdleMode.kBrake);
    _backRightMain.setIdleMode(IdleMode.kBrake);

    _frontLeftCanEncoder = _frontLeftMain.getEncoder();
    _frontRightCanEncoder = _frontRightMain.getEncoder();
    _backLeftCanEncoder = _backLeftMain.getEncoder();
    _backRightCanEncoder = _backRightMain.getEncoder();

    _frontLeftCanController = _frontLeftMain.getPIDController();
    _frontRightCanController = _frontRightMain.getPIDController();
    _backRightCanController = _backRightMain.getPIDController();
    _backLeftCanController = _backLeftMain.getPIDController();
    
    _frontLeftCanController.setP(_kPDrive);
    _frontLeftCanController.setI(_kIDrive);
    _frontLeftCanController.setIZone(_kIZoneDrive);
    _frontLeftCanController.setD(_kDDrive);
    _frontLeftCanController.setFF(_kFFDrive);
    _frontLeftCanController.setOutputRange(-1, 1);
    _frontLeftCanController.setSmartMotionMaxVelocity(2000, 0);
    _frontLeftCanController.setSmartMotionMaxAccel(1500, 0);

    _frontRightCanController.setP(_kPDrive);
    _frontRightCanController.setI(_kIDrive);
    _frontRightCanController.setIZone(_kIZoneDrive);
    _frontRightCanController.setD(_kDDrive);
    _frontRightCanController.setFF(_kFFDrive);
    _frontRightCanController.setOutputRange(-1, 1);
    _frontRightCanController.setSmartMotionMaxVelocity(2000, 0);
    _frontRightCanController.setSmartMotionMaxAccel(1500, 0);

    _backRightCanController.setP(_kPDrive);
    _backRightCanController.setI(_kIDrive);
    _backRightCanController.setIZone(_kIZoneDrive);
    _backRightCanController.setD(_kDDrive);
    _backRightCanController.setFF(_kFFDrive);
    _backRightCanController.setOutputRange(-1, 1);
    _backRightCanController.setSmartMotionMaxVelocity(2000, 0);
    _backRightCanController.setSmartMotionMaxAccel(1500, 0);

    _backLeftCanController.setP(_kPDrive);
    _backLeftCanController.setI(_kIDrive);
    _backLeftCanController.setIZone(_kIZoneDrive);
    _backLeftCanController.setD(_kDDrive);
    _backLeftCanController.setFF(_kFFDrive);
    _backLeftCanController.setOutputRange(-1, 1);
    _backLeftCanController.setSmartMotionMaxVelocity(2000, 0);
    _backLeftCanController.setSmartMotionMaxAccel(1500, 0);
    
  }

  //Drive to position using smart motion profiling
  public void setSetPoint(double targetFrontLeft, double targetFrontRight, double targetBackLeft, double targetBackRight){

    _frontLeftCanController.setReference(targetFrontLeft, ControlType.kSmartMotion);
    _frontRightCanController.setReference(targetFrontRight, ControlType.kSmartMotion);
    _backLeftCanController.setReference(targetBackLeft, ControlType.kSmartMotion);
    _backRightCanController.setReference(targetBackRight, ControlType.kSmartMotion);

  }

  //The following four functions return their respective motor's encoder value
  public double getFrontLeftPosition(){

    return _frontLeftCanEncoder.getPosition();

  }

  public double getFrontRightPosition(){

    return _frontRightCanEncoder.getPosition();
  
  }

  public double getBackLeftPosition(){

    return _backLeftCanEncoder.getPosition();
  
  }

  public double getBackRightPosition(){

    return _backRightCanEncoder.getPosition();
  
  }

  //Sets motor output as a value from -1 (full power backwards) to 1 (full power forwards)
  public void setTank(double left, double right) {
    _frontLeftMain.set(left);
    _backLeftMain.set(left);
    _frontRightMain.set(right);
    _backRightMain.set(right);
  }

  //Sets the motors to run according to a mecanum drivetrain
  public void setMecanum(double x, double y, double rotation) {
    _drive.driveCartesian(x * maxPower, y * maxPower, rotation * maxPower);
  }

  public void setMecanum(double x, double y, double rotation, double gyroAngle) {
    _drive.driveCartesian(x * maxPower, y * maxPower, rotation * maxPower, gyroAngle);
  }

  //Call the following commands to change or view the PID values
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

  //Returns the temperature of the back right motor
  public double getTemperature(){
    return _backRightMain.getMotorTemperature();
  }

  public void toggleSlow(){
    this.maxPower = maxPower == 1 ? .3 : 1;
  }

  
  @Override
  public void initDefaultCommand() {
   // setDefaultCommand(new OperateMecanumDrive());
   setDefaultCommand(new OperateControllerDrive());
  }
}