/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** 
 * Add your docs here.
 */
public class LimeLight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static NetworkTable table; 
  private static NetworkTableInstance inst;
  //NetworkTableEntry tv;
  private static NetworkTableEntry tx;
  //NetworkTableEntry tl;
  private static NetworkTableEntry ty;
  private static NetworkTableEntry ledMode;

  private static boolean isDetected;
  private static double x = 0;
  private static double y = 0;
  private static double latency;
  
  private static final double HEIGHTLIMELIGHT = 34.75; // Units: Inches, 
  private static final double VISIONTAPEAREA = 0;

  private static Servo servo;


  public void init()
  {
    table = NetworkTableInstance.getDefault().getTable("limelight");
        
    //tv = table.getEntry("tv");
    //tl = table.getEntry("tl");
    ty = table.getEntry("ty");
    tx = table.getEntry("tx");
    ledMode = table.getEntry("ledMode");

    inst = NetworkTableInstance.getDefault();
    setLEDOn(false);
  }

  public double getX()
  {
    return tx.getDouble(0.0);
  }

  public void setLEDOn(boolean ledOn)
  {
    if(ledOn)
    {
      ledMode.setNumber(3); // Led forced on
    }
    else if(!ledOn)
    {
      ledMode.setNumber(1); // Led forced off
    }
  }

  public void debug()
  {
    //isDetected = tv.getDouble(0.0);
    //latency = tl.getDouble(0.0);
    y = ty.getDouble(0.0);
    x = getX();

    SmartDashboard.putNumber("x", x);
    //SmartDashboard.putNumber("latency", latency);
    SmartDashboard.putNumber("y", y);
  }

  @Override
  public void initDefaultCommand() {

  }
}
