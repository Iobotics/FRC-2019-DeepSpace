/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.commands.EnableController;
import frc.robot.commands.ExitHab3;
import frc.robot.commands.GoToHab3;
import frc.robot.commands.RotateCamera;
import frc.robot.commands.RotateLimelight;
import frc.robot.commands.AutoAlighnment.CameraAssist;
import frc.robot.commands.Ball.FromShipToHome;
import frc.robot.commands.Ball.PositionCargoShip;
import frc.robot.commands.Ball.PositionFirstLevel;
import frc.robot.commands.Ball.ReturnHome;
import frc.robot.commands.Drivetrain.ToggleSlowMode;
import frc.robot.commands.HabThree.ToggleHabThreeBack;
import frc.robot.commands.Hatch.ToggleHatch;
import frc.robot.commands.Hatch.ToggleHook;
import frc.robot.commands.Intake.IntakeBall;
import frc.robot.commands.Intake.RunChassisIntake;
import frc.robot.commands.Intake.SetIntakeVelocity;
import frc.robot.commands.Intake.StopChassisIntake;
import frc.robot.commands.Intake.StopIntakeBall;
import frc.robot.commands.Intake.StopIntakeVelocity;
import frc.robot.commands.Shooter.RunShooter;
import frc.robot.commands.Shooter.ShootBall;
import frc.robot.commands.Shooter.StopShooter;
import frc.robot.commands.Shooter.StopShooterAndRotateLimelight;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private static boolean controllerEnabled = true; // TODO- Ask what default is

  private final Joystick _lStick = new Joystick(0);
  private final Joystick _rStick = new Joystick(1);
  private final XboxController _controller = new XboxController(2);
  private final JoystickButton slowModeButton = new JoystickButton(_lStick, 6);

  // Intake Buttons
  private final JoystickButton intakeBall = new JoystickButton(_rStick, 1); // Right Trigger
  private final JoystickButton outtakeBall = new JoystickButton(_lStick, 1); // Left Trigger
  //private final JoystickButton runIntake = new JoystickButton(_controller, 4);
  private final JoystickButton velocityIntake = new JoystickButton(_controller, 5);

  // Shooter Buttons
  private final JoystickButton positionShooterFirstLevel = new JoystickButton(_controller, 2);
  private final JoystickButton positionShooterCargoShip = new JoystickButton(_controller, 3);
  private final JoystickButton shootBall = new JoystickButton(_controller, 6);
  private final JoystickButton grabBall = new JoystickButton(_controller, 8); // Actuates the Shooter
  private final JoystickButton holdBall = new JoystickButton(_controller, 7);
  // private final JoystickButton shiptohome = new JoystickButton(_controller, 4);

  // Hatch Buttons
  private final JoystickButton toggleHatchHook = new JoystickButton(_rStick, 4); // Left Thumb Button
  private final JoystickButton extendHatch = new JoystickButton(_rStick, 5); // Right Thumb Button

  // ZoneTheory
  private final JoystickButton toggleZoneTwoBack = new JoystickButton(_lStick, 4);
  // private final JoystickButton autoZone3 = new JoystickButton(_rStick, 2);

  private final JoystickButton cameraAutoCargo = new JoystickButton(_rStick, 2);
  private final JoystickButton cameraAutoRocket = new JoystickButton(_lStick, 2);
  private final JoystickButton rotateCamera = new JoystickButton(_controller, 10);
  private final JoystickButton rotateLimelight = new JoystickButton(_controller, 9);

  private final JoystickButton enableController = new JoystickButton(_lStick, 10); // TODO- Ask which button

  private final JoystickButton gotoHabitat3 = new JoystickButton(_rStick, 10);

  public final JoystickButton ledSwitch = new JoystickButton(_rStick, 9);

  public OI(){

    // Hatch Commands
    extendHatch.whenPressed(new ToggleHatch());
    toggleHatchHook.whenPressed(new ToggleHook());

    intakeBall.whenPressed(new ConditionalCommand(new IntakeBall()){
      @Override
      protected boolean condition() {
        return true;
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
    shootBall.whenReleased(new ConditionalCommand(new StopShooterAndRotateLimelight()){
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

    toggleZoneTwoBack.whenPressed(new ToggleHabThreeBack());

    //cameraAutoCargo.whenPressed(new CameraAssist(false));
    //cameraAutoRocket.whenPressed(new CameraAssist(true));
    
    //rotateCamera.whenPressed(new RotateCamera());
    //rotateLimelight.whenPressed(new RotateLimelight());

    velocityIntake.whenPressed(new SetIntakeVelocity());
    velocityIntake.whenReleased(new StopIntakeVelocity());

    enableController.whenPressed(new EnableController(!controllerEnabled));

    gotoHabitat3.whenPressed(new GoToHab3());
    gotoHabitat3.whenReleased(new ExitHab3());

    slowModeButton.whenPressed(new ToggleSlowMode());
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

  public boolean getControllerLeftDown()
  {
    return _controller.getRawButton(9);
  }

  public boolean getCameraButton()
  {
    return _lStick.getRawButton(2) || _rStick.getRawButton(2);
  }

  public boolean getXButton()
  {
    return _controller.getRawButton(1);
  }

  public boolean getYButton(){
    return _controller.getRawButton(4);
  }

  public void setControllerEnabled(boolean enabled)
  {
    controllerEnabled = enabled;
  }

  public boolean getAButton()
  {
    return _controller.getAButton();
  }

  public boolean getStartButton(){
    return _controller.getRawButton(10);
  }

  public boolean getServoButton()
  {
    return _lStick.getRawButton(5);
  }
}