package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

/**
 * A subsystem that interfaces with the intake motor
 */
public class IntakeSubsystem extends SubsystemBase {

	private final WPI_VictorSPX intakeMotor = new WPI_VictorSPX(ArmConstants.intakeMotorID);

	public void intake() {
		intakeMotor.set(ControlMode.PercentOutput, ArmConstants.intakeSpeed);
	}

	public void shoot() {
		SmartDashboard.putNumber("Time", System.currentTimeMillis());
		intakeMotor.set(ControlMode.PercentOutput, ArmConstants.shooterSpeed);
	}

	public void stop() {
		intakeMotor.set(ControlMode.PercentOutput, 0);
	}
}