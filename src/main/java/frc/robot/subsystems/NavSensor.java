/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.kauailabs.navx.frc.AHRS;

/**
 * Add your docs here.
 */
public class NavSensor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private AHRS _navSensor;
  
  @Override
  public void initDefaultCommand() {
    
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void init(){
    _navSensor = new AHRS(Port.kMXP);
  }
  public double getAngle(){
    return _navSensor.getAngle();
  }
  public void resetGyro(){
    _navSensor.reset();
  }

  public AHRS getSensor(){
    return _navSensor;
  }

  public boolean isCalibrating() {
    return _navSensor.isCalibrating();
  }
}
