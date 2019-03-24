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
import frc.robot.commands.Drivetrain.OperateMecanumDrive;

/**
 * Drivetrain
 * Written by Noah Taniguchi
 * The drivetrain runs using mecanum or tank drive.
 * The motors used in the drivetrain are spark maxes.
 */
public class Drivetrain extends Subsystem {

  private CANSparkMax frontLeftMain;
  private CANSparkMax frontRightMain;
  private CANSparkMax backLeftMain;
  private CANSparkMax backRightMain;
  private CANPIDController frontLeftCanController;
  private CANPIDController frontRightCanController;
  private CANPIDController backLeftCanController;
  private CANPIDController backRightCanController;
  private CANEncoder frontLeftCanEncoder;
  private CANEncoder frontRightCanEncoder;
  private CANEncoder backLeftCanEncoder;
  private CANEncoder backRightCanEncoder;

  private static double kPDrive =  5e-5;
  private static double kIDrive = 1e-6;
  private static double kIZoneDrive = 0;
  private static double kDDrive = 0;
  private static double kFFDrive = 0.000156;
  private static double kForwardRR = 1;
  private static double kStrafeRR = 1;
  private static double kIsMoreStrafe = .5;


  private double kPTurn = 0.005;
  private double kITurn = 0;
  private double kDTurn = 0;

  private double maxPower = 1;

  private MecanumDrive drive;
  
  //Should be called in the robot init
  public void init(){

    frontLeftMain = new CANSparkMax(RobotMap.frontLeftMain, MotorType.kBrushless);
    frontLeftMain.setInverted(true);
    //frontLeftMain.setSmartCurrentLimit(65,55);
    //frontLeftMain.setSmartCurrentLimit(40);

    frontRightMain =  new CANSparkMax(RobotMap.frontRightMain, MotorType.kBrushless);
    frontRightMain.setInverted(true);
    //frontRightMain.setSmartCurrentLimit(65,55);
    //frontRightMain.setSmartCurrentLimit(40);

    backLeftMain = new CANSparkMax(RobotMap.backLeftMain, MotorType.kBrushless);
    backLeftMain.setInverted(true);
    //backLeftMain.setSmartCurrentLimit(65,55);
    //backLeftMain.setSmartCurrentLimit(40);
    
    backRightMain = new CANSparkMax(RobotMap.backRightMain, MotorType.kBrushless);
    backRightMain.setInverted(true);
    //backRightMain.setSmartCurrentLimit(65,55);
    //backRightMain.setSmartCurrentLimit(40);

    drive = new MecanumDrive(
      frontLeftMain, 
      backLeftMain, 
      frontRightMain,   
      backRightMain
    );

    drive.setSafetyEnabled(false);


    frontLeftMain.setIdleMode(IdleMode.kBrake);
    frontRightMain.setIdleMode(IdleMode.kBrake);
    backLeftMain.setIdleMode(IdleMode.kBrake);
    backRightMain.setIdleMode(IdleMode.kBrake);

    frontLeftCanEncoder = frontLeftMain.getEncoder();
    frontRightCanEncoder = frontRightMain.getEncoder();
    backLeftCanEncoder = backLeftMain.getEncoder();
    backRightCanEncoder = backRightMain.getEncoder();

    frontLeftCanController = frontLeftMain.getPIDController();
    frontRightCanController = frontRightMain.getPIDController();
    backRightCanController = backRightMain.getPIDController();
    backLeftCanController = backLeftMain.getPIDController();
    
    frontLeftCanController.setP(kPDrive);
    frontLeftCanController.setI(kIDrive);
    frontLeftCanController.setIZone(kIZoneDrive);
    frontLeftCanController.setD(kDDrive);
    frontLeftCanController.setFF(kFFDrive);
    frontLeftCanController.setOutputRange(-1, 1);
    frontLeftCanController.setSmartMotionMaxVelocity(2000, 0);
    frontLeftCanController.setSmartMotionMaxAccel(1500, 0);

    frontRightCanController.setP(kPDrive);
    frontRightCanController.setI(kIDrive);
    frontRightCanController.setIZone(kIZoneDrive);
    frontRightCanController.setD(kDDrive);
    frontRightCanController.setFF(kFFDrive);
    frontRightCanController.setOutputRange(-1, 1);
    frontRightCanController.setSmartMotionMaxVelocity(2000, 0);
    frontRightCanController.setSmartMotionMaxAccel(1500, 0);

    backRightCanController.setP(kPDrive);
    backRightCanController.setI(kIDrive);
    backRightCanController.setIZone(kIZoneDrive);
    backRightCanController.setD(kDDrive);
    backRightCanController.setFF(kFFDrive);
    backRightCanController.setOutputRange(-1, 1);
    backRightCanController.setSmartMotionMaxVelocity(2000, 0);
    backRightCanController.setSmartMotionMaxAccel(1500, 0);

    backLeftCanController.setP(kPDrive);
    backLeftCanController.setI(kIDrive);
    backLeftCanController.setIZone(kIZoneDrive);
    backLeftCanController.setD(kDDrive);
    backLeftCanController.setFF(kFFDrive);
    backLeftCanController.setOutputRange(-1, 1);
    backLeftCanController.setSmartMotionMaxVelocity(2000, 0);
    backLeftCanController.setSmartMotionMaxAccel(1500, 0);
    
  }

