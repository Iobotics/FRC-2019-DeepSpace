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

public class CameraDriveTest extends CommandBase implements PIDSource, PIDOutput
{
  private static double x;
  //private static final double kF = .18; //.14 min power to go, 
  //DO NOT USE F value because it can add this positive power to a NEGATIVE power in opposite directions
  private static final double kP = 0.04;//.05
  private static final double kI = 0.0;//.005
  private static final double kD = 0.0;

  private static  final double THRESHOLD = 1.0; //degrees;  Experiment (-2,-1.5)TODO- Redo
  private static final double MAXSPEED = 1.0;


  private static String xDirection;
  private static PIDController pid;
  
  private static final double DEADBAND = 0.2;

  //double xSpeed;
  private static double ySpeed;
  private static double rotation;
  private static double speed;

    public CameraDriveTest()
    {
        requires(limelight);
        requires(chair);

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
        //ySpeed = Math.abs(oi.getRightControllerY()) < DEADBAND ? 0 : -oi.getRightControllerY() * .3;
        //rotation = Math.abs(oi.getLeftControllerX()) < DEADBAND ? 0 : oi.getLeftControllerX() * .3;

        x = limelight.getX();


        //SmartDashboard.putNumber("Gyro: ", navSensor.getAngle());

        /*if((x >= threshHoldLowerX && x <= threshHoldHigherX) && distance <= thresholdDistance)
        {//Indicators of how the bot is close to the target
            goodToShoot = true;
        }
        else
        {
            goodToShoot = false;
        }*/

        if(x < -THRESHOLD) 
        { 
            xDirection = "Left"; //target to the left, x is negative
        }
        else if(x > THRESHOLD)
        {
            xDirection = "Right";
        }
        else
        {
            xDirection = "Good";
        }

        SmartDashboard.putString("xDirection", xDirection);
        SmartDashboard.putNumber("speed", speed);
        SmartDashboard.putNumber("x", x);
        SmartDashboard.putNumber("error", pid.getError());
        SmartDashboard.putBoolean("onTarget", pid.onTarget());
    }


    //@Override
    protected boolean isFinished() { //If this is true it will stop,false keep going
        //return pid.onTarget() || this.isTimedOut() || oi.getYButton();
        return !oi.getControllerLeftDown();
    }

    //@Override
    protected void end()
    {
        pid.disable();
        chair.setMecanum(0, 0, 0);
    }

    //@Override
    protected void interrupted()
    {
        this.end();
    }

    private double clip(double number, double min, double max)
    {
        if(number >= max)
        {
            return max;
        }
        else if(number <= min)
        {
            return min;
        }
        return number;
    }

    //@Override
    public void pidWrite(double pidSpeed) {
        chair.setMecanum(pidSpeed, 0, 0); //Go Left negative, right is positive
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
