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

public class CameraAssistDistance extends CommandBase implements PIDSource, PIDOutput
{
  private static double distance;
  // DO NOT USE F value because it can add this positive power to a NEGATIVE power in opposite directions
  private static final double kP = 0.01; // Before .01
  private static final double kI = 0.0;
  private static final double kD = 0.0;

  private static  final double THRESHOLD = 1; // 3 inches
  private static  double setpoint;
  private static final double MAXSPEED = 1.0;

  private static PIDController pid;
  //private static double speed;

    public CameraAssistDistance()
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
        distance = limelight.getDistance();
        if(limelightservo.isOnCargoSide())
        {
            setpoint = 33.25;
        }
        else
        {
            setpoint = 10; // Before 5// Before 0
        }
        if(Math.abs(distance - setpoint) <= THRESHOLD)
        {
            this.end();
        }
        pid.reset();
        pid.setSetpoint(setpoint);
        pid.enable();
    }

    //@Override
    protected void execute()
    {
        //limelight.setLEDOn(true);
        //SmartDashboard.putBoolean("onTarget", pid.onTarget());
        //SmartDashboard.putNumber("speed", speed);
        //SmartDashboard.putNumber("error", pid.getError());
        //SmartDashboard.putBoolean("onTarget", pid.onTarget());
        //SmartDashboard.putNumber("distance", limelight.getDistance());
    }


    //@Override
    protected boolean isFinished() { //If this is true it will stop, false keep going
        if(limelightservo.isOnCargoSide())
        {
            return (pid.onTarget() && limelight.isTargetDetected()) || !oi.getCameraButton();
        }
        return !limelight.isTargetDetected() || !oi.getCameraButton();
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
        drivetrain.setMecanum(0, -1 * pidSpeed *limelightservo.onCargoSideMultiplier(), 0); //Go Left negative, right is positive
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
        return limelight.getDistance();
	}
}
