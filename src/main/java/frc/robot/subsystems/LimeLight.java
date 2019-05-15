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

  private  NetworkTable table; 
  private  NetworkTableInstance inst;
  private  NetworkTableEntry tv;
  private  NetworkTableEntry tx;
  private  NetworkTableEntry ty;
  private  NetworkTableEntry ta;
  private  NetworkTableEntry ledMode;

  private  boolean isDetected;
  private  double x = 0;
  private  double y = 0;
  private  double area = 0;

  private  final double VERTICALTHRESHOLD = 2; // degrees


  public void init()
  {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    //table.getInstance().startClientTeam(2438);
        
    tv = table.getEntry("tv");
    ty = table.getEntry("ty");
    tx = table.getEntry("tx");
    ta = table.getEntry("ta");

    ledMode = table.getEntry("ledMode");

    inst = NetworkTableInstance.getDefault();

    //setLEDOn(false);
  }

  public boolean isTargetDetected()
  {
    if(tv.getDouble(0.0) == 1)
    {
      return true;
    }
    return false;
  }

  public double getX()
  { //crosshair center to center of target to x-axis  
    return tx.getDouble(0.0);
  }

  public double getY()
  { //crosshair center to center of target to y-axis
    return ty.getDouble(0.0);
  }

  public boolean isRocketShip()
  {
    if(Math.abs(getY()) < VERTICALTHRESHOLD)
    {
      return true;
    }
    return false;
  }

  public double getDistance()
  {
    double area = ta.getDouble(0.0);
    double distance = 75.18 * Math.pow(area, -.4534); 
    //Function relating distance to the area percentage of the target
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

  public void setLEDOn()
  {
    ledMode.setNumber(3.0);
  }

  public void setLEDOff()
  {
    ledMode.setNumber(1.0);
  }

  public void setLEDDefault()
  {
    ledMode.setNumber(0);
  }

  public void debug()
  {
    //isDetected = tv.getDouble(0.0);
    /*y = ty.getDouble(0.0);
    x = getX();

    //SmartDashboard.putNumber("x", x);
    //SmartDashboard.putNumber("y", y);*/
    
    SmartDashboard.putNumber("distance", this.getDistance());
    //SmartDashboard.putBoolean("onRocketCargo", this.isRocketShip());
  }

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new LimelightDebug());
  }
}
