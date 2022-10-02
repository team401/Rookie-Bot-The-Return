package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ClimbSubsystem extends SubsystemBase {

    private final CANSparkMax arm = new CANSparkMax(ClimbConstants.climbArmID, MotorType.kBrushless);

    private final RelativeEncoder encoder = arm.getEncoder();

    private int lastOutput = 0; //Marks the last commanded direction of movement

    public ClimbSubsystem() {
        arm.getEncoder().setPosition(ClimbConstants.minPosition);
    }

    @Override
    public void periodic() {

        // If we go out of bounds and are still trying to move, stop
        if (encoder.getPosition() > ClimbConstants.maxPosition && lastOutput > 0)
            arm.set(0);
        else if (encoder.getPosition() < ClimbConstants.minPosition && lastOutput < 0)
            arm.set(0);
    }

    public void climbUp() {
        lastOutput = 1;
        arm.set(ClimbConstants.climbVolts / 12);
    }

    public void climbDown() {
        lastOutput = -1;
        arm.set(-ClimbConstants.climbVolts / 12);
    }

    public void climbStop() {
        lastOutput = 0;
        arm.set(0);
    }
}
