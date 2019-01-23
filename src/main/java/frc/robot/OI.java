/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Grab;
import frc.robot.commands.Release;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //private final Joystick _lStick = new Joystick(0);
  //private final Joystick _rStick = new Joystick(1);
  private final XboxController _xStick = new XboxController(3);

  private final JoystickButton releaseZone3 = new JoystickButton(_xStick, 4);
  private final JoystickButton grabZone3 = new JoystickButton(_xStick, 1);

  public OI(){
    releaseZone3.whenPressed(new Release());
    grabZone3.whenPressed(new Grab());
  }

  public boolean getBButtonPressed()
  {
    return _xStick.getRawButton(2);
  }

  public boolean getXButtonPressed()
  {
    return _xStick.getRawButtonPressed(3);
  }

  public double getLeftStickX(){
    //return _lStick.getX();
    return 0;
  }

  public double getLeftStickY(){
    //return _lStick.getY();
    return 0;
  }

  public double getRightStickX(){
    //return _rStick.getX();
    return 0;
  }

  public double getRightStickY(){
    //return _rStick.getY();
    return 0;
  }

  public XboxController getController() {
    return _xStick;
  }
  
}
