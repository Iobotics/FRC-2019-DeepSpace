/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CameraDrive;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoTurn;
import frc.robot.commands.ToggleZoneTwoFront;
import frc.robot.commands.ToggleZoneTwoBack;
import frc.robot.commands.MoveOnZoneTwo;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private final Joystick _lStick = new Joystick(0);
  private final Joystick _rStick = new Joystick(1);
  private final Joystick _xStick = new Joystick(3);

  private final JoystickButton _cameraTurnButton = new JoystickButton(_xStick, 5);

  public boolean getXButton()
  {
    return _xStick.getRawButton(3);
  }

  public boolean getYButton()
  {
    //return _xStick.getRawButtonPressed(4);
    return _xStick.getRawButton(4);
  }

  public boolean getAButton()
  {
    return _xStick.getRawButton(1);
  }

  public boolean getBButton()
  {
    return _xStick.getRawButton(3);
  }

  public double getXStickX(){
    return _xStick.getX(Hand.kRight);
  }
  private final JoystickButton levelTwoFront = new JoystickButton(_lStick, 4);
  private final JoystickButton levelTwoBack = new JoystickButton(_lStick, 5);
  private final JoystickButton autoZoneTwo = new JoystickButton(_lStick, 10);
  private final JoystickButton autoDriveTest = new JoystickButton(_lStick, 11);

  public OI(){
    levelTwoFront.whenPressed(new ToggleZoneTwoFront());    
    levelTwoBack.whenPressed(new ToggleZoneTwoBack());
    autoZoneTwo.whenPressed(new MoveOnZoneTwo());
    autoDriveTest.whenPressed(new AutoDrive(60));
    
    _cameraTurnButton.whileHeld(new CameraDrive());
  }

  public boolean getLeftStickMid()
  {
    return _lStick.getRawButton(1); //TODO- Find actual button number
  }

  public double getLeftStickX(){
    //return _lStick.getX();
    return _xStick.getX(Hand.kLeft);
  }

  public double getLeftStickY(){
    //return _lStick.getY();
    return _xStick.getY(Hand.kLeft);
  }

  public double getRightStickX(){
    return _rStick.getX();
  }

  public double getRightStickY(){
    return _rStick.getY();
  }
  
}