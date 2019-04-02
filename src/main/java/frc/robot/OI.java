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
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.CameraAssist;
import frc.robot.commands.EnableController;
import frc.robot.commands.ExitHab3;
import frc.robot.commands.GoToHab3;
import frc.robot.commands.RotateCamera;
import frc.robot.commands.Ball.FromShipToHome;
import frc.robot.commands.Ball.PositionCargoShip;
import frc.robot.commands.Ball.PositionFirstLevel;
import frc.robot.commands.Ball.ReturnHome;
import frc.robot.commands.Hatch.CloseHook;
import frc.robot.commands.Hatch.ExtendHatch;
import frc.robot.commands.Hatch.GrabAndRetractHatch;
import frc.robot.commands.Hatch.GrabHatch;
import frc.robot.commands.Hatch.OpenHook;
import frc.robot.commands.Hatch.RetractHatch;
import frc.robot.commands.Hatch.ToggleHatch;
import frc.robot.commands.Hatch.ToggleHook;
import frc.robot.commands.Intake.IntakeBall;
import frc.robot.commands.Intake.RunChassisIntake;
import frc.robot.commands.Intake.SetIntakeVelocity;
import frc.robot.commands.Intake.StopIntakeBall;
import frc.robot.commands.Intake.StopIntakeVelocity;
import frc.robot.commands.Intake.StopChassisIntake;
import frc.robot.commands.Shooter.HoldShooterPos;
import frc.robot.commands.Shooter.RunShooter;
import frc.robot.commands.Shooter.SetShooterPos;
import frc.robot.commands.Shooter.ShootBall;
import frc.robot.commands.Shooter.StopShooter;
import frc.robot.commands.Shooter.StopShooterArm;
import frc.robot.commands.ZoneTwo.ToggleZoneTwoBack;
import frc.robot.commands.LEDSwitch;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private static boolean controllerEnabled = true; // TODO- Ask what default is

  private final Joystick _lStick = new Joystick(0);
  private final Joystick _rStick = new Joystick(1);
  private final XboxController _controller = new XboxController(2);


  // Intake Buttons
  private final JoystickButton intakeBall = new JoystickButton(_rStick, 1); // Right Trigger
  private final JoystickButton outtakeBall = new JoystickButton(_lStick, 1); // Left Trigger 
  //private final JoystickButton runIntake = new JoystickButton(_controller, 1);
  private final JoystickButton velocityIntake = new JoystickButton(_controller, 9);

  // Shooter Buttons
  private final JoystickButton positionShooterFirstLevel = new JoystickButton(_controller, 2);
  private final JoystickButton positionShooterCargoShip = new JoystickButton(_controller, 3);
  private final JoystickButton shootBall = new JoystickButton(_controller, 6);
  private final JoystickButton grabBall = new JoystickButton(_controller, 8); // Actuates the Shooter
  private final JoystickButton holdBall = new JoystickButton(_controller, 7);
  //private final JoystickButton shiptohome = new JoystickButton(_controller, 4);

  // Hatch Buttons
  private final JoystickButton grabHatch = new JoystickButton(_rStick, 3); // Left Center Thumb Button 
  private final JoystickButton toggleHatchHook = new JoystickButton(_rStick, 4); // Left Thumb Button
  private final JoystickButton extendHatch = new JoystickButton(_rStick, 5); // Right Thumb Button

  // ZoneTheory
  private final JoystickButton toggleZoneTwoBack = new JoystickButton(_lStick, 4);
  //private final JoystickButton autoZone3 = new JoystickButton(_rStick, 2);

  private final JoystickButton cameraAuto = new JoystickButton(_lStick, 2);
  private final JoystickButton rotateCamera = new JoystickButton(_controller, 4); 

  private final JoystickButton shooterIntake = new JoystickButton(_lStick, 8);
  private final JoystickButton enableController = new JoystickButton(_lStick, 10); // TODO- Ask which button

  private final JoystickButton gotoHabitat3 = new JoystickButton(_rStick, 10);

  public final JoystickButton ledSwitch = new JoystickButton(_rStick, 9);


  public OI(){

    // Hatch Commands
    extendHatch.whenPressed(new ToggleHatch());
    toggleHatchHook.whenPressed(new ToggleHook());
    grabHatch.whenPressed(new GrabHatch());
    grabHatch.whenReleased(new GrabAndRetractHatch());
    ledSwitch.whenPressed(new LEDSwitch());;

    intakeBall.whenPressed(new ConditionalCommand(new IntakeBall()){
      @Override
      protected boolean condition() {
        return !getControllerButtons();
      }
    });
    intakeBall.whenReleased(new StopIntakeBall());

    /*runIntake.whenPressed(new ConditionalCommand(new RunChassisIntake()){
      @Override
      protected boolean condition() {
        return controllerEnabled;
      }
    });
    runIntake.whenReleased(new ConditionalCommand(new StopChassisIntake()){
      @Override
      protected boolean condition() {
        return controllerEnabled;
      }
    });*/

    positionShooterFirstLevel.whenPressed(new ConditionalCommand(new PositionFirstLevel()){
      @Override
      protected boolean condition() {
        return controllerEnabled;
      }
    });
    positionShooterFirstLevel.whenReleased(new ConditionalCommand(new ReturnHome()){
      @Override
      protected boolean condition() {
        return controllerEnabled;
      }
    });

    positionShooterCargoShip.whenPressed(new ConditionalCommand(new PositionCargoShip()){
      @Override
      protected boolean condition() {
        return controllerEnabled;
      }
    });
    positionShooterCargoShip.whenReleased(new ConditionalCommand(new FromShipToHome()){
      @Override
      protected boolean condition() {
        return controllerEnabled;
      }
    });

    shootBall.whenPressed(new ConditionalCommand(new ShootBall()){
      @Override
      protected boolean condition() {
        return controllerEnabled;
      }
    });
    shootBall.whenReleased(new ConditionalCommand(new StopShooter()){
      @Override
      protected boolean condition() {
        return controllerEnabled;
      }
    });

    grabBall.whenPressed(new ConditionalCommand(new RunShooter(0.5)){
      @Override
      protected boolean condition() {
        return controllerEnabled;
      }
    });
    grabBall.whenReleased(new ConditionalCommand(new StopShooter()){
      @Override
      protected boolean condition() {
        return controllerEnabled;
      }
    });

    holdBall.whileHeld(new ConditionalCommand(new RunShooter(0.1)){
      @Override
      protected boolean condition() {
        return controllerEnabled;
      }
    });

    outtakeBall.whenPressed(new RunShooter(-1));
    outtakeBall.whenReleased(new StopShooter());

    toggleZoneTwoBack.whenPressed(new ToggleZoneTwoBack());

    cameraAuto.whenPressed(new CameraAssist());
    rotateCamera.whenPressed(new RotateCamera());

    shooterIntake.whenPressed(new HoldShooterPos(Constants.shooterIntake));
    shooterIntake.whenReleased(new SetShooterPos(Constants.shooterHome));

    velocityIntake.whenPressed(new SetIntakeVelocity());
    velocityIntake.whenReleased(new StopIntakeVelocity());

    enableController.whenPressed(new EnableController(!controllerEnabled));
    
    gotoHabitat3.whenPressed(new GoToHab3());
    gotoHabitat3.whenReleased(new ExitHab3());
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

  public double getControllerStickRight(){
    return _controller.getRawAxis(5);
  }

  public boolean getLeftStickMid()
  {
    return _lStick.getRawButton(2);
  }

  public boolean getYButton()
  {
    return _controller.getBumper(Hand.kLeft);
  }

  public double getLeftTriggerAxis(){
    return _controller.getTriggerAxis(Hand.kLeft);
  }

  public boolean getControllerLeftDown()
  {
    return _controller.getRawButton(9);
  }

  public boolean getCameraButton()
  {
    return _lStick.getRawButton(2);
  }

  public boolean getControllerButtons()
  {
    return _controller.getXButton();
  }

  public void setControllerEnabled(boolean enabled)
  {
    controllerEnabled = enabled;
  }

  public boolean getAButton()
  {
    return _controller.getAButton();
  }
}