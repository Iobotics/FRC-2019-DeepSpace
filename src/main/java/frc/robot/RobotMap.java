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
  public static final int frontLeftMain = 1;
  public static final int frontLeftSlave = 0;
  public static final int frontRightMain = 2;
  public static final int frontRightSlave = 3;
  public static final int backLeftMain = 6;
  public static final int backLeftSlave = 7;
  public static final int backRightMain = 4;
  public static final int backRightSlave = 5;
  public static final int intakeLeft = 8;
  public static final int intakeRight = 9;
}
