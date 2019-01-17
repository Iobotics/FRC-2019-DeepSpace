package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraDrive extends CommandBase
{
    NetworkTable table; 
    NetworkTableEntry tv;
    NetworkTableEntry tx;
    NetworkTableEntry tx0;
    NetworkTableEntry tx1;
    NetworkTableEntry tx2;
    NetworkTableEntry ty0;
    NetworkTableEntry ty1;
    NetworkTableEntry ty2;
    NetworkTableEntry ta;
    NetworkTableEntry ta0;
    NetworkTableEntry ta1;
    NetworkTableEntry ta2;
    NetworkTableEntry ts;
    NetworkTableEntry tl;
    NetworkTableEntry ty;

    double isDetected;
    double x;
    double x0;
    double x1;
    double x2;
    double y;
    double y0;
    double y1;
    double y2;
    double area;
    double a0;
    double a1;
    double a2;
    double a3;
    double rotation;
    double latency;
    double distance;
    double height = 10;
    double contours;
    double[] diff = new double[3];
    double pixelX;
    double pixelY;

    int best;

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

    public CameraDrive()
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
        tx0 = table.getEntry("tx0");
        tx1 = table.getEntry("tx1");
        tx2 = table.getEntry("tx2");
        ty0 = table.getEntry("ty0");
        ty1 = table.getEntry("ty1");
        ty2 = table.getEntry("ty2");
        ta0 = table.getEntry("ta0");
        ta1 = table.getEntry("ta1");
        ta2 = table.getEntry("ta2");

        isDetected = tv.getDouble(0.0);
        area = ta.getDouble(0.0);
        rotation = ts.getDouble(0.0);
        latency = tl.getDouble(0.0);
        y = ty.getDouble(0.0);
        distance = height/Math.tan(ty.getDouble(0.0));
        x = tx.getDouble(0.0);
        x0 = tx0.getDouble(0.0);
        x1 = tx1.getDouble(0.0);
        x2 = tx2.getDouble(0.0);
        y0 = ty0.getDouble(0.0);
        y1 = ty0.getDouble(0.0);
        y2 = ty0.getDouble(0.0);
        a0 = ta0.getDouble(0.0);
        a1 = ta1.getDouble(0.0);
        a2 = ta2.getDouble(0.0);

        double[] allXs = {x0, x1, x2};
        double[] allYs = {y0, y2, y2};
        
        
        SmartDashboard.putNumber("x0", x0); //target to robot's left negative, right positive
        SmartDashboard.putNumber("x1", x1);
        SmartDashboard.putNumber("x2", x2);
        //SmartDashboard.putNumber("area", area);
        //SmartDashboard.putNumber("isDetected", isDetected);
        //SmartDashboard.putNumber("Rotation of Target", rotation);
        //SmartDashboard.putNumber("latency", latency);
        //SmartDashboard.putNumber("y", y);
        SmartDashboard.putNumber("a0", a0);
        SmartDashboard.putNumber("a1", a1);
        SmartDashboard.putNumber("a2", a2);
        if(a2 != 0)
        {
            diff[0] = makePositive(y0-y1);
            diff[1] = makePositive(y1-y2);
            diff[2] = makePositive(y0-y2);
            if(diff[0] < diff[1])
            {
                best = 1;
            }
            else
            {
                best = 0;
            }
            if(diff[2]<diff[best])
            {
                best = 2;
            }
            if(best == 0)
            {
                pixelX = (x0 + x1)/2;
                pixelY = (y0 + y1)/2;
            }
            else if(best == 1)
            {
                pixelX = (x0 + x2)/2;
                pixelY = (y0 + y2)/2;
            }
            else if(best == 2)
            {
                pixelX = (x1 + x2)/2;
                pixelY = (y1 + y2)/2;
            }
            x = Math.atan2(1, (2*Math.tan(54/2))/2 * ((1/160)*(pixelX-159.5)));
            y = Math.atan2(1, (2*Math.tan(41/2))/2 * ((1/120)*(119.5-pixelY)));
        }
        

            error = x;
            if(x > threshHold) //Target is on robot's right
            {
                speed = clip(error * kP + kF, 0, maxSpeed); //Make them go right, make positive
            }
            else if(x < -threshHold)//Is on robot's left
            {
                speed = clip(error * kP - kF, -maxSpeed, 0);//Make them go left, make negative
            }
            else
            {
                speed = 0;
            }
            drivetrain.setMecanum(-stickX, -stickY, speed);
            SmartDashboard.putNumber("speed", speed);
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

    private double makePositive(double number)
    {
        if(number<0)
        {
            return -number; 
        }
        return number;
    }
}
