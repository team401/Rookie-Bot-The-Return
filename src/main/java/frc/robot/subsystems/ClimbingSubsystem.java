package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbingSubsystem extends SubsystemBase {
    
    private final CANSparkMax climbingArm = new CANSparkMax(Constants.climbingArmID, MotorType.kBrushed);

    private static double topConstraint = 50; //tune
    private static double bottomConstraint = 0; //tune

    private static boolean runUp, runDown;

    public ClimbingSubsystem() {
        climbingArm.getEncoder().setPosition(0);
    }

    public void up() {
        if (runUp){
            climbingArm.set(1.0); //voltage, goes forward
        }
        else {
            stop();
        }

    }

    public void down() {
        if (runDown){
            climbingArm.set(-1.0); //voltage, goes negative
        }
        else {
            stop();
        }
    }

    public void stop() {
        climbingArm.set(0);
    }

    public void periodic() { //every 20ms check weather to runUp or runDown
        double armPos = climbingArm.getEncoder().getPosition();
        if (armPos < topConstraint) {
            runUp = true;
        } else {
            runUp = false;
        }
        if (armPos > bottomConstraint) {
            runDown = true;
        } else {
            runDown = false;
        }
        SmartDashboard.putNumber("Encoder Position", armPos);
    }


}
