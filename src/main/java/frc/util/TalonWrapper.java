/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Talon Wrapper to
 */
public class TalonWrapper implements SpeedController {

    private TalonSRX talon;

    public TalonWrapper(TalonSRX talon){
        this.talon = talon;
    }

    @Override
    public void pidWrite(double output) {
        talon.set(ControlMode.Position, output);
        System.out.println("Calling pidWrite in TalonWrapper");
    }

    @Override
    public void set(double speed) {
        talon.set(ControlMode.PercentOutput, speed);
    }

    @Override
    public double get() {
        return talon.getMotorOutputPercent();
    }

    @Override
    public void setInverted(boolean isInverted) {
        talon.setInverted(isInverted);
    }

    @Override
    public boolean getInverted() {
        return talon.getInverted();
    }

    @Override
    public void disable() {
        talon.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void stopMotor() {
        talon.set(ControlMode.PercentOutput, 0);
    }

}
