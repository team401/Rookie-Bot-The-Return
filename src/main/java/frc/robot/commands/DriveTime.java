package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.DriveSubsystem;

/**
 * A Command that drives the robot in a straight line for N seconds
 */
public class DriveTime extends CommandBase {

	private final DriveSubsystem drive;
	private final double timeS;
	private final Timer timer = new Timer();

	/**
	 * @param drive the drive subsystem
	 * @param timeS the amount of time in seconds that the robot will drive
	 *			  forward/backward
	 */
	public DriveTime(DriveSubsystem drive, double timeS) {
		// Sets the local variables to the parameters passed in
		this.drive = drive;
		this.timeS = timeS;

		// Adds the drive subsystem to the requirements so only one command using drive
		// can run at a time
		addRequirements(drive);
	}

	@Override
	public void initialize() {
		// Start the timer at 0s
		timer.reset();
		timer.start();
	}

	@Override
	public void execute() {
		// Run the drive directly backwards
		drive.arcadeDrive(DrivetrainConstants.autoDrivePercent, 0);
	}

	@Override
	public boolean isFinished() {
		// returns true if {timeS} has passed since the start of the command
		return timer.hasElapsed(timeS);
	}

	@Override
	public void end(boolean isFinished) {
		// Stop the drive when the command ends
		drive.arcadeDrive(0, 0);
	}

}
