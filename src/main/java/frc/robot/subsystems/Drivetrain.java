/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.CameraDrive;
import frc.robot.commands.OperateMecanumDrive;
import frc.util.TalonWrapper;

public class Drivetrain extends Subsystem {

  private boolean isSwitched = false;

  private TalonSRX _frontLeftMain;
  private TalonSRX _frontLeftSlave;

  private TalonSRX _frontRightMain;
  private TalonSRX _frontRightSlave;

  private TalonSRX _backLeftMain;
  private TalonSRX _backLeftSlave;

  private TalonSRX _backRightMain;
  private TalonSRX _backRightSlave;

  private DoubleSolenoid _solenoid;

  private MecanumDrive drive;
  
  public void init(){
    _frontLeftMain = new TalonSRX(RobotMap.frontLeftMain);
    _frontLeftSlave = new TalonSRX(RobotMap.frontLeftSlave);
    _frontLeftSlave.follow(_frontLeftMain);
    _frontLeftMain.setInverted(true);
    _frontLeftSlave.setInverted(true);

    _frontRightMain =  new TalonSRX(RobotMap.frontRightMain);
    _frontRightSlave = new TalonSRX(RobotMap.frontRightSlave);
    _frontRightSlave.follow(_frontRightMain);
    _frontRightMain.setInverted(true);
    _frontRightSlave.setInverted(true);
    


    _backLeftMain = new TalonSRX(RobotMap.backLeftMain);
    _backLeftSlave = new TalonSRX(RobotMap.backLeftSlave);
    _backLeftSlave.follow(_backLeftMain);
    _backLeftMain.setInverted(true);
    _backLeftSlave.setInverted(true);
    
    _backRightMain = new TalonSRX(RobotMap.backRightMain);
    _backRightSlave = new TalonSRX(RobotMap.backRightSlave);
    _backRightSlave.follow(_backRightMain);
    _backRightMain.setInverted(true);
    _backRightSlave.setInverted(true);
   

    drive = new MecanumDrive(
      new TalonWrapper(_frontLeftMain), 
      new TalonWrapper(_backLeftMain), 
      new TalonWrapper(_frontRightMain),
      new TalonWrapper(_backRightMain)
    );

    _solenoid = new DoubleSolenoid(0, 1);
  }

  public void setTank(double left, double right){
    _frontLeftMain.set(ControlMode.PercentOutput, left);
    _backLeftMain.set(ControlMode.PercentOutput, left);
    _frontRightMain.set(ControlMode.PercentOutput, right);
    _backRightMain.set(ControlMode.PercentOutput, right);
  }

  public void setMecanum(double x, double y, double rotation){
    drive.driveCartesian(x, y, rotation);
  } 

  public void switchSolenoid(){
    if (isSwitched){
      _solenoid.set(Value.kReverse);
      isSwitched = false;
    }
    else{
      _solenoid.set(Value.kForward);
      isSwitched = true;
    }
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new OperateMecanumDrive());
    //setDefaultCommand(new CameraDrive());
  }
}
