package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.DriveSubsystem;

/**
 * A Command that drives the robot in a straight line for N seconds
 */
public class DriveTime extends CommandBase {

	private final DriveSubsystem drive;
	private final double timeS;
	private final double forward;
	private double desiredAngle;

	private final Timer timer = new Timer();

	private final PIDController controller = new PIDController(0.2, 0, 0.05);

	/**
	 * @param drive the drive subsystem
	 * @param timeS the amount of time in seconds that the robot will drive
	 *			  forward/backward
	 */
	public DriveTime(DriveSubsystem drive, double timeS, boolean forward) {
		// Sets the local variables to the parameters passed in
		this.drive = drive;
		this.timeS = timeS;
		this.forward = forward ? 1 : -1;
		
		// Adds the drive subsystem to the requirements so only one command using drive
		// can run at a time
		addRequirements(drive);
	}
	
	@Override
	public void initialize() {
		// Start the timer at 0s
		timer.reset();
		timer.start();
		this.desiredAngle = drive.GetDriveAngle();
	}

	@Override
	public void execute() {
		// Run the drive directly backwards while compensating for rotational error
		double rot = -controller.calculate(drive.GetDriveAngle(), desiredAngle);
		drive.arcadeDrive(DrivetrainConstants.autoDrivePercent * -forward, rot);
		SmartDashboard.putNumber("Rot", drive.GetDriveAngle());
		SmartDashboard.putNumber("DesRot", desiredAngle);
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
