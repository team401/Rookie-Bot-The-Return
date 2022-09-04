package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.robot.Constants.ArmConstonstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxPIDController.AccelStrategy;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax armMotor = new CANSparkMax(ArmConstonstants.armMotorID, MotorType.kBrushless);
    private final TalonSRX intakeMotor = new TalonSRX(ArmConstonstants.intakeMotorID);

    private final SparkMaxPIDController armController = armMotor.getPIDController();
    AccelStrategy accelStrategy = AccelStrategy.kTrapezoidal;

    public ArmSubsystem() {
        // invert motors
        // set up PID
        armMotor.getEncoder().setPosition(0.0);
        armMotor.setIdleMode(IdleMode.kBrake);
        armController.setFeedbackDevice(armMotor.getEncoder());

        
        armController.setSmartMotionAccelStrategy(AccelStrategy.kTrapezoidal, 0);
        armController.setSmartMotionAllowedClosedLoopError(0.01, 0);
        armController.setSmartMotionMaxAccel(30, 0);
        armController.setSmartMotionMaxVelocity(10, 0);
    }

    public void intake() {
        intakeMotor.set(ControlMode.PercentOutput, 0.5);
    }
    
    public void spit() {
        intakeMotor.set(ControlMode.PercentOutput, -0.5);
    }

    public void stopIntake() {
        intakeMotor.set(ControlMode.PercentOutput, 0);
    }

    public void runArmPID(double setPointRad) {
        armController.setReference(setPointRad, ControlType.kPosition);
    }
}