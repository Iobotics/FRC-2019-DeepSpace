package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraDriveTest extends CommandBase implements PIDSource, PIDOutput
{
    double x;

  double kF = .1; //TODO - find f and p
  double kP = .02; //
  double kI = 0;
  double kD = 0;

  double threshHold = .2; //degrees; -1, -6 (-1,-6 Experiment) TODO- Redo
  double threshHoldLowerX = -6;
  double threshHoldHigherX = 6;
  //double thresholdDistance = 32; //Inches TODO- Find
  double maxSpeed = 1;

  //boolean goodToShoot = false;

  String xDirection;
  //String zDirection;
  //String distanceMode;

  //boolean pressed = false;
  //boolean unPressed = true;
  //boolean can = false;

  private PIDController pid;
  
  public static final double DEADBAND = 0.2;

  double xSpeed;
  double ySpeed;
  double rotation;

    public CameraDriveTest()
    {
        requires(drivetrain);
        requires(limelight);
        requires(navSensor);

        pid = new PIDController(kP, kI, kD, kF, this, this);
        pid.setAbsoluteTolerance(threshHold);
        pid.setOutputRange(-maxSpeed, maxSpeed);
    }

    @Override
    protected void initialize()
    {
        
    }

    @Override
    protected void execute()
    {
        ySpeed = Math.abs(oi.getRightStickY()) < DEADBAND ? 0 : oi.getRightStickY();
        rotation = Math.abs(oi.getLeftStickX()) < DEADBAND ? 0 : -oi.getLeftStickX();


        //SmartDashboard.putNumber("Gyro: ", navSensor.getAngle());

        /*if((x >= threshHoldLowerX && x <= threshHoldHigherX) && distance <= thresholdDistance)
        {//Indicators of how the bot is close to the target
            goodToShoot = true;
        }
        else
        {
            goodToShoot = false;
        }*/

        if(x < threshHoldLowerX)
        { 
            xDirection = "Left"; //target to the left
        }
        else if(x > threshHoldHigherX)
        {
            xDirection = "Right";
        }
        else{
            xDirection = "Good";
        }

        SmartDashboard.putString("xDirection", xDirection);
    }


    @Override
    protected boolean isFinished() { //If this is true it will stop,false keep going
        return pid.onTarget() || this.isTimedOut();
    }

    @Override
    protected void end()
    {
        drivetrain.setMecanum(0, 0, 0, 0);
    }

    @Override
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

    @Override
    public void pidWrite(double pidSpeed) {
        drivetrain.setMecanum(pidSpeed, ySpeed, rotation, navSensor.getAngle());
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
    public double pidGet() {
        return limelight.getX();
	}
}
