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
public class SpeedArmLower extends ProfiledPIDCommand {

	private final ArmSubsystem arm;

	/**
	 * @param arm  Arm subsystem
	 */
	public SpeedArmLower(ArmSubsystem arm) {

		super(
				new ProfiledPIDController(
						25, 0, 0,
						new TrapezoidProfile.Constraints(
								ArmConstants.maxVelocity*2,
								ArmConstants.maxAccel*2)),
				// Close loop on heading
				arm::getPosition,
				// Set reference to target
				ArmConstants.loweredPosition,
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
		return getController().atGoal();
	}

}