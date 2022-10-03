//arm for this is brushless
package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
    private final WPI_VictorSPX intakeMotor = new WPI_VictorSPX(Constants.intakeMotorID);
    private final CANSparkMax armMotor = new CANSparkMax(Constants.armMotorID, MotorType.kBrushless);

    private final RelativeEncoder armEncoder = armMotor.getEncoder();

    private double intakeMotorSpeed = 1.0;

    private final ProfiledPIDController armController = new ProfiledPIDController(0.0, 0.0, 0.0,
        new TrapezoidProfile.Constraints(
            Units.rotationsPerMinuteToRadiansPerSecond(30), 
            Units.rotationsPerMinuteToRadiansPerSecond(60))); //trapezoid motion porfiling takes radians per second as parameter, easier to think about in rotations per second (speed of 1 rotation/2 sec, set point of 90 deg)
    
    public ArmSubsystem(){
        armMotor.setInverted(false); //set phase of MotorFeedbackSensor to be inline with phase of motor
        armMotor.setIdleMode(IdleMode.kBrake); //brake: sparkmax shorts all wires to cut charge, idle: 

        armEncoder.setPosition(0); //set current position (which should be down) to 0, default # rotations of motor
        armEncoder.setPositionConversionFactor(Constants.armGearRatio); //sets conversion factor of encoder to be useful

        armController.setTolerance(0.01); //within x of the goal, says it is the goal
        
        intakeMotorSpeed = SmartDashboard.getNumber("Intake Motor Speed [-1, 1]", 1.0);
    }

    public void collect(){
        //TUNE this and shoot
        intakeMotor.set(ControlMode.PercentOutput, intakeMotorSpeed);
    }
    public void shoot(){
        intakeMotor.set(ControlMode.PercentOutput, -intakeMotorSpeed); 
    }
    public void stop(){
        intakeMotor.set(ControlMode.PercentOutput, 0);

    }
    public void runArmPID(double setPointRad){

        armMotor.set(
            armController.calculate(Units.rotationsToRadians(armEncoder.getPosition()), setPointRad) 
            +
            Constants.feedfoward.calculate(setPointRad, Constants.feedforwardVelocity));
    }
    public void periodic() {
        SmartDashboard.putNumber("Arm Encoder Position", armEncoder.getPosition());
    }
}