/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Class to Handle values which are neededd multiple places and never change
 */
public class Constants {
  // timeout time used for TalonSRX PID control
  public static final int timeoutMS = 20;

  public static final double wheelCirumference = 4 * Math.PI;

  public static final double rotationsToWheel = 48/11;
  // equal to Inches / Rotations, divide from rotations to get inches, multiply by inches to get rotations
  public static final double rotationsToInches = wheelCirumference / rotationsToWheel;

  // Range of tolerance for if the loop is completed, measured in motor rotating
  public static final double toleranceRange = 2;
  
  // Pot value of the shooter Arm when at Horizontal, used to calculate the angle of the arm
  public static final int shooterArmCenter = 308; // Before 985

  public static final int shooterHome = shooterArmCenter + 57;

  public static final int shooterIntake = shooterArmCenter + 49;

  public static final int cargoShipAngle = shooterArmCenter + 12; // Before + 18// Before +2

  public static final int firstLevelAngle = shooterArmCenter - 10;

  // Intake Arm Position

  public static final int intakeArmHome = -329; // Before -659

  public static final int intakeArmShooter = -265; // Before -620

  public static final int intakeArmIntake =  -265; // Before -504

  public static final int intakeArmOut = -265;

  public static final int intakeArmForwardLimit =  -227; // more positive is forward
  public static final int intakeArmReverseLimit =  Constants.intakeArmHome + 2;

  public static final double distanceThreshold = 0;
  public static final double rotateThreshold = 0;
  public static final double strafeThreshold = 0;

}
