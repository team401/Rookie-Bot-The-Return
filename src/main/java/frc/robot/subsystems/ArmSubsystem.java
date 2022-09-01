package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstonstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax armMotor = new CANSparkMax(ArmConstonstants.armMotorID, MotorType.kBrushless);
    private final TalonSRX intakeMotor = new TalonSRX(ArmConstonstants.intakeMotorID);

    private final SparkMaxPIDController armController = armMotor.getPIDController();

    public ArmSubsystem() {
        // invert motors
        // set up PID
        armMotor.getEncoder().setPosition(0.0);
        armController.setFeedbackDevice(armMotor.getEncoder());
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