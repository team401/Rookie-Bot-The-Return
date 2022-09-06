package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ClimbConstants;

public class ClimbSubsystem extends SubsystemBase {
    private static final CANSparkMax leftClimb = new CANSparkMax(ClimbConstants.leftClimbID, MotorType.kBrushless);
    private static final CANSparkMax rightClimb = new CANSparkMax(ClimbConstants.rightClimbID, MotorType.kBrushless);

    private static boolean isAtTop;
    private static boolean isAtBottom;

    private static double leftTop = ClimbConstants.leftUpperLimit;
    private static double rightTop = ClimbConstants.rightUpperLimit;
    
    public ClimbSubsystem() {
        leftClimb.getEncoder().setPosition(0);
        rightClimb.getEncoder().setPosition(0);

        isAtTop = false;
        isAtBottom = false;
    }
    
    public void climbUp() {
        if (isAtTop) {
            climbStop();
        } else {
            leftClimb.set(1); //TODO: adjust climb speed
            rightClimb.set(1);
        }
    }

    public void climbDown() {
        if (isAtBottom) {
            climbStop();
        } else {
            leftClimb.set(-1);
            rightClimb.set(-1);
        }
    }

    public void climbStop() {
        leftClimb.set(0);
        rightClimb.set(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Arm Encoder", leftClimb.getEncoder().getPosition());
        SmartDashboard.putNumber("Right Arm Encoder", rightClimb.getEncoder().getPosition());

        if (leftClimb.getEncoder().getPosition() > leftTop || rightClimb.getEncoder().getPosition() > rightTop) {
            isAtTop = true;
        } else {
            isAtTop = false; 
        }

        if (leftClimb.getEncoder().getPosition() <= 0 || rightClimb.getEncoder().getPosition() <= 0) {
            isAtBottom = true;
        } else {
            isAtBottom = false;
        }
    }
}
