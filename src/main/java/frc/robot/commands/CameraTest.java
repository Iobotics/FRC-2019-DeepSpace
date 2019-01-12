package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraTest extends CommandBase
{
    NetworkTable table; 
    NetworkTableEntry tv;
    NetworkTableEntry tx;
    NetworkTableEntry ta;
    NetworkTableEntry ts;
    NetworkTableEntry tl;

    double isDetected;
    double x;
    double y;
    double area;
    double rotation;
    double latency;

    public CameraTest()
    {}

    @Override
    protected void initialize()
    {
        table = NetworkTableInstance.getDefault().getTable("limelight"); 
        
    }

    @Override
    protected void execute()
    {
        tv = table.getEntry("tv");
        tx = table.getEntry("tx");
        ta = table.getEntry("ta");
        ts = table.getEntry("ts");
        tl = table.getEntry("tl");

        isDetected = tv.getDouble(0.0);
        x = tx.getDouble(0.0);
        area = ta.getDouble(0.0);
        rotation = ts.getDouble(0.0);
        latency = tl.getDouble(0.0);

        SmartDashboard.putNumber("x", x);
        SmartDashboard.putNumber("area", area);
        SmartDashboard.putNumber("isDetected", isDetected);
        SmartDashboard.putNumber("Rotation of Target", rotation);
        SmartDashboard.putNumber("latency", latency);
        SmartDashboard.putNumber("TestDashBoard", 1);

        if(rotation > 0 && rotation < 0){
            // ^ threash hold later ^
            // drivetrain turn by -rotation;
        }


    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end()
    {}

    @Override
    protected void interrupted(){}
}