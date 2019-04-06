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

public class CameraAssistRotate extends CommandBase implements PIDSource, PIDOutput
{
    private static boolean can0 = true;
    private static boolean can1 = true;
    private static boolean first = true;
    private static double time;
  private static double startTime;
  private static double ratio;
  private static double rotationMultiplier = 1.0;
  // DO NOT USE F value because it can add this positive power to a NEGATIVE power in opposite directions
  private static final double kP = 0.01;
  private static final double kI = 0.0;
  private static final double kD = 0.0;

  private static final double SETPOINT = 2.44;
  private static  final double THRESHOLD = .1; // width:height
  private static final double MAXSPEED = 1.0;
  private static final double ENDTIME = 2.0; // seconds
  private static double averageError = 0;
  private static double lastError = 0;
  private static double error[];
  private static double i = 0;


  //private static String xDirection;
  private static PIDController pid;
  //private static double speed;
  private static Timer timer; // time in seconds


    public CameraAssistRotate()
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
        ratio = limelight.getWidthHeightRatio();
        if(Math.abs(ratio) <= THRESHOLD)
        {
            this.end();
        }
        pid.reset();
        pid.setSetpoint(SETPOINT);
        pid.enable();
        limelight.setLEDOn(true);

        error = new double[5];
    }

    //@Override
    protected void execute()
    {
        SmartDashboard.putBoolean("onTarget", pid.onTarget());
        //SmartDashboard.putNumber("speed", speed);
        //SmartDashboard.putNumber("x", x);
        //SmartDashboard.putNumber("error", pid.getError());
        //SmartDashboard.putBoolean("onTarget", pid.onTarget());
    }


    //@Override
    protected boolean isFinished() { //If this is true it will stop, false keep going
        //return !oi.getCameraButton();
        /*if(pid.onTarget() && can)
        {
            can = false;
            timer.start();
            startTime = timer.get();
        }
        if(pid.onTarget() && !can)
        {
            if(timer.get() - startTime > ENDTIME)
            {
                timer.stop();
                return true;
            }
        }
        else if(!pid.onTarget() && !can)
        {
            can = true;
            timer.stop();
        }
        return false;*/
        return pid.onTarget();
    }

    //@Override
    protected void end()
    {
        pid.disable();
        limelight.setLEDOn(false);
        drivetrain.setMecanum(0, 0, 0);
    }

    //@Override
    protected void interrupted()
    {
        this.end();
    }

    //@Override
    public void pidWrite(double pidSpeed) {
        if(i < 5 && !first)
        {
            averageError += pid.getError() / 5;
            i++;
        }
        else if(i >= 5)
        {
            i = 0;
            if(Math.abs(averageError) - Math.abs(lastError) >= 0)
            {
                rotationMultiplier = -rotationMultiplier;
            }
            first = false;
            lastError = averageError;
        }
        drivetrain.setMecanum(0, 0, pidSpeed * limelightservo.onCargoSideMultiplier() * rotationMultiplier); //Go Left negative, right is positive
        lastError = pid.getError();
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
        return  limelight.getWidthHeightRatio();
	}
}
