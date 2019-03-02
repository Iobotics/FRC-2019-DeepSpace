/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX leftIntakeArm;
  TalonSRX rightIntakeArm;
  TalonSRX intake;

  public void init()
  {
      leftIntakeArm = new TalonSRX(RobotMap.leftIntake);
      leftIntakeArm.setInverted(false);
      leftIntakeArm.setNeutralMode(NeutralMode.Brake);

      rightIntakeArm = new TalonSRX(RobotMap.rightIntake);
      rightIntakeArm.setInverted(true);
      rightIntakeArm.setNeutralMode(NeutralMode.Brake);

      intake = new TalonSRX(RobotMap.centerIntake);
      
      
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