  //Drive to position using smart motion profiling
  public void setSetPoint(double targetFrontLeft, double targetFrontRight, double targetBackLeft, double targetBackRight){

    frontLeftCanController.setReference(targetFrontLeft, ControlType.kSmartMotion);
    frontRightCanController.setReference(targetFrontRight, ControlType.kSmartMotion);
    backLeftCanController.setReference(targetBackLeft, ControlType.kSmartMotion);
    backRightCanController.setReference(targetBackRight, ControlType.kSmartMotion);

  }

  //The following four functions return their respective motor's encoder value
  public double getFrontLeftPosition(){

    return frontLeftCanEncoder.getPosition();

  }

  public double getFrontRightPosition(){

    return frontRightCanEncoder.getPosition();
  
  }

  public double getBackLeftPosition(){

    return backLeftCanEncoder.getPosition();
  
  }

  public double getBackRightPosition(){

    return backRightCanEncoder.getPosition();
  
  }

  //Sets motor output as a value from -1 (full power backwards) to 1 (full power forwards)
  public void setTank(double left, double right) {
    frontLeftMain.set(left);
    backLeftMain.set(left);
    frontRightMain.set(right);
    backRightMain.set(right);
  }

  public double getPower()
  {
    return backLeftMain.get();
  }

  //Sets the motors to run according to a mecanum drivetrain
  public void setMecanum(double x, double y, double rotation) {
    if(Math.abs(x) - Math.abs(this.getPower()) >= 0 || Math.abs(y) - Math.abs(this.getPower()) >= 0 || Math.abs(rotation) - Math.abs(this.getPower()) >= 0) //The speed is increasing
    {
      if(Math.abs(x) >= kIsMoreStrafe)
      {
        frontLeftMain.setOpenLoopRampRate(kStrafeRR);
        frontRightMain.setOpenLoopRampRate(kStrafeRR);
        backLeftMain.setOpenLoopRampRate(kStrafeRR);
        backRightMain.setOpenLoopRampRate(kStrafeRR);
      }
      else
      {
        frontLeftMain.setOpenLoopRampRate(kForwardRR);
        frontRightMain.setOpenLoopRampRate(kForwardRR);
        backLeftMain.setOpenLoopRampRate(kForwardRR);
        backRightMain.setOpenLoopRampRate(kForwardRR);
      }
    }
    else
    {
      frontLeftMain.setOpenLoopRampRate(0);
      frontRightMain.setOpenLoopRampRate(0);
      backLeftMain.setOpenLoopRampRate(0);
      backRightMain.setOpenLoopRampRate(0);
    }
    drive.driveCartesian(x * maxPower, y * maxPower, rotation * maxPower);
  }

  public void setMecanum(double x, double y, double rotation, double gyroAngle) {
    drive.driveCartesian(x * maxPower, y * maxPower, rotation * maxPower, gyroAngle);
  }

  //Call the following commands to change or view the PID values
  public double getP(){
    return kPDrive;
  }

  public void setP(double p){
    kPDrive = p;
  }
  
  public double getI(){
    return kIDrive;
  }

  public void setI(double i){
    kIDrive = i;
  }

  public double getD(){
    return kDDrive;
  }

  public void setD(double d){
    kDDrive = d;
  }

  public double getFF(){
    return kFFDrive;
  }

  public void setFF(double ff){
    kFFDrive = ff;
  }

  public double getIzone(){
    return kIZoneDrive;
  }

  public void setIzone(double Izone){
    kIZoneDrive = Izone;
  }

  public double getkPTurn() {
    return kPTurn;
  }

  public double getkITurn() {
    return kITurn;
  }

  public double getkDTurn() {
    return kDTurn;
  }

  //Returns the temperature of the back right motor
  public double getTemperature(){
    return backRightMain.getMotorTemperature();
  }

  public void toggleSlow(){
    this.maxPower = maxPower == 1 ? .3 : 1;
  }

  
  @Override
  public void initDefaultCommand() {
   // setDefaultCommand(new OperateMecanumDrive());
   setDefaultCommand(new OperateMecanumDrive());
  }
}