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
  public static final int frontLeftMain = 10;
  public static final int frontRightMain = 9;
  public static final int backLeftMain = 1;
  public static final int backRightMain = 8;
  public static final int intakeLeft = 4;
  public static final int intakeRight = 7;

  public static final int zoneTwoFrontForward = 2;
  public static final int zoneTwoFrontReverse = 3;
  public static final int zoneTwoBackForward = 4;
  public static final int zoneTwoBackReverse = 5;
}
