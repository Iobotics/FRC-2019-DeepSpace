/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CommandBase;
//import frc.robot.commands.ResetGyro;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  PowerDistributionPanel _pdp;
  Compressor _compressor;
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  static UsbCamera usbCamera0;
  static UsbCamera usbCamera1;

  int width = 2592; //640, 2592
  int height = 1944; //480, 1944
  int fps = 30;
  VideoSink sink;
  VideoSink toSink0;
  VideoSink toSink1;


  //CvSink cvSink0;
  //CvSink cvSink1;
  CvSource outputStream0;
  CvSource outputStream1;
  CvSource rawStream0;
  CvSource rawStream1;
  NetworkTable table;
  String address = "roborio-2438-frc.local";
  NetworkTableEntry buttonStatus;
  NetworkTableInstance inst;
  boolean buttonToggle;

  OI oi = new OI();

  int numCam = 4;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    _pdp = new PowerDistributionPanel();
    //_compressor = new Compressor();

    _pdp.clearStickyFaults();
    
    //_compressor.clearAllPCMStickyFaults();

    //_compressor.start();

    CommandBase.init();
    
    usbCamera0 = CameraServer.getInstance().startAutomaticCapture(0);
    outputStream0 = CameraServer.getInstance().putVideo("Camera0", 160, 120); //160, 120


    usbCamera1 = CameraServer.getInstance().startAutomaticCapture(1);
    outputStream1 = CameraServer.getInstance().putVideo("Camera1", 160, 120);

    new Thread(() -> {
      usbCamera0.setResolution(160, 120); //160, 120. 176, 144
      usbCamera1.setResolution(160, 120);
      
      CvSink cvSink0 = CameraServer.getInstance().getVideo(usbCamera0);
      CvSink cvSink1 = CameraServer.getInstance().getVideo(usbCamera1);
            
      Mat source0 = new Mat();
      Mat output0 = new Mat();
      
      Mat source1 = new Mat();
      Mat output1 = new Mat();
      
      while(!Thread.interrupted()) {
        {
          cvSink0.grabFrame(source0);
          if(cvSink0.grabFrame(source0) == 0)
          {
            SmartDashboard.putString("Error", cvSink0.getError());
          }
          Imgproc.cvtColor(source0, output0, Imgproc.COLOR_BGR2GRAY);
          outputStream0.putFrame(output0);
          
          cvSink1.grabFrame(source1);
          if(cvSink1.grabFrame(source1) == 0)
          {
            SmartDashboard.putString("Error", cvSink1.getError());
          }
          Imgproc.cvtColor(source1, output1, Imgproc.COLOR_BGR2GRAY);
          outputStream1.putFrame(output1);
        }
      }
  }).start();
    
    sink = CameraServer.getInstance().getServer();
    usbCamera0.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
    usbCamera1.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
    outputStream0.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
    outputStream1.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
    
    table = NetworkTableInstance.getDefault().getTable("table");

    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
      //table.setServer(address);


      if(oi.getAButton() || numCam == 4)
      {
        numCam = 0;
        sink.setSource(usbCamera0);
      }
      if(oi.getBButton())
      {
        numCam = 1;
        sink.setSource(usbCamera1);
      }
      numCam = 3;

   }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    //(new ResetGyro()).start();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
