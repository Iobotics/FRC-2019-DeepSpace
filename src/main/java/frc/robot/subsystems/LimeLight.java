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
import frc.robot.commands.AutoAlighnment.LimelightDebug;

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
  private static NetworkTableEntry ta;
  private static NetworkTableEntry ta0;
  private static NetworkTableEntry ta1;
  private static NetworkTableEntry ta2;
  private static NetworkTableEntry tx0;
  private static NetworkTableEntry tx1;
  private static NetworkTableEntry tx2;
  private static NetworkTableEntry thor;
  private static NetworkTableEntry tvert;
  private static NetworkTableEntry ledMode;

  private static boolean isDetected;
  private static double x = 0;
  private static double y = 0;
  private static double latency;
  private static double area = 0;
  private static double area0 = 0;
  private static double area1 = 0;
  private static double area2 = 0;
  private static double x0 = 0;
  private static double x1 = 0;
  private static double x2 = 0;
  private static double aspectRatio = 0;
  private static double last = 0;

  private static Servo servo;


  public void init()
  {
    table = NetworkTableInstance.getDefault().getTable("limelight");
        
    //tv = table.getEntry("tv");
    //tl = table.getEntry("tl");
    ty = table.getEntry("ty");
    tx = table.getEntry("tx");
    ta = table.getEntry("ta");
    ta0 = table.getEntry("ta0");
    ta1 = table.getEntry("ta1");
    ta2 = table.getEntry("ta2");
    tx0 = table.getEntry("tx0");
    tx1 = table.getEntry("tx1");
    tx2 = table.getEntry("tx2");
    thor = table.getEntry("thor");
    tvert = table.getEntry("tvert");

    ledMode = table.getEntry("ledMode");

    inst = NetworkTableInstance.getDefault();
    //setLEDOn(false);
    setLEDOn(true);
  }

  public double getX()
  {
    return tx.getDouble(0.0);
  }

  public double getWidthHeightRatio() // Parallel 2.44
  {
    return thor.getDouble(0.0)/tvert.getDouble(0.0);
  }

  public double getDistance()
  {
    double area = ta.getDouble(0.0);
    double distance = 75.18 * Math.pow(area, -.4534);
    return distance;
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
    area0 = ta0.getDouble(0.0);
    area1 = ta1.getDouble(0.0);
    area2 = ta2.getDouble(0.0);
    x0 = tx0.getDouble(0.0);
    x1 = tx1.getDouble(0.0);
    x2 = tx2.getDouble(0.0);

    SmartDashboard.putNumber("x", x);
    //SmartDashboard.putNumber("latency", latency);
    SmartDashboard.putNumber("y", y);
    SmartDashboard.putNumber("area0", area0);
    SmartDashboard.putNumber("area1", area1);
    SmartDashboard.putNumber("area2", area2);
    SmartDashboard.putNumber("tx0", x0);
    SmartDashboard.putNumber("tx1", x1);
    SmartDashboard.putNumber("tx2", x2);
    //System.out.println(this.getWidthHeightRatio() - last);
    //SmartDashboard.putNumber("width height ratio", this.getWidthHeightRatio());
    //last = this.getWidthHeightRatio();
    
    SmartDashboard.putNumber("distance", this.getDistance());
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new LimelightDebug());
  }
}
