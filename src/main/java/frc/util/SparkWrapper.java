/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Talon Wrapper to
 */
public class SparkWrapper implements SpeedController {

    private CANSparkMax spark;

    public SparkWrapper(CANSparkMax spark){
        this.spark = spark;
    }

    @Override
    public void pidWrite(double output) {
        spark.set(output);
        System.out.println("Calling pidWrite in TalonWrapper");
    }

    @Override
    public void set(double speed) {
        spark.set(speed);
    }

    @Override
    public double get() {
        return spark.get();
    }

    @Override
    public void setInverted(boolean isInverted) {
        spark.setInverted(isInverted);
    }

    @Override
    public boolean getInverted() {
        return spark.getInverted();
    }

    @Override
    public void disable() {
        spark.set(0);
    }

    @Override
    public void stopMotor() {
        spark.set(0);
    }

}
