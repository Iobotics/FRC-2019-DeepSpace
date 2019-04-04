package frc.robot.commands.AutoAlighnment;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CommandBase;
import jdk.jfr.Threshold;

public class CameraAssistRotate extends CommandBase implements PIDSource, PIDOutput
{
  private static double areaDifference;
  // DO NOT USE F value because it can add this positive power to a NEGATIVE power in opposite directions
  private static final double kP = 0.05;
  private static final double kI = 0.0;
  private static final double kD = 0.0;

  private static  final double THRESHOLD = 0; // area percentage
  private static final double MAXSPEED = 1.0;


  //private static String xDirection;
  private static PIDController pid;
  //private static double speed;
  private static boolean onTarget;

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
        areaDifference = limelight.getAreaDifference();
        if(Math.abs(areaDifference) <= THRESHOLD)
        {
            this.end();
        }
        pid.reset();
        pid.setSetpoint(0);
        pid.enable();
        limelight.setLEDOn(true);
    }

    //@Override
    protected void execute()
    {
        areaDifference = limelight.getAreaDifference();
        if(Math.abs(areaDifference) <= THRESHOLD)
        {
            onTarget = true;
        }
        else
        {
            onTarget = false;
        }

        SmartDashboard.putBoolean("onTarget", onTarget);
        //SmartDashboard.putNumber("speed", speed);
        //SmartDashboard.putNumber("x", x);
        //SmartDashboard.putNumber("error", pid.getError());
        //SmartDashboard.putBoolean("onTarget", pid.onTarget());
    }


    //@Override
    protected boolean isFinished() { //If this is true it will stop, false keep going
        return !oi.getCameraButton();
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
        drivetrain.setMecanum(0, 0, pidSpeed); //Go Left negative, right is positive
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
        return limelightservo.onCargoSideMultiplier() * limelight.getAreaDifference();
	}
}
