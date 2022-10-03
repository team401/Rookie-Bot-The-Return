package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
  private final Joystick joystickDrive = new Joystick(Constants.joystickDrivePort);
  private final Joystick joystickRotate = new Joystick(Constants.joystickRotatePort);
  private final XboxController gamePad = new XboxController(Constants.xboxControllerPort);
  private final DriveSubsystem drive = new DriveSubsystem();
  private final ClimbingSubsystem climb = new ClimbingSubsystem();
  private final ArmSubsystem arm = new ArmSubsystem();

  public RobotContainer() {

    configureButtonBindings();

    drive.setDefaultCommand(new OperatorControl(
        drive, 
        () -> joystickDrive.getRawAxis(0), 
        () -> joystickRotate.getRawAxis(1)));
    
  }


  private void configureButtonBindings() {
    /* 
    replaced with POV!
    new JoystickButton(gamePad, Button.kY.value)
        .whenPressed(climb::up)
        .whenReleased(climb::stop);

    repeat of y button?
    new JoystickButton(gamePad, Button.kB.value)
        .whenPressed(climb::up)
        .whenReleased(climb::stop);
    */

    new JoystickButton(gamePad, Button.kA.value)
        .whenPressed(arm::collect)
        .whenReleased(arm::stop);
    new JoystickButton(gamePad, Button.kX.value)
        .whenPressed(arm::shoot)
        .whenReleased(arm::stop);
    
    new JoystickButton(gamePad, Button.kRightBumper.value)
        .whileHeld(() -> arm.runArmPID(Constants.topOfArm))
        .whenReleased(() -> arm.runArmPID(0));

    new POVButton(gamePad, 180) 
        .whenPressed(climb::down)
        .whenReleased(climb::stop);
    new POVButton(gamePad, 0)
        .whenPressed(climb::up)
        .whenReleased(climb::stop);

  }


  public Command getAutonomousCommand() {
        return null;
  }
}
