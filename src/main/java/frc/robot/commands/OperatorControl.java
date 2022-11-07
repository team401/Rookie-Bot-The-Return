package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

/**
 * A command that allows the joysticks to control the movement of the robot via
 * the drive subsystem
 */
public class OperatorControl extends CommandBase {
	private DriveSubsystem drive;

	// Objects that contain the values for the joysticks
	private DoubleSupplier forward;
	private DoubleSupplier rotate;

	/**
	 * @param subsystem the drive subsystem
	 * @param fwd	   the double supplier for forward direction
	 * @param rot	   the double supplier for rotation
	 */
	public OperatorControl(DriveSubsystem subsystem, DoubleSupplier fwd, DoubleSupplier rot) {
		// Sets the local variables to the parameters passed in
		drive = subsystem;
		forward = fwd;
		rotate = rot;

		// Adds the drive subsystem to the requirements so only one command using drive
		// can run at a time
		addRequirements(drive);
	}

    @Override
    public void execute() {
        // Run the drive (as a percent) forward/backward and rotate (as a percent)
        // -0.2 is to account for drivetrain drift (when trying to drive straight robot
        // drifted to the right)
		// both start moving at 0.25
		double fwd = forward.getAsDouble();
        fwd *= .8;

		double rot = rotate.getAsDouble();
		// change scalar here
		rot *= .5;

		SmartDashboard.putNumber("Forward", fwd);
		SmartDashboard.putNumber("Rotation", rot);
        drive.arcadeDrive(fwd, rot);
    }
}