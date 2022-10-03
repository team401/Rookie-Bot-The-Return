package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * A subsystem that handles the climbing arm
 */
public class ClimbSubsystem extends SubsystemBase {

    private final CANSparkMax motor = new CANSparkMax(ClimbConstants.climbArmID, MotorType.kBrushless);
    private final RelativeEncoder encoder = motor.getEncoder();

    public ClimbSubsystem() {
        // Resets the motor encoder so that it's at a position 0 (assumes that the robot boots up with the climbing arms all the way down)
        motor.getEncoder().setPosition(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Climber Position", getPosition());
    }

    public void setPercent(double percent) {
        motor.set(percent);
    }

    /**
     * @return the position of the climbing motor in [UNITS]
     */
    public double getPosition() {
        return encoder.getPosition();
    }
}