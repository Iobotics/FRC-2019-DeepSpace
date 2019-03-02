/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Ball.PositionCargoShip;
import frc.robot.commands.Ball.PositionFirstLevel;
import frc.robot.commands.Ball.ReturnHome;
import frc.robot.commands.Ball.ShootBall;
import frc.robot.commands.Ball.StopShootBall;
import frc.robot.commands.Hatch.ExtendHatch;
import frc.robot.commands.Hatch.GrabAndRetractHatch;
import frc.robot.commands.Hatch.GrabHatch;
import frc.robot.commands.Hatch.RetractHatch;
import frc.robot.commands.Hatch.ToggleHook;
import frc.robot.commands.Intake.IntakeBall;
import frc.robot.commands.Intake.RunChassisIntake;
import frc.robot.commands.Intake.StopIntakeBall;
import frc.robot.commands.Intake.StopChassisIntake;
import frc.robot.commands.Shooter.RunShooter;
import frc.robot.commands.Shooter.StopShooter;
import frc.robot.commands.ZoneTwo.ToggleZoneTwoBack;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private final Joystick _lStick = new Joystick(0);
  private final Joystick _rStick = new Joystick(1);
  private final XboxController _controller = new XboxController(2);


  //Intake Buttons
  private final JoystickButton intakeBall = new JoystickButton(_lStick, 1);//Left Trigger
  private final JoystickButton outtakeBall = new JoystickButton(_rStick, 1);//Right Trigger
  private final JoystickButton runIntake = new JoystickButton(_rStick, 6);

  //Shooter Buttons
  private final JoystickButton positionShooterFirstLevel = new JoystickButton(_controller, 1);
  private final JoystickButton positionShooterCargoShip = new JoystickButton(_controller, 2);
  private final JoystickButton shootBall = new JoystickButton(_controller, 6);
  private final JoystickButton grabBall = new JoystickButton(_controller, 8);
  private final JoystickButton releaseBall = new JoystickButton(_controller, 7);

  //Hatch Buttons
  private final JoystickButton grabHatch = new JoystickButton(_lStick, 3);//Left Center Thumb Button 
  private final JoystickButton toggleHatchHook = new JoystickButton(_lStick, 4);//Left Thumb Button
  private final JoystickButton extendHatch = new JoystickButton(_lStick, 5);//Right Thumb Button

  //ZoneTheory
  private final JoystickButton toggleZoneTwoBack = new JoystickButton(_rStick, 4);
  private final JoystickButton autoZone3 = new JoystickButton(_rStick, 2);

  private final JoystickButton cameraAuto = new JoystickButton(_controller, 9);


  public OI(){

    //Hatch Commands
    extendHatch.whenPressed(new ExtendHatch());
    extendHatch.whenReleased(new RetractHatch());
    toggleHatchHook.whenPressed(new ToggleHook());
    toggleHatchHook.whenReleased(new ToggleHook());

    intakeBall.whenPressed(new IntakeBall());
    intakeBall.whenReleased(new StopIntakeBall());
    runIntake.whenPressed(new RunChassisIntake());
    runIntake.whenReleased(new StopChassisIntake());

    positionShooterFirstLevel.whenPressed(new PositionFirstLevel());
    positionShooterFirstLevel.whenReleased(new ReturnHome());

    positionShooterCargoShip.whenPressed(new PositionCargoShip());
    positionShooterCargoShip.whenReleased(new ReturnHome());

    shootBall.whenPressed(new ShootBall());
    shootBall.whenReleased(new ShootBall());

    grabHatch.whenPressed(new GrabHatch());
    grabHatch.whenReleased(new GrabAndRetractHatch());

    grabBall.whenPressed(new RunShooter(0.5));
    grabBall.whenReleased(new StopShooter());

    releaseBall.whenPressed(new RunShooter(-1));
    releaseBall.whenReleased(new StopShooter());

    toggleZoneTwoBack.whenPressed(new ToggleZoneTwoBack());
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

  public double getControllerStick(){
    return _controller.getRawAxis(1);
  }

  public boolean getLeftStickMid()
  {
    return _lStick.getRawButton(2);
  }

  public boolean getYButton()
  {
    return _controller.getRawButtonPressed(4); //right stick on x box controller
  }

  public double getLeftTriggerAxis(){
    return _controller.getTriggerAxis(Hand.kLeft);
  }

  public boolean getControllerLeftDown()
  {
    return _controller.getRawButton(9);
  }
  
}