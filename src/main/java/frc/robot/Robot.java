/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
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
import frc.robot.commands.Intake.HoldIntakePosition;

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

  //NetworkTable table;
  //NetworkTableEntry yButton;
  //NetworkTableInstance inst;

  NetworkTable testTable;
  NetworkTableEntry testButton;
  NetworkTableInstance testInst;
  NetworkTableEntry testNumber;

  OI oi = new OI();

  UsbCamera logitech;
  UsbCamera fishEye;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    _pdp = new PowerDistributionPanel();

    CommandBase.init();

    //inst = NetworkTableInstance.getDefault();
    //inst.startClientTeam(2438);
    //table = inst.getTable("outTable");
    //yButton = table.getEntry("yButton");

    //testInst = NetworkTableInstance.getDefault();
    //testInst.startClientTeam(2436);
    //testTable = testInst.getTable("testTable");
    //testButton = testTable.getEntry("testButton");
    //testNumber = testTable.getEntry("testNumber");

    _compressor = new Compressor();

    _pdp.clearStickyFaults();
    _compressor.clearAllPCMStickyFaults();

    _compressor.start();
    _compressor.clearAllPCMStickyFaults();
    _pdp.clearStickyFaults();
    
    SmartDashboard.putData("Auto mode", m_chooser);
  
    logitech = CameraServer.getInstance().startAutomaticCapture(0);
    logitech.setResolution(160,120);
    fishEye = CameraServer.getInstance().startAutomaticCapture(1);
    //fishEye.setResolution(160,120);
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
      //yButton.setBoolean(oi.getYButton());
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
    m_autonomousCommand = null;
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
    // teleop starts running.
    Command startCommand = new HoldIntakePosition(Constants.intakeArmHome);
    //startCommand.start();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    Scheduler.getInstance().run();

    SmartDashboard.putNumber("Front Left Current", CommandBase.drivetrain.getFrontLeftCurrent());
    SmartDashboard.putNumber("Front Right Current", CommandBase.drivetrain.getFrontRightCurrent());
    SmartDashboard.putNumber("Back Left Current", CommandBase.drivetrain.getBackLeftCurrent());
    SmartDashboard.putNumber("Back Right Current", CommandBase.drivetrain.getBackRightCurrent());
  
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
  
}

