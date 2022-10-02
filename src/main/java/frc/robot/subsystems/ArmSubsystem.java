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
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;


public class ArmSubsystem extends SubsystemBase {

    private final CANSparkMax motor = new CANSparkMax(ArmConstants.armMotorID, MotorType.kBrushless);
    private final RelativeEncoder encoder = motor.getEncoder();

    public ArmSubsystem() {
        motor.setInverted(false);
        motor.setIdleMode(IdleMode.kCoast);

        encoder.setPositionConversionFactor(ArmConstants.armGearRatio);
        encoder.setPosition(ArmConstants.raisedPosition); //Arm should start out raised
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("ArmPosition", encoder.getPosition());
    }

    public void reset() {
        encoder.setPosition(0);
    }

    public void setPercent(double output) {
        motor.set(output);
    }

    public double getPosition() {
        return encoder.getPosition();
    }

    public double getVelocity() {
        return encoder.getVelocity();
    }
}
