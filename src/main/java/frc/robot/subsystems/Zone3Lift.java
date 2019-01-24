/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.OperateZone3Lift;

/**
 * Add your docs here.
 */
public class Zone3Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX left;
  TalonSRX left2;
  TalonSRX right;
  TalonSRX right2;

  DoubleSolenoid top;
  DoubleSolenoid bottom;

  public void init()
  {
    left = new TalonSRX(RobotMap.leftZone3);
    left2 = new TalonSRX(RobotMap.leftZone3_2);
    right = new TalonSRX(RobotMap.rightZone3);
    right2 = new TalonSRX(RobotMap.rightZone3_2);

    top = new DoubleSolenoid(RobotMap.topForwardValve, RobotMap.topReverseValve);
    bottom = new DoubleSolenoid(RobotMap.bottomFowardValve, RobotMap.bottomReverseValve);

    left.setInverted(true);
    left2.setInverted(true);
    left.setNeutralMode(NeutralMode.Brake);
    left.setNeutralMode(NeutralMode.Brake);

    right.follow(left);
    right2.follow(left);
    left2.follow(left);
  }

  public void liftPower(double power)
  {
    left.set(ControlMode.PercentOutput, power);
  }

  public void grab()
  {
    top.set(Value.kForward);
    bottom.set(Value.kForward);
  }

  public void release()
  {
    top.set(Value.kReverse);
    bottom.set(Value.kReverse);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new OperateZone3Lift());
  }
}
