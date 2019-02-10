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

import frc.robot.commands.hatch.PopHatch;
import frc.robot.commands.hatch.RetractHatch;
import frc.robot.commands.hatch.DropHatch;
import frc.robot.commands.hatch.GrabHatch;
=======
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoTurn;
import frc.robot.commands.RunIntake;
import frc.robot.commands.RunOutake;
<<<<<<< HEAD
>>>>>>> SparkDriveTrain
>>>>>>> c307a4ec7b67a2a643624881d3ecde2ccb6db331
=======
>>>>>>> 2bdcfa97a2ce64240353508a2d6f307760ccb799

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private final Joystick _lStick = new Joystick(0);
  private final Joystick _rStick = new Joystick(1);

<<<<<<< HEAD
<<<<<<< HEAD
  private final JoystickButton _releaseHatchButton = new JoystickButton(_rStick, 4);
  private final JoystickButton _collectHatchButton = new JoystickButton(_rStick, 5);

  public OI() {
    _releaseHatchButton.whenPressed(new DropHatch());
    _collectHatchButton.whenPressed(new RetractHatch());
  }

  public double getRightStickX() {
    return _rStick.getX();
=======
<<<<<<< HEAD
  public OI(){
    
=======
=======
>>>>>>> 2bdcfa97a2ce64240353508a2d6f307760ccb799
  private final JoystickButton runIntake = new JoystickButton(_rStick, 3);
  private final JoystickButton runOutake = new JoystickButton(_lStick, 3);

  public OI(){
<<<<<<< HEAD
    runIntake.whileHeld(new RunIntake());
    runOutake.whileHeld(new RunOutake());
    autoDrive.whenPressed(new AutoTurn(90));
  }
  public double getRightStickX(){
    return _rStick.getX(); 
>>>>>>> c307a4ec7b67a2a643624881d3ecde2ccb6db331
  }

  public double getRightStickY() {
    return _rStick.getY();
>>>>>>> SparkDriveTrain
=======
    runIntake.whileHeld(new RunIntake(0.5));
    runOutake.whileHeld(new RunIntake(-0.5));
>>>>>>> 2bdcfa97a2ce64240353508a2d6f307760ccb799
  }

  public double getLeftStickX() {
    return _lStick.getX();
  }

  public double getLeftStickY() {
    return _lStick.getY();
  }

  public double getRightStickX(){
    return _rStick.getX();
  }

  public double getRightStickY(){
    return _rStick.getY();
  }
  
}
