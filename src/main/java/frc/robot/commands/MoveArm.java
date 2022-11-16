package frc.robot.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that moves the arm to a rotational goal
 */
public class MoveArm extends ProfiledPIDCommand {

	private final ArmSubsystem arm;

	/**
	 * @param arm  Arm subsystem
	 * @param goal Goal for the arm in rotations of the arm, 0 is all the way down
	 *			 (horizontal to the ground)
	 */
	public MoveArm(ArmSubsystem arm, double goal) {

		super(
				new ProfiledPIDController(
					25, 0, 0,
						new TrapezoidProfile.Constraints(
								ArmConstants.maxVelocity,
								ArmConstants.maxAccel)),
				// Close loop on heading
				arm::getPosition,
				// Set reference to target
				goal,
				// Pipe output to turn robot
				(output, setpoint) -> {
					arm.setPercent(output);
					SmartDashboard.putNumber("Setpoint", setpoint.position);
				},
				// Require the drive
				arm);

		this.arm = arm;

		getController().setTolerance(0.01);

	}

	@Override
	public boolean isFinished() {
		return SmartDashboard.getNumber("KidSafe(1=yes)", 0) == 1;
	}

	@Override
	public void end(boolean isInterrupted) {
		arm.setPercent(0);
	}

}