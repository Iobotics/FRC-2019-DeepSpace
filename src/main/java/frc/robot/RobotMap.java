/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static final int proximitySensor = 0;
<<<<<<< HEAD
=======
  public static final int intakePot = 0;
>>>>>>> intakeArm

  public static final int hookSolenoidForward = 6;
  public static final int hookSolenoidReverse = 7;
  public static final int extendHatchForward = -1;
  public static final int extendHatchReverse = -1;

  public static final int zoneTwoBackForward = 4;
  public static final int zoneTwoBackReverse = 5;
<<<<<<< HEAD
=======
  public static final int intakeExtender = 0;
>>>>>>> intakeArm

  public static final int frontLeftMain = 10;
  public static final int frontRightMain = 9;
  public static final int backLeftMain = 1;
  public static final int backRightMain = 8;

  public static final int chassisIntake = 6;
<<<<<<< HEAD
  public static final int rightIntakeArm = 13;
  public static final int leftIntakeArm = 14;
=======
  public static final int rightIntakeArm = 12;
  public static final int leftIntakeArm = 11;
>>>>>>> intakeArm

  public static final int leftLift = 2;
  public static final int rightLift = 5;
  public static final int leftLiftSlave = 13;
  public static final int rightLiftSlave = 14;

  public static final int rightShooter = 7;
  public static final int leftShooter = 4;
  public static final int shooterArm = 3;

  public static final int ledStrip = 4;
}
