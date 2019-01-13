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
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.OperateMecanumDrive;
import frc.robot.commands.OperateTankDrive;
import frc.util.SparkWrapper;
//import frc.util.Clipper;
import frc.util.SparkWrapper;

public class Drivetrain extends Subsystem {

  private CANSparkMax _frontLeftMain;
  //private TalonSRX _frontLeftSlave;

  private CANSparkMax _frontRightMain;
  //private TalonSRX _frontRightSlave;

  private CANSparkMax _backLeftMain;
  //private TalonSRX _backLeftSlave;

  private CANSparkMax _backRightMain;
  //private TalonSRX _backRightSlave;

  private MecanumDrive drive;
  
  public void init(){
    _frontLeftMain = new CANSparkMax(RobotMap.frontLeftMain, MotorType.kBrushless);
    //_frontLeftSlave = new TalonSRX(RobotMap.frontLeftSlave);
    //_frontLeftSlave.follow(_frontLeftMain);
    _frontLeftMain.setInverted(true);
    //_frontLeftSlave.setInverted(true);

    _frontRightMain =  new CANSparkMax(RobotMap.frontRightMain, MotorType.kBrushless);
    //_frontRightSlave = new TalonSRX(RobotMap.frontRightSlave);
    //_frontRightSlave.follow(_frontRightMain);
    _frontRightMain.setInverted(true);
    //_frontRightSlave.setInverted(true);
    


    _backLeftMain = new CANSparkMax(RobotMap.backLeftMain, MotorType.kBrushless);
    //_backLeftSlave = new TalonSRX(RobotMap.backLeftSlave);
    //_backLeftSlave.follow(_backLeftMain);
    _backLeftMain.setInverted(true);
    //_backLeftSlave.setInverted(true);
    
    _backRightMain = new CANSparkMax(RobotMap.backRightMain, MotorType.kBrushless);
    //_backRightSlave = new TalonSRX(RobotMap.backRightSlave);
    //_backRightSlave.follow(_backRightMain);
    _backRightMain.setInverted(true);
    //_backRightSlave.setInverted(true);
   

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

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new OperateMecanumDrive());
  }
}
