/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ResetGyro;

/**
 * Navsensor
 */
public class NavSensor extends Subsystem {

  private AHRS _navSensor;
  
  public void init(){
    _navSensor = new AHRS(Port.kMXP);
  }

  public double getAngle(){
    return _navSensor.getAngle();
  }

  public PIDSourceType getPidSourceType(){
    return _navSensor.getPIDSourceType();
  }

  public double getRoll(){
    return _navSensor.getRoll();
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

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ResetGyro());
  }
  
}
