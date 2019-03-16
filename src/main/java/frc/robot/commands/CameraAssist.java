package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jdk.jfr.Threshold;

public class CameraAssist extends CommandBase implements PIDSource, PIDOutput
{
  private static double x;
  //private static final double kF = .18; //.14 min power to go, 
  //DO NOT USE F value because it can add this positive power to a NEGATIVE power in opposite directions
  private static final double kP = 0.05;//TODO- Test this
  private static final double kI = 0.0;
  private static final double kD = 0.0;

  private static  final double THRESHOLD = .5; //degrees
  private static final double MAXSPEED = 1.0;


  private static String xDirection;
  private static PIDController pid;
  private static double speed;
  private static boolean onTarget;

    public CameraAssist()
    {
        requires(limelight);
        requires(drivetrain);

        pid = new PIDController(kP, kI, kD, this, this);
        pid.setAbsoluteTolerance(THRESHOLD);
        pid.setOutputRange(-MAXSPEED, MAXSPEED);
        
    }

    //@Override
    protected void initialize()
    {   
        pid.reset();
        pid.setSetpoint(0);
        pid.enable();
    }

    //@Override
    protected void execute()
    {
        x = limelight.getX();
        if(x >= -THRESHOLD && x <= THRESHOLD)
        {
            onTarget = true;
        }
        else
        {
            onTarget = false;
        }

        SmartDashboard.putBoolean("onTarget", onTarget);
        SmartDashboard.putNumber("speed", speed);
        SmartDashboard.putNumber("x", x);
        SmartDashboard.putNumber("error", pid.getError());
        //SmartDashboard.putBoolean("onTarget", pid.onTarget());
    }


    //@Override
    protected boolean isFinished() { //If this is true it will stop,false keep going
        return !oi.getCameraButton();
    }

    //@Override
    protected void end()
    {
        pid.disable();
        drivetrain.setMecanum(0, 0, 0);
    }

    //@Override
    protected void interrupted()
    {
        this.end();
    }

    //@Override
    public void pidWrite(double pidSpeed) {
        drivetrain.setMecanum(pidSpeed, 0, 0); //Go Left negative, right is positive
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
    public double pidGet() {//Target to left error is negative
        return -limelight.getX();
	}
}
