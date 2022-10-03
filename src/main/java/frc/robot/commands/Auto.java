package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * Command group to run autonomous
 * Schedules commands sequentially
 */
public class Auto extends SequentialCommandGroup {

    /**
     * Different autonoumous routines to run
     */
    public enum AutoType {
        Shoot, DriveShoot, Drive, Nothing
    }

    private final DriveSubsystem drive;
    private final IntakeSubsystem intake;

    private final double driveTimeS = 0.5;
    
    /**
     * @param drive Drivetrain subsystem
     * @param intake Intake subsystem
     * @param autoType Which routine to run
     */
    public Auto(DriveSubsystem drive, IntakeSubsystem intake, AutoType autoType) {
        this.drive = drive;
        this.intake = intake;
        
        switch (autoType) {
            // Start up against hub
            // Score two balls
            case Shoot:
                addCommands(
                    new InstantCommand(intake::shoot, intake),
                    new WaitCommand(driveTimeS),
                    new InstantCommand(intake::stop, intake)
                );
            // Start up against hub
            // Score two balls then back up across the line
            case DriveShoot:
                addCommands(
                    new InstantCommand(intake::shoot, intake),
                    new WaitCommand(driveTimeS),
                    new InstantCommand(intake::stop, intake),
                    new DriveTime(drive, driveTimeS)
                );
            // Back up across the line
            case Drive:
                addCommands(
                    new DriveTime(drive, driveTimeS)
                );

            // Do nothing
            case Nothing:
            default:
                addCommands(
                );
        }
    }
}
