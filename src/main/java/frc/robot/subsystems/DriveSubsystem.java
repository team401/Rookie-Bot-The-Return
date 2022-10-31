package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.DrivetrainConstants;

/**
 * Subsystem that handles the differential drivetrain
 */
public class DriveSubsystem extends SubsystemBase {
	private final WPI_VictorSPX frontLeft = new WPI_VictorSPX(DrivetrainConstants.frontLeftID);
	private final WPI_VictorSPX frontRight = new WPI_VictorSPX(DrivetrainConstants.frontRightID);
	private final WPI_VictorSPX backLeft = new WPI_VictorSPX(DrivetrainConstants.backLeftID);
	private final WPI_VictorSPX backRight = new WPI_VictorSPX(DrivetrainConstants.backRightID);

	// Motor controller groups that represent the left and right gearboxes
	private final MotorControllerGroup left = new MotorControllerGroup(frontLeft, backLeft);
	private final MotorControllerGroup right = new MotorControllerGroup(frontRight, backRight);

	// Differential drivetrain object
	private final DifferentialDrive drive = new DifferentialDrive(left, right);

	private final PigeonIMU pigeon = new PigeonIMU(DrivetrainConstants.pigeonID);
	private final double angleOffset;

	public DriveSubsystem() {
		left.setInverted(true);

		frontLeft.setNeutralMode(NeutralMode.Brake);
		backLeft.setNeutralMode(NeutralMode.Brake);
		frontRight.setNeutralMode(NeutralMode.Brake);
		backRight.setNeutralMode(NeutralMode.Brake);

		angleOffset = pigeon.getYaw();

	}

	public double GetDriveAngle()
	{
		return pigeon.getYaw()-angleOffset;
	}

	/**
	 * Drives the robot using arcade drive
	 * 
	 * @param forward  forward speed
	 * @param rotation rotation value
	 */
	public void arcadeDrive(double forward, double rotation) {
		drive.arcadeDrive(forward, -rotation);
	}

	/**
	 * Drives the robot using arcade drive
	 * 
	 * @param left  left side power
	 * @param right right side power
	 */
	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

}
