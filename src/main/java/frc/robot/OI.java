/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoTurn;
import frc.robot.commands.LevelReset;
import frc.robot.commands.MoveOnZoneTwo;
import frc.robot.commands.RunIntake;
import frc.robot.commands.RunOutake;
import frc.robot.commands.ToggleZoneTwoBack;
import frc.robot.commands.ToggleZoneTwoFront;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private final Joystick _lStick = new Joystick(0);
  private final Joystick _rStick = new Joystick(1);

  private final JoystickButton runIntake = new JoystickButton(_rStick, 3);
  private final JoystickButton runOutake = new JoystickButton(_lStick, 4);
  private final JoystickButton toggleFront = new JoystickButton(_rStick, 7);
  private final JoystickButton toggleBack = new JoystickButton(_rStick, 6);
  private final JoystickButton zoneReset = new JoystickButton(_rStick, 11);

  public OI(){
    toggleFront.whenPressed(new ToggleZoneTwoFront());
    toggleBack.whenPressed(new ToggleZoneTwoBack());
    zoneReset.whenPressed(new LevelReset());
    runIntake.whileHeld(new RunIntake(0.5));
    runOutake.whileHeld(new RunIntake(-0.5));
  }

  public double getLeftStickX(){
    return _lStick.getX();
  }

  public double getLeftStickY(){
    return _lStick.getY();
  }

  public double getRightStickX(){
    return _rStick.getX();
  }

  public double getRightStickY(){
    return _rStick.getY();
  }
  
}
