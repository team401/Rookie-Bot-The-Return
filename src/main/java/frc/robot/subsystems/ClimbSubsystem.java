package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


/**
 * A subsystem that handles the climbing arm
 */
public class ClimbSubsystem extends SubsystemBase {
	
	private final TalonFX motor = new TalonFX(ClimbConstants.climbArmID);
	boolean confirmed = false;
	
    public ClimbSubsystem() {
        // Resets the motor encoder so that it's at a position 0 (assumes that the robot
        // boots up with the climbing arms all the way down)
        motor.setSelectedSensorPosition(ClimbConstants.offset);
        motor.setNeutralMode(NeutralMode.Brake);
        // motor.setInverted(true);
    }

	@Override
	public void periodic() {
		SmartDashboard.putNumber("Climber Position", getPosition());

		SmartDashboard.putNumber("Climber Draw", motor.getStatorCurrent());

		SmartDashboard.putBoolean("Climber Confirmed", confirmed);
	}

	public void setPercent(double percent) {
		motor.set(ControlMode.PercentOutput, percent);
	}

	/**
	 * @return the position of the climbing motor in [UNITS]
	 */
	public double getPosition() {
		return motor.getSelectedSensorPosition();
	}

	/**
	 * sets the position of the motor encoder to 0
	 */
	public void reset() {
		motor.setSelectedSensorPosition(0);
	}

	/**
	 * Confirm that climber is ready to be locked in
	 */
	public void confirmPullDown() {
		confirmed = !confirmed;
	}

	/**
	 * Move climber telescope up
	 */
	public void moveUp() {
		if (getPosition() < ClimbConstants.maxPosition)
			setPercent(0.5);
		else
			setPercent(0);
	}

	/**
	 * Move climber telescope down
	 */
	public void moveDown() {
		if ((getPosition() > ClimbConstants.minUnconfirmedPosition && !confirmed) 
				|| (getPosition() > ClimbConstants.minPosition && confirmed))
			setPercent(-0.5);
		else 
			setPercent(0);
	}

	/**
	 * Hold climber telescope in place
	 */
	public void stop() {
		setPercent(0);
	}

}