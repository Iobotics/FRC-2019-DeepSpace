/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoAlighnment;

import javax.management.RuntimeErrorException;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CommandBase;

public class TurnToTarget extends CommandBase implements PIDOutput, PIDSource {

  private static PIDController _pid;

  private static double _target;
  private static boolean _isRocket;

  private static double _onTargetTime = Double.MAX_VALUE;
  private static boolean _onTarget = false;
  private static final double THRESHOLD = 5;

  //Target Angles for Targets

  public TurnToTarget(){
    this(false);
  }

  public TurnToTarget(boolean isRocket) {
    requires(drivetrain);
    requires(navSensor);
    requires(limelight);
    _isRocket = isRocket;
    _pid = new PIDController(drivetrain.getkPTurn(), drivetrain.getkITurn(), drivetrain.getkDTurn(), this, this); 
    _pid.setInputRange(0, 360);
    _pid.setOutputRange(-0.5, 0.5);
    _pid.setContinuous();
    _pid.setAbsoluteTolerance(THRESHOLD);
  }

  @Override
  protected void initialize() {

    if(navSensor.getAngle() <= 135 && navSensor.getAngle() > 45 && !_isRocket){
      _target = 90;
    }

   else  if(navSensor.getAngle() <= 315 && navSensor.getAngle() > 225 && !_isRocket){
    _target = 270;
    }

    else  if((navSensor.getAngle() <= 45 || navSensor.getAngle() > 315) && !_isRocket){
      _target = 0;
    }

    else  if(navSensor.getAngle() <= 225 && navSensor.getAngle() > 135 && !_isRocket){
      _target = 180;
    }

    else  if((navSensor.getAngle() <= 90 && navSensor.getAngle() > 0) && _isRocket){
      _target = 30;
    }

    else  if(navSensor.getAngle() <= 180 && navSensor.getAngle() > 90 && _isRocket){
      _target = 150;
    }

    else  if(navSensor.getAngle() <= 270 && navSensor.getAngle() > 180 && _isRocket){
      _target = 210;
    }

    else  if(navSensor.getAngle() <= 360 && navSensor.getAngle() > 270 && _isRocket){
      _target = 330;
    }

    _pid.reset();
    _pid.setSetpoint(_target);
    _pid.enable();
  }

  @Override
  protected void execute() {
    SmartDashboard.putNumber("Rotation",  navSensor.getAngle());
  }

  @Override
  protected boolean isFinished() {
    if(oi.getLeftStickX() > .2 || oi.getRightStickX() > .2 || oi.getLeftStickY() > .2 || oi.getRightStickY() > .2){
      return true;
    }
    if(!_pid.onTarget()){
      _onTarget = false;
      _onTargetTime = Double.MAX_VALUE;
      return false;
    }

    else if (_pid.onTarget() && !_onTarget){
      _onTarget = true;
      _onTargetTime = this.timeSinceInitialized() + 0.3;
      return false;
    }

    else if (_pid.onTarget() && _onTarget && this.timeSinceInitialized() <= _onTargetTime){
      return false;
    }

    return true;
  }

  @Override
  protected void end() {
    drivetrain.setTank(0, 0);
    _pid.disable();
  }

  @Override
  protected void interrupted() {
    this.end();
  }

  @Override
  public void pidWrite(double output) {
    drivetrain.setMecanum(0,0, output);
  }

  @Override
  public void setPIDSourceType(PIDSourceType pidSource) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public PIDSourceType getPIDSourceType() {
    return PIDSourceType.kDisplacement;
  }

  @Override
  public double pidGet() {
    return navSensor.getAngle();
  }
}
