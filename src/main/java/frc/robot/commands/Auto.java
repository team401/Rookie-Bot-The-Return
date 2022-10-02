package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class Auto extends SequentialCommandGroup {

    public enum AutoType {
        Shoot, DriveShoot, Drive, Nothing
    }

    private final DriveSubsystem drive;
    private final IntakeSubsystem intake;

    private final double driveTimeS = 0.5;
    
    public Auto(DriveSubsystem drive, IntakeSubsystem intake, AutoType autoType) {
        this.drive = drive;
        this.intake = intake;
        
        switch (autoType) {
            case Shoot:
                addCommands(
                    new InstantCommand(intake::shoot, intake),
                    new WaitCommand(driveTimeS),
                    new InstantCommand(intake::stop, intake)
                );
                
            case DriveShoot:
                addCommands(
                    new InstantCommand(intake::shoot, intake),
                    new WaitCommand(driveTimeS),
                    new InstantCommand(intake::stop, intake),
                    new DriveTime(drive, driveTimeS)
                );

            case Drive:
                addCommands(
                    new DriveTime(drive, driveTimeS)
                );

            case Nothing:
            default:
                addCommands(
                );
        }
    }
}
