package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;


public class ArmSubsystem extends ProfiledPIDSubsystem {

    private final CANSparkMax motor = new CANSparkMax(ArmConstants.armMotorID, MotorType.kBrushless);
    private final RelativeEncoder encoder = motor.getEncoder();

    public ArmSubsystem() {
        super(
            new ProfiledPIDController(0, 0, 0, 
                new TrapezoidProfile.Constraints(0, 0)
            )
        );

        motor.setInverted(false);
        motor.setIdleMode(IdleMode.kCoast);

        encoder.setPosition(ArmConstants.raisedPosition); //Arm should start out raised
        encoder.setPositionConversionFactor(ArmConstants.armGearRatio);

        getController().setTolerance(ArmConstants.controllerTolerance);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("ArmPosition", encoder.getPosition());
    }

    @Override
    public void useOutput(double output, State setpoint) {
        output += ArmConstants.armFeedForward.calculate(setpoint.position, setpoint.velocity);
        motor.set(output);
    }

    @Override
    public double getMeasurement() {
        return encoder.getPosition();
    }
}
