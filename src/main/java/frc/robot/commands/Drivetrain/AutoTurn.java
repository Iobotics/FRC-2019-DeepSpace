/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CommandBase;

public class AutoTurn extends CommandBase implements PIDOutput {

  private PIDController _pid;

  private double _degrees;

  private double _onTargetTime = Double.MAX_VALUE;
  private boolean _onTarget = false;

  public AutoTurn(double _degrees) {
    requires(drivetrain);
    requires(navSensor);
    _pid = new PIDController(drivetrain.get_kPTurn(), drivetrain.get_kITurn(), drivetrain.get_kDTurn() , navSensor.getSensor(), this);
    _pid.setInputRange(-180, 180);
    _pid.setOutputRange(-0.5, 0.5);
    _pid.setContinuous();
    _pid.setAbsoluteTolerance(5);
    this._degrees = _degrees;
  }

  @Override
  protected void initialize() {
    _pid.setSetpoint(_degrees);
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
    drivetrain.setTank(-output, -output);
  }
}
