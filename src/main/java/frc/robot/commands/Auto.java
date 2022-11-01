package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.Constants.ArmConstants;

/**
 * Command group to run autonomous
 * Schedules commands sequentially
 */
public class Auto extends SequentialCommandGroup {

	/**
	 * Different autonoumous routines to run
	 */
	public enum AutoType {
		// Does nothing and just sits on the tarmac
		Nothing, 
		// Drives forward out of the tarmac
		Taxi, 
		// Starts flush against the hub, spits the ball out, then drives backwards out of the tarmac
		OneBall, 
		// Starts on the edge of the tarmac facing a ball, collects ball then drives back to spit both into hub
		TwoBall,
		// Starts facing the first ball in 5 ball for enemy alliance, shoots own cargo that knocks enemy cargo away
		Troll
	}

	private final DriveSubsystem drive;
	private final IntakeSubsystem intake;
	private final ArmSubsystem arm;

	/**
	 * @param drive	Drivetrain subsystem
	 * @param intake Intake subsystem
	 * @param arm Arm subsystem
	 * @param autoType Which routine to run
	 */
	public Auto(DriveSubsystem drive, IntakeSubsystem intake, ArmSubsystem arm, AutoType autoType) {
		this.drive = drive;
		this.intake = intake;
		this.arm = arm;

		switch (autoType) {
			case Taxi:
				addCommands(
					new DriveTime(drive, 1.5, true)
				);
				break;
			case OneBall:
				addCommands(
					new InstantCommand(intake::shoot, intake),
					new WaitCommand(1),
					new InstantCommand(intake::stop, intake),
					new DriveTime(drive, 3, false)
				);
				break;
			case TwoBall:
				addCommands(
					new MoveArm(arm, ArmConstants.loweredPosition).withTimeout(2),
					new InstantCommand(intake::intake, intake),
					new DriveTime(drive, 1.5, true),
					new InstantCommand(intake::stop, intake),
					new MoveArm(arm, ArmConstants.raisedPosition).withTimeout(2),
					new DriveTime(drive, 1.5, false),
					new RotateDrive(drive, 180).withTimeout(2),
					new PrintCommand("jALKDFJL:KJDASLKFJlkADF"),
					new DriveTime(drive, 2, true),
					new InstantCommand(intake::shoot, intake),
					new WaitCommand(3),
					new InstantCommand(intake::stop, intake)
				);
				break;
			case Troll:
				addCommands(
					new SpeedArmLower(arm),
					new InstantCommand(intake::shoot, intake),
					new WaitCommand(2),
					new InstantCommand(intake::stop, intake),
					new MoveArm(arm, ArmConstants.raisedPosition).withTimeout(2),
					new RotateDrive(drive, 90).withTimeout(1.5),
					new DriveTime(drive, 1.5, true)
				);
			default:
				addCommands();
		}
	}
}
