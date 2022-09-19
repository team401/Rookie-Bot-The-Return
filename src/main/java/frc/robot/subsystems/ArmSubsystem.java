package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.robot.Constants.ArmConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax armMotor = new CANSparkMax(ArmConstants.armMotorID, MotorType.kBrushless);
    private final WPI_VictorSPX intakeMotor = new WPI_VictorSPX(ArmConstants.intakeMotorID);

    private final RelativeEncoder armEncoder = armMotor.getEncoder();

    private final ProfiledPIDController armController = new ProfiledPIDController(
        0.0, 0.0, 0.0, //TODO: Tune
        new TrapezoidProfile.Constraints(
            Units.rotationsPerMinuteToRadiansPerSecond(30) * ArmConstants.armGearRatio,
            Units.rotationsPerMinuteToRadiansPerSecond(60)));


    public ArmSubsystem() {
        armMotor.setInverted(false);
        armMotor.setIdleMode(IdleMode.kBrake);

        armEncoder.setPosition(0);
        armEncoder.setPositionConversionFactor(ArmConstants.armGearRatio);

        armController.setTolerance(0.01);
    }

    public void intake() {
        intakeMotor.set(ControlMode.PercentOutput, ArmConstants.shooterSpeed);
    }
    
    public void spit() {
        intakeMotor.set(ControlMode.PercentOutput, -ArmConstants.shooterSpeed);
    }

    public void shoot() {
        intakeMotor.set(ControlMode.PercentOutput, -ArmConstants.shooterSpeed / 2);
    }

    public void stop() {
        intakeMotor.set(ControlMode.PercentOutput, 0);
    }

    public void runArmPID(double setPointRad) {
        //double output = armController.calculate(armEncoder.getPosition(), setPointRad);

        //TODO: Feedforward - Hopefully works :)
        double output = ArmConstants.armFeedForward.calculate(setPointRad, ArmConstants.acceleration, ArmConstants.velocity);

        armMotor.set(output);
    }
}