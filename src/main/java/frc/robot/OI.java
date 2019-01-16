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
<<<<<<< HEAD
import frc.robot.commands.OperateSolenoid;
=======
import frc.robot.commands.RunIntake;
import frc.robot.commands.RunOutake;
>>>>>>> 764faa2cfeae19124851d733752df39cdba4726f

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private final Joystick _lStick = new Joystick(0);
  private final Joystick _rStick = new Joystick(1);

<<<<<<< HEAD
<<<<<<< HEAD
  private final JoystickButton _solenoidButton = new JoystickButton(_lStick, 1);

  public OI(){
    _solenoidButton.whenPressed(new OperateSolenoid());
  }

=======
=======
  private final JoystickButton runIntake = new JoystickButton(_rStick, 3);
  private final JoystickButton runOutake = new JoystickButton(_lStick, 3);

>>>>>>> 764faa2cfeae19124851d733752df39cdba4726f
  public OI(){
    runIntake.whileHeld(new RunIntake());
    runOutake.whileHeld(new RunOutake());
  }
>>>>>>> SparkDriveTrain
  public double getRightStickX(){
    return _rStick.getX();
  }

  public double getRightStickY(){
    return _rStick.getY();
  }

  public double getLeftStickX(){
    return _lStick.getX();
  }

  public double getLeftStickY(){
    return _lStick.getY();
  }

}
