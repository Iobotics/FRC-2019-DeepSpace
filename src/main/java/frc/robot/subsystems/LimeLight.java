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
import edu.wpi.first.wpilibj.command.Subsystem;

/** 
 * Add your docs here.
 */
public class LimeLight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  NetworkTable table; 
  NetworkTableInstance inst;
  //NetworkTableEntry tv;
  NetworkTableEntry tx;
  //NetworkTableEntry ta;
  //NetworkTableEntry ts;
  //NetworkTableEntry tl;
  NetworkTableEntry ty;

  double isDetected;
  double x = 0;
  double y;
  double area;
  double rotation;
  double latency;

  public void init()
  {
    table = NetworkTableInstance.getDefault().getTable("limelight");
        
        //tv = table.getEntry("tv");
        //ta = table.getEntry("ta");
        //ts = table.getEntry("ts");
        //tl = table.getEntry("tl");
        ty = table.getEntry("ty");
        tx = table.getEntry("tx");

        inst = NetworkTableInstance.getDefault();
  }

  public double getX()
  {
    return tx.getDouble(0.0);
  }

  public void debug()
  {
    //isDetected = tv.getDouble(0.0);
        //area = ta.getDouble(0.0);
        //rotation = ts.getDouble(0.0);
        //latency = tl.getDouble(0.0);
        //y = ty.getDouble(0.0);
        //distance = -height/Math.tan(Math.toRadians(ty.getDouble(0.0)));
        x = getX();

        
            //SmartDashboard.putNumber("speed", speed);
            //SmartDashboard.putNumber("x", x);
            //SmartDashboard.putNumber("area", area);
            //SmartDashboard.putNumber("isDetected", isDetected);
            //SmartDashboard.putNumber("Rotation of Target", rotation);
            //SmartDashboard.putNumber("latency", latency);
            //SmartDashboard.putNumber("y", y);
            //SmartDashboard.putBoolean("Ready to Launch", goodToShoot);
            //SmartDashboard.putNumber("Distance", distance);
            //SmartDashboard.putString("x direction", xDirection);
            //SmartDashboard.putString("If you should go forward", zDirection);
            //SmartDashboard.putString("Distance Sensing Mode", distanceMode);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
