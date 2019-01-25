/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoTurn;
import frc.robot.commands.CommandBase;
import frc.robot.commands.ResetGyro;
<<<<<<< HEAD
=======
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.NavSensor;
>>>>>>> SparkDriveTrain

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

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    _pdp = new PowerDistributionPanel();
    _compressor = new Compressor();

    _pdp.clearStickyFaults();
    _compressor.clearAllPCMStickyFaults();

    _compressor.start();

    CommandBase.init();
    
    SmartDashboard.putData("Auto mode", m_chooser);
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
  public void robotPeriodic() { }

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
    m_autonomousCommand = new AutoTurn(90);
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
    (new ResetGyro()).start();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
    Scheduler.getInstance().run();
    SmartDashboard.putBoolean("Calibraing", CommandBase.navSensor.isCalibrating());
    /* 
    SmartDashboard.putData("DriveTrain", CommandBase.drivetrain);
    SmartDashboard.putNumber("kP Drive", CommandBase.drivetrain.getP());
    SmartDashboard.putNumber("kI Drive", CommandBase.drivetrain.getI());
    SmartDashboard.putNumber("kD Drive", CommandBase.drivetrain.getD());
    SmartDashboard.putNumber("kFF Drive", CommandBase.drivetrain.getFF());
    SmartDashboard.putNumber("kIzone Drive", CommandBase.drivetrain.getIzone());

     double p = SmartDashboard.getNumber("kP Drive", 0);
     if (p != CommandBase.drivetrain.getP()){CommandBase.drivetrain.setP(p);}

     double  i= SmartDashboard.getNumber("kI Drive", 0);
     if (i != CommandBase.drivetrain.getI()){CommandBase.drivetrain.setI(i);}

     double d = SmartDashboard.getNumber("kD Drive", 0);
     if (d != CommandBase.drivetrain.getD()){CommandBase.drivetrain.setD(d);}

     double ff = SmartDashboard.getNumber("kFF Drive", 0);
     if (ff != CommandBase.drivetrain.getFF()){CommandBase.drivetrain.setFF(ff);}

     double iZone = SmartDashboard.getNumber("kIzone Drive", 0);
     if (iZone != CommandBase.drivetrain.getIzone()){CommandBase.drivetrain.setIzone(iZone );}
     */

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
