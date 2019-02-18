package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraDrive extends CommandBase
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
  double error;
  double maxSpeed = 1;
  double speed;
  double test = 0;
  double cycle = 1;

  //boolean goodToShoot = false;

  String xDirection;
  //String zDirection;
  //String distanceMode;

  //boolean pressed = false;
  //boolean unPressed = true;
  //boolean can = false;
  
  public static final double DEADBAND = 0.2;

    public CameraDrive()
    {
        requires(drivetrain);
        requires(limelight);
        requires(navSensor);
    }

    @Override
    protected void initialize()
    {
        
    }

    @Override
    protected void execute()
    {
        x = limelight.getX();

        double xSpeed = Math.abs(oi.getRightStickX()) < DEADBAND ? 0 : oi.getRightStickX();
        double ySpeed = Math.abs(oi.getRightStickY()) < DEADBAND ? 0 : oi.getRightStickY();
        double speed = Math.abs(oi.getLeftStickX()) < DEADBAND ? 0 : -oi.getLeftStickX();


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

        if(oi.getLeftStickMid())
        {
            error = x;
            if(x > threshHold) //Target is on robot's right, PID loop to turn to the target
            {
                xSpeed = clip(error * kP + kF, 0, maxSpeed); //Make them go right, make positive
            }
            else if(x < -threshHold)//Is on robot's left
            {
                xSpeed = clip(error * kP - kF, -maxSpeed, 0);//Make them go left, make negative
            }
            else
            {
                xSpeed = 0;
            }
        }
            drivetrain.setMecanum(xSpeed, ySpeed, speed, navSensor.getAngle());
    }


    @Override
    protected boolean isFinished() { //If this is true it will stop,false keep going
        return false;
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
}
