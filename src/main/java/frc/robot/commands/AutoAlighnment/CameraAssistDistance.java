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
  private double distance;
  // DO NOT USE F value because it can add this positive power to a NEGATIVE power in opposite directions
  // But actually can if you set the F value to the direction specified
  private final double kP = 0.01;
  private final double kI = 0.0;
  private final double kD = 0.0;

  private final double THRESHOLD = 1;
  private double setpoint;
  private final double MAXSPEED = 1.0;
  private final double SHIPDISTANCE = 33.25;
  private final double ROCKETDISTANCE = 33.25;

  private PIDController pid;
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
        distance = limelight.getDistance();
        if(limelightservo.isOnCargoSide())
        {
            if(limelight.isRocketShip())
            {
                setpoint = ROCKETDISTANCE;
            }
            else
            {
                setpoint = SHIPDISTANCE;
            }
        }
        else
        {
            setpoint = 10;
            // If placing hatches it would go until it could not see the
            // target anymore
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
        drivetrain.setMecanum(0, -1 * pidSpeed *limelightservo.onCargoSideMultiplier(), 0); 
        // Go left negative, right is positive
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
    public double pidGet() { // When target is to the left error is negative
        return limelight.getDistance();
	}
}
