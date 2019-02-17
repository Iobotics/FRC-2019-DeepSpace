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
    //timeout time used for TalonSRX PID control
    public static final int timeoutMS = 20;

    public static final double wheelCirumference = 4 * Math.PI;

    public static final double rotationsToWheel = 48/11;
    //equal to Inches / Rotations, multiply with rotations to get inches, divide inches to get rotations
    public static final double rotationsToInches = rotationsToWheel * wheelCirumference;

    //Range of tolerance for if the loop is completed, measured in motor rotatins
    public static final double toleranceRange = 2;
}