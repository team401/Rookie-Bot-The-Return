package frc.robot.subsystems;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbSubsystem extends SubsystemBase {

    private final CANSparkMax motor = new CANSparkMax(ClimbConstants.climbArmID, MotorType.kBrushless);
    private final RelativeEncoder encoder = motor.getEncoder();

    private int lastOutput = 0; //Marks the last commanded direction of movement
    
    private double setpoint;

    public ClimbSubsystem() {
        motor.getEncoder().setPosition(0);
    }

    @Override
    public void periodic() {

        // If we go out of bounds and are still trying to move, stop
        if (encoder.getPosition() > ClimbConstants.maxPosition && lastOutput > 0)
            motor.set(0);
        else if (encoder.getPosition() < ClimbConstants.minPosition && lastOutput < 0)
            motor.set(0);

        SmartDashboard.putNumber("Climber location", encoder.getPosition());
    }

    public double getPosition() {
        return encoder.getPosition();
    }

    public void setPercent(double percent) {
        motor.set(percent);
    }
}
