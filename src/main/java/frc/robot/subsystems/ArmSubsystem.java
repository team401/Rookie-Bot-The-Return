package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

/**
 * Subsystem that handles the rotation arm
 */
public class ArmSubsystem extends SubsystemBase {

    private final CANSparkMax motor = new CANSparkMax(ArmConstants.armMotorID, MotorType.kBrushless);
    private final RelativeEncoder encoder = motor.getEncoder();

    public ArmSubsystem() {
        motor.setInverted(false);
        motor.setIdleMode(IdleMode.kCoast);

        encoder.setPositionConversionFactor(ArmConstants.armGearRatio);
        encoder.setPosition(ArmConstants.raisedPosition); //Arm should start out raised
    }

    /**
     * Resets the position of the encoder
     */
    public void reset() {
        encoder.setPosition(0);
    }

    public void setPercent(double output) {
        motor.set(output);
    }

    /**
     * @return position of the arm in rotations (0 is parallel to the ground)
     */
    public double getPosition() {
        return encoder.getPosition();
    }

    /**
     * @return velocity of the arm in rotations per second
     */
    public double getVelocity() {
        return encoder.getVelocity();
    }
}
