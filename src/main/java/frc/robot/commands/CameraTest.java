package frc.robot.commands;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraTest extends CommandBase
{
    NetworkTable table; 
    NetworkTableEntry tv;
    NetworkTableEntry tx;
    NetworkTableEntry ta;
    NetworkTableEntry ts;
    NetworkTableEntry tl;
    NetworkTableEntry ty;

    double isDetected;
    double x;
    double y;
    double area;
    double rotation;
    double latency;
    double distance;
    double height = 10;

    double kF = .1; //kF .1
    double kP = .02; //kP .02
    double kI = 0;
    double kD = 0;

    double threshHold = .2; //degrees
    double error;
    double maxSpeed = 1;
    double speed;
    double stickX;
    double stickY;
    double test = 0;

    public CameraTest()
    {
        requires(drivetrain);
    }

    @Override
    protected void initialize()
    {
        table = NetworkTableInstance.getDefault().getTable("limelight"); 
        
    }

    @Override
    protected void execute()
    {
        
        stickX = oi.getLeftStickX(); //Right positive, left negative
        stickY = -oi.getLeftStickY();

        tv = table.getEntry("tv");
        ta = table.getEntry("ta");
        ts = table.getEntry("ts");
        tl = table.getEntry("tl");
        ty = table.getEntry("ty");
        tx = table.getEntry("tx");

        isDetected = tv.getDouble(0.0);
        area = ta.getDouble(0.0);
        rotation = ts.getDouble(0.0);
        latency = tl.getDouble(0.0);
        y = ty.getDouble(0.0);
        distance = height/Math.tan(ty.getDouble(0.0));
        x = tx.getDouble(0.0);

        SmartDashboard.putNumber("x", x); //target to robot's left negative, right positive
        //SmartDashboard.putNumber("area", area);
        //SmartDashboard.putNumber("isDetected", isDetected);
        //SmartDashboard.putNumber("Rotation of Target", rotation);
        //SmartDashboard.putNumber("latency", latency);
        //SmartDashboard.putNumber("y", y);

        error = x;
        if(x > threshHold) //Target is on robot's right
        {
            speed = clip(error * kP + kF, 0, maxSpeed); //Make them go right, make positive
            //test = 1;
        }
        else if(x < -threshHold)//Is on robot's left
        {
            speed = clip(error * kP - kF, -maxSpeed, 0);//Make them go left, make negative
            //test = 2;
        }
        else
        {
            speed = 0;
            //test = 3;
        }
            drivetrain.setMecanum(stickX, stickY, speed);
            SmartDashboard.putNumber("speed", speed);

            //SmartDashboard.putNumber("Test", test);
    }

    @Override
    protected boolean isFinished() { //If this is true it will stop,false keep going
        return x < threshHold && x > -threshHold;
    }

    @Override
    protected void end()
    {

    }

    @Override
    protected void interrupted(){
        this.end();
    }

    public double getX()
    {
        tx = table.getEntry("tx");
        x = tx.getDouble(0.0);//target right to center is positive, target left to center is negative
        return x;
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
