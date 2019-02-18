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
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoTurn;
import frc.robot.commands.HoldShooterPos;
import frc.robot.commands.LevelReset;
import frc.robot.commands.ToggleZoneTwoFront;
import frc.robot.commands.ball.RunCargoCarriage;
import frc.robot.commands.ball.ShootCargoShip;
import frc.robot.commands.ball.ShootFirstLevel;
import frc.robot.commands.ball.StopCargoCarriage;
import frc.robot.commands.hatch.ExtendHatch;
import frc.robot.commands.hatch.PopHatch;
import frc.robot.commands.hatch.ToggleHook;
import frc.robot.commands.ToggleZoneTwoBack;
import frc.robot.commands.MoveOnZoneTwo;
import frc.robot.commands.RotateCamera;
import frc.robot.commands.RunShooter;
import frc.robot.commands.SetShooterPos;
import frc.robot.commands.RunChassisIntake;
import frc.robot.commands.StopChassisIntake;
import frc.robot.commands.StopShooter;
import frc.robot.commands.ToggleIntake;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private final Joystick _lStick = new Joystick(0);
  private final Joystick _rStick = new Joystick(1);
  private final XboxController _controller = new XboxController(2);

  private final JoystickButton levelTwoFront = new JoystickButton(_lStick, 4); 
  private final JoystickButton levelTwoBack = new JoystickButton(_lStick, 5);
  private final JoystickButton autoZoneTwo = new JoystickButton(_lStick, 10);
  private final JoystickButton autoDriveTest = new JoystickButton(_lStick, 11);
  private final JoystickButton toggleIntake = new JoystickButton(_controller, 3);
  private final JoystickButton intakeShooterBall = new JoystickButton(_controller, 1);
  private final JoystickButton shootBall = new JoystickButton(_controller, 2);
  private final JoystickButton runIntakeButton = new JoystickButton(_lStick, 1);
  private final JoystickButton autoDrive = new JoystickButton(_lStick, 11);
  private final JoystickButton runCargoCarriage = new JoystickButton(_controller, 4);
  private final JoystickButton extendHatch = new JoystickButton(_rStick, 4);
  private final JoystickButton popHatch = new JoystickButton(_rStick, 3);
  private final JoystickButton shootCargoShip = new JoystickButton(_controller, 7);
  private final JoystickButton shootFirstLevel = new JoystickButton(_controller, 8);
  private final JoystickButton forwardRotate = new JoystickButton(_controller, 6);//Right Bumper


  public OI(){
    levelTwoFront.whenPressed(new ToggleZoneTwoFront());    
    levelTwoBack.whenPressed(new ToggleZoneTwoBack());
    autoZoneTwo.whenPressed(new MoveOnZoneTwo());
    autoDriveTest.whenPressed(new AutoDrive(60));
    toggleIntake.whenPressed(new ToggleIntake());
    intakeShooterBall.whenPressed(new RunShooter(0.5));
    intakeShooterBall.whenReleased(new StopShooter());

    shootBall.whenPressed(new RunShooter(-1));
    shootBall.whenReleased(new StopShooter());
    
    runIntakeButton.whileHeld(new RunChassisIntake());
    runIntakeButton.whenReleased(new StopChassisIntake());
    autoDrive.whenPressed(new AutoDrive(50));

    runCargoCarriage.whileHeld(new RunCargoCarriage());
    runCargoCarriage.whenReleased(new StopCargoCarriage());
    shootFirstLevel.whenPressed(new ShootFirstLevel());
    shootCargoShip.whenPressed(new ShootCargoShip());  

    extendHatch.whenPressed(new ExtendHatch());
    popHatch.whenPressed(new ToggleHook());

    forwardRotate.whenPressed(new RotateCamera());

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
    return _controller.getRawButton(12); //right stick on x box controller
  }
  
}