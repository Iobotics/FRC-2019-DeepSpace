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

  private PIDController _pid;

  private double _target;

  private double _onTargetTime = Double.MAX_VALUE;
  private boolean _onTarget = false;

  //Target Angles for Targets



  public TurnToTarget() {
    requires(drivetrain);
    requires(navSensor);
    requires(limelight);
    _pid = new PIDController(drivetrain.getkPTurn(), drivetrain.getkITurn(), drivetrain.getkDTurn(), this, this); 
    _pid.setInputRange(-180, 180);
    _pid.setOutputRange(-0.5, 0.5);
    _pid.setContinuous();
    _pid.setAbsoluteTolerance(5);
  }

  @Override
  protected void initialize() {

    if( (90 - 45) < navSensor.getAngle()
     && navSensor.getAngle() < (90 + 45) /*&& limelight.isRocket*/)
    {
      _target = 90;
    }

    else if( (-90 - 45) < navSensor.getAngle()
     && navSensor.getAngle() < (-90 + 45) /*limelight.isRocket*/)
    {
      _target = -90;
    }

    else if( (30 - 15) < navSensor.getAngle()
     && navSensor.getAngle() < (30 + 15) )
    {
      _target = 30;
    }

    else if( (-30 - 15) < navSensor.getAngle()
    && navSensor.getAngle() < (-30 + 15) )
    {
     _target = -30;
    }  

    else if( (0 - 15) < navSensor.getAngle()
    && navSensor.getAngle() < (0 + 15) )
    {
     _target = 0;
    }

    else if( (150 - 15) < navSensor.getAngle()
    && navSensor.getAngle() < (150 + 15) )
    {
     _target = 150;
    }  

    else if( (-150 - 15) < navSensor.getAngle()
    && navSensor.getAngle() < (-150 + 15) )
    {
     _target = -150;
    }  

    else if( (-165) < navSensor.getAngle()
    && navSensor.getAngle() < (165) )
    {
     _target = 180;
    }  
    _pid.reset();
    _pid.setSetpoint(_target);
    _pid.enable();
  }

  @Override
  protected void execute() {
    SmartDashboard.putNumber("Rotation", navSensor.getAngle());
  }

  @Override
  protected boolean isFinished() {
    if(!_pid.onTarget()){
      _onTarget = false;
      _onTargetTime = Double.MAX_VALUE;
      return false;
    }

    else if (_pid.onTarget() && !_onTarget){
      _onTarget = true;
      _onTargetTime = this.timeSinceInitialized() + 1;
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
    return navSensor.getAngle() % 180;
}
}
