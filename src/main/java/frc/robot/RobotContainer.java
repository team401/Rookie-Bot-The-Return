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
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ClimbConstants;
import frc.robot.commands.Auto;
import frc.robot.commands.Climb;
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

    private final Joystick leftStick = new Joystick(0);
    private final Joystick rightStick = new Joystick(1);
    private final XboxController gamepad = new XboxController(2);

    private SendableChooser<Command> autoChooser = new SendableChooser<Command>();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Set up default commands
        drive.setDefaultCommand(new OperatorControl(drive,
                () -> leftStick.getRawAxis(1),
                () -> rightStick.getRawAxis(0)));
        
        // Configure the button bindings
        configureButtonBindings();
        configureAutoPaths();
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
        new JoystickButton(gamepad, Button.kB.value)
            .whenPressed(intake::intake)
            .whenReleased(intake::stop);

        // Shoot
        new JoystickButton(gamepad, Button.kY.value)
            .whenPressed(intake::shoot)
            .whenReleased(intake::stop);
        
        // Raise Intake
        new POVButton(gamepad, 0)
            .whenPressed(new MoveArm(arm, ArmConstants.raisedPosition));

        // Lower Intake
        new POVButton(gamepad, 180)
            .whenPressed(new MoveArm(arm, ArmConstants.loweredPosition));

        // Reset Arm
        new JoystickButton(gamepad, Button.kStart.value)
            .whenPressed(() -> arm.reset(), arm);

        // Jog Arm Down
        new Trigger(() -> (gamepad.getRawAxis(1) > 0.5))
            .whenActive(() -> arm.setPercent(-0.1), arm)
            .whenInactive(() -> arm.setPercent(0), arm);

        // Jog Arm Up
        new Trigger(() -> (gamepad.getRawAxis(1) < -0.5))
            .whenActive(() -> arm.setPercent(0.2), arm)
            .whenInactive(() -> arm.setPercent(0), arm);

        // Raise Climber
        new POVButton(gamepad, 90)
            .whenPressed(new Climb(climb, ClimbConstants.maxPosition));

        // Raise Climber
        new POVButton(gamepad, 270)
            .whenPressed(new Climb(climb, ClimbConstants.minPosition));
    }

    private void configureAutoPaths() {
        autoChooser.addOption("Shoot", 
            new Auto(drive, intake, AutoType.Shoot));
        autoChooser.addOption("DriveShoot", 
            new Auto(drive, intake, AutoType.DriveShoot));
        autoChooser.addOption("Drive",
            new Auto(drive, intake, AutoType.Drive));
        autoChooser.addOption("Nothing",
            new Auto(drive, intake, AutoType.Nothing));
        
        autoChooser.setDefaultOption("-DriveShoot-", 
            new Auto(drive, intake, AutoType.DriveShoot));

        SmartDashboard.putData("Auto Mode", autoChooser);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
         return autoChooser.getSelected();
    }
}
