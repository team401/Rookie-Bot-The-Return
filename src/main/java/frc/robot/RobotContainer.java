// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.MoveArm;
import frc.robot.commands.OperatorControl;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ClimbSubsystem;

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
    private final ClimbSubsystem climber = new ClimbSubsystem();

    private final Joystick leftStick = new Joystick(0);
    private final Joystick rightStick = new Joystick(1);
    private final XboxController gamepad = new XboxController(2);
    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Set up default commands
        drive.setDefaultCommand(new OperatorControl(drive,
                () -> leftStick.getRawAxis(1),
                () -> rightStick.getRawAxis(0)));

        arm.setDefaultCommand(new MoveArm(arm, 0));
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

        //Intake
        new JoystickButton(gamepad, Button.kLeftBumper.value) //change to trigger maybe?
            .whenPressed(arm::intake)
            .whenReleased(arm::stop);

        //Shoot
        new JoystickButton(gamepad, Button.kRightBumper.value) //change to trigger maybe?
            .whenPressed(arm::shoot)
            .whenReleased(arm::stop);

        //Spit
        new JoystickButton(gamepad, Button.kStart.value)
            .whenPressed(arm::spit)
            .whenReleased(arm::stop);
        
        //Raise Intake
        new JoystickButton(gamepad, Button.kA.value)
            .whenPressed(new MoveArm(arm, 0));

        //Lower Intake
        new JoystickButton(gamepad, Button.kB.value)
            .whenPressed(new MoveArm(arm, Constants.ArmConstants.lowerPosition));

        // Raise climbers
        new JoystickButton(gamepad, Button.kX.value)
            .whenPressed(climber::climbUp)
            .whenReleased(climber::climbStop);
        
        // Lower climbers
        new JoystickButton(gamepad, Button.kY.value)
            .whenPressed(climber::climbDown)
            .whenReleased(climber::climbStop);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    //public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        // return m_autoCommand
    //}
}
