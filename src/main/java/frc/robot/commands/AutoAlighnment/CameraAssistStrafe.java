package frc.robot.commands.AutoAlighnment;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CommandBase;
import jdk.jfr.Threshold;

public class CameraAssistStrafe extends CommandBase implements PIDSource, PIDOutput
{
  private static double x;
  // DO NOT USE F value because it can add this positive power to a NEGATIVE power in opposite directions
  private static final double kP = 0.10; //Before .12
  private static final double kI = 0.012;
  private static final double kD = 0.2;

  private static  final double THRESHOLD = .5; //degrees
  private static final double SETPOINT = 0;
  private static final double MAXSPEED = 1.0;

  private static PIDController pid;
  //private static double speed;

    public CameraAssistStrafe()
    {
        requires(limelight);
        requires(drivetrain);
        requires(limelightservo);

        pid = new PIDController(kP, kI, kD, this, this);
        pid.setAbsoluteTolerance(THRESHOLD);
        pid.setOutputRange(-MAXSPEED, MAXSPEED);
        
    }

    //@Override
    protected void initialize()
    {   
        //limelight.setLEDOn(true);
        /*x = limelight.getX();
        if(Math.abs(x - SETPOINT) <= THRESHOLD)
        {
            this.end();
        }*/
        pid.reset();
        pid.setSetpoint(SETPOINT);
        pid.enable();
    }

    //@Override
    protected void execute()
    {
        //limelight.setLEDOn(true);
        x = limelight.getX();

        //SmartDashboard.putBoolean("onTarget", pid.onTarget());
        //SmartDashboard.putNumber("speed", speed);
        //SmartDashboard.putNumber("x", x);
        //SmartDashboard.putNumber("error", pid.getError());
        //SmartDashboard.putBoolean("onTarget", pid.onTarget());
        //SmartDashboard.putNumber("limelight servo", limelightservo.onCargoSideMultiplier());
    }


    //@Override
    protected boolean isFinished() { // If this is true it will stop, false keep going
        return (pid.onTarget() && limelight.isTargetDetected()) || !oi.getCameraButton();
    }

    //@Override
    protected void end()
    {
        pid.disable();
        //limelight.setLEDOn(false);
        drivetrain.setMecanum(0, 0, 0);
    }

    //@Override
    protected void interrupted()
    {
        this.end();
    }

    //@Override
    public void pidWrite(double pidSpeed) {
        drivetrain.setMecanum(-1 * limelightservo.onCargoSideMultiplier() * pidSpeed, 0, 0); // Go Left negative, right is positive
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
    public double pidGet() { //Target to left error is negative
        return limelight.getX();
    }
}