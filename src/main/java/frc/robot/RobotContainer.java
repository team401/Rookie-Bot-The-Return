// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ClimbConstants;
import frc.robot.commands.Auto;
import frc.robot.commands.MoveArm;
import frc.robot.commands.OperatorControl;
import frc.robot.commands.Auto.AutoType;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final DriveSubsystem drive = new DriveSubsystem();
    private final ArmSubsystem arm = new ArmSubsystem();
    private final IntakeSubsystem intake = new IntakeSubsystem();
    private final ClimbSubsystem climb = new ClimbSubsystem();

    private final XboxController gamepad = new XboxController(0);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Set up default commands
        drive.setDefaultCommand(new OperatorControl(drive,
                () -> gamepad.getRawAxis(1),
                () -> gamepad.getRawAxis(4)));

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {

        // Intake
        // Runs the intake motor when pressed and stops when released
        new JoystickButton(gamepad, Button.kB.value)
                .whenPressed(intake::intake)
                .whenReleased(intake::stop);

        // Shoot
        // Runs the intake motor in reverse to shoot the ball out and stops when
        // released
        new JoystickButton(gamepad, Button.kY.value)
                .whenPressed(intake::shoot)
                .whenReleased(intake::stop);

        // Raise Intake
        // Moves the arm to the raised position when pressed
        new POVButton(gamepad, 0)
                .whenPressed(new MoveArm(arm, ArmConstants.raisedPosition));

        // Lower Intake
        // Moves the arm to the lowered position when pressed
        new POVButton(gamepad, 180)
                .whenPressed(new MoveArm(arm, ArmConstants.loweredPosition));

        // Reset Arm
        // Reset arm position to zero
        new JoystickButton(gamepad, Button.kStart.value)
                .whenPressed(() -> arm.reset(), arm);

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }
}
