package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraDrive extends CommandBase
{
    NetworkTable table;
  NetworkTable outTable; 
  NetworkTableInstance inst;
  //NetworkTableEntry tv;
  NetworkTableEntry tx;
  //NetworkTableEntry ta;
  //NetworkTableEntry ts;
  //NetworkTableEntry tl;
  NetworkTableEntry ty;

  NetworkTableEntry _distance;
  NetworkTableEntry _x;
  NetworkTableEntry _good;
  NetworkTableEntry _aButton;

  double isDetected;
  double x = 0;
  double y;
  double area;
  double rotation;
  double latency;
  //double distance = 0;
  //double heightLowerRocket = 28.75;
  //double heightHigherRocket = 38; //TODO-Find
  //double heightCargo = 28; //TODO- Find
  //double heightObject = 0;
  //double heightCamera = 34.75;
  //double height = heightCamera - heightLowerRocket; //inches

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
        //requires(drivetrain);
        //requires(navSensor);
    }

    @Override
    protected void initialize()
    {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        
        //tv = table.getEntry("tv");
        //ta = table.getEntry("ta");
        //ts = table.getEntry("ts");
        //tl = table.getEntry("tl");
        ty = table.getEntry("ty");
        tx = table.getEntry("tx");

        inst = NetworkTableInstance.getDefault();
        outTable = inst.getTable("outTable");

        _distance = outTable.getEntry("distance");
        _x = outTable.getEntry("x");
        _good = outTable.getEntry("good");
        _aButton = outTable.getEntry("aButton");
    }

    @Override
    protected void execute()
    {
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

        /*if(distance > thresholdDistance)
        {
            zDirection = "Forward";
        }
        else
        {
            zDirection = "Good";
        }
        pressed = oi.getAButton();
        if(pressed && unPressed){
            can = true;
            unPressed = false;
        }
        else if(!pressed)
        {
            unPressed = true;
        }

        if(can && cycle <= 2)
        {
            cycle++;
            can = false;
        }
        else if(can && cycle >= 3)
        {
            cycle = 1;
        }

        if(cycle == 1)
        {
            heightObject = heightLowerRocket;
            distanceMode = "Lower Rocket";
        }
        else if(cycle == 2)
        {
            heightObject = heightHigherRocket;
            distanceMode = "Higher Rocket";
        }
        else if(cycle == 3)
        {
            heightObject = heightCargo;
            distanceMode = "Cargo";
        }*/

        //isDetected = tv.getDouble(0.0);
        //area = ta.getDouble(0.0);
        //rotation = ts.getDouble(0.0);
        //latency = tl.getDouble(0.0);
        //y = ty.getDouble(0.0);
        //distance = -height/Math.tan(Math.toRadians(ty.getDouble(0.0)));
        x = tx.getDouble(0.0);

        //_good.setBoolean(goodToShoot);
        _x.setNumber(x);
        //_distance.setNumber(distance);
        _aButton.setBoolean(oi.getAButton());

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
            //SmartDashboard.putNumber("speed", speed);
            //SmartDashboard.putNumber("x", x);
            //SmartDashboard.putNumber("area", area);
            //SmartDashboard.putNumber("isDetected", isDetected);
            //SmartDashboard.putNumber("Rotation of Target", rotation);
            //SmartDashboard.putNumber("latency", latency);
            //SmartDashboard.putNumber("y", y);
            //SmartDashboard.putBoolean("Ready to Launch", goodToShoot);
            //SmartDashboard.putNumber("Distance", distance);
            //SmartDashboard.putString("x direction", xDirection);
            //SmartDashboard.putString("If you should go forward", zDirection);
            //SmartDashboard.putString("Distance Sensing Mode", distanceMode);
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
