package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ClimbConstants;

public class ClimbSubsystem extends SubsystemBase {
    private static final CANSparkMax climbOne = new CANSparkMax(ClimbConstants.climbOneID, MotorType.kBrushless);
    private static final CANSparkMax climbTwo = new CANSparkMax(ClimbConstants.climbOneID, MotorType.kBrushless);

    private static boolean isAtTop;
    private static boolean isAtBottom;
    
    public ClimbSubsystem() {
        climbOne.getEncoder().setPosition(0);
        climbTwo.getEncoder().setPosition(0);

        isAtTop = false;
        isAtBottom = false;
    }
    
    public void climbUp() {
        if (isAtTop) {
            climbOne.set(0);
            climbTwo.set(0);
        } else {
            climbOne.set(1);
            climbTwo.set(1);
        }
    }

    public void climbDown() {
        if (isAtBottom) {
            climbOne.set(0);
            climbTwo.set(0);
        } else {
            climbOne.set(-1);
            climbTwo.set(-1);
        }
    }

    public void climbStop() {
        climbOne.set(0);
        climbTwo.set(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Arm Encoder", climbOne.getEncoder().getPosition());
        SmartDashboard.putNumber("Right Arm Encoder", climbTwo.getEncoder().getPosition());

        if (climbOne.getEncoder().getPosition() > 10 || climbTwo.getEncoder().getPosition() > 10) {
            isAtTop = true;
        } else {
            isAtTop = false; 
        }

        if (climbOne.getEncoder().getPosition() < 1 || climbTwo.getEncoder().getPosition() < 1) {
            isAtBottom = true;
        } else {
            isAtBottom = false;
        }
    }
}
