/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDBase;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;

public class AutoTurn extends CommandBase implements PIDOutput {

  private PIDController _pid;

  private double _kP = 0;
  private double _kI = 0;
  private double _kD = 0;

  private double _degrees;

  private double _onTargetTime = Double.MAX_VALUE;
  private boolean _onTarget = false;

  public AutoTurn(double _degrees) {
    requires(drivetrain);
    requires(navSensor);
    _pid = new PIDController(_kP, _kI, _kD, (PIDSource) navSensor, this);
    _pid.setContinuous();
    this._degrees = _degrees;
  }

  @Override
  protected void initialize() {
    _pid.setSetpoint(_degrees);
    _pid.enable();
  }

  @Override
  protected void execute() {
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
    drivetrain.setTank(output, -output);
  }
}
