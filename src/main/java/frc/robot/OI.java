/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.hatch.PopHatch;
import frc.robot.commands.RunIntake;
import frc.robot.commands.hatch.GrabHatch;
import frc.robot.commands.GetBallIn;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private final Joystick _lStick = new Joystick(0);
  private final Joystick _rStick = new Joystick(1);

  private final JoystickButton _releaseHatchButton = new JoystickButton(_rStick, 4);
  private final JoystickButton _collectHatchButton = new JoystickButton(_rStick, 5);
  private final JoystickButton _intake = new JoystickButton(_rStick, 1);
  private final JoystickButton _outake = new JoystickButton(_lStick, 1);
  private final JoystickButton _distance = new JoystickButton(_rStick, 8);
  private final JoystickButton _grab = new JoystickButton(_rStick, 9);
  private final JoystickButton _retract = new JoystickButton(_rStick, 10);
  private final JoystickButton _climb = new JoystickButton(_rStick, 3);

  public OI() {
    _releaseHatchButton.whenPressed(new PopHatch());
    _collectHatchButton.whenPressed(new GrabHatch());
    _intake.whileHeld(new RunIntake(1));
    _outake.whileHeld(new RunIntake(-1));
    _distance.whileHeld(new GetBallIn());
  }

  public double getRightStickX() {
    return _rStick.getX();
  }

  public double getRightStickY() {
    return _rStick.getY();
  }

  public double getLeftStickX() {
    return _lStick.getX();
  }

  public double getLeftStickY() {
    return _lStick.getY();
  }

}
