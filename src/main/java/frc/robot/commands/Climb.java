package frc.robot.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.subsystems.ClimbSubsystem;

/**
 * Command that runs the telescopes to a position
 */
public class Climb extends CommandBase {

	private final ClimbSubsystem climb;
	private final double goal;
	private final ProfiledPIDController controller = new ProfiledPIDController(0, 0, 0,
			new TrapezoidProfile.Constraints(ClimbConstants.maxVelocity, ClimbConstants.maxAccel));

	/**
	 * @param climb Climb subsystem
	 * @param goal  Goal for the arm in default encoder units
	 */
	public Climb(ClimbSubsystem climb, double goal) {
		this.climb = climb;
		this.goal = goal;

		addRequirements(climb);
	}

	@Override
	public void execute() {
		// Calculate output from measurement to goal, then output as percent to the
		// motor
		double output = controller.calculate(climb.getPosition(), goal);
		climb.setPercent(output);
	}
}
