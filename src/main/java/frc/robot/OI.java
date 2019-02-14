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

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //private final Joystick _lStick = new Joystick(0);
  private final Joystick _rStick = new Joystick(1);
  private final Joystick _xStick = new Joystick(3);

  private final JoystickButton _cameraTurnButton = new JoystickButton(_xStick, 5);


  public OI()
  {
    
    _cameraTurnButton.whileHeld(new CameraDrive());
  }

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

  public double getRightStickX(){
    return _xStick.getX(Hand.kRight);
  }

  public double getRightStickY(){
    return _rStick.getY();
  }

  public double getLeftStickX(){
    //return _lStick.getX();
    return _xStick.getX(Hand.kLeft);
  }

  public double getLeftStickY(){
    //return _lStick.getY();
    return _xStick.getY(Hand.kLeft);
  }
}
