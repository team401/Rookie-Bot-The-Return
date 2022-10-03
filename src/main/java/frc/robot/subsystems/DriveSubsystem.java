package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
    
    private final WPI_VictorSPX frontleft = new WPI_VictorSPX(Constants.frontLeftID);
    private final WPI_VictorSPX frontright = new WPI_VictorSPX(Constants.frontRightID);
    private final WPI_VictorSPX backleft = new WPI_VictorSPX(Constants.backLeftID);
    private final WPI_VictorSPX backright = new WPI_VictorSPX(Constants.backRightID);

    private final MotorControllerGroup left = new MotorControllerGroup(frontleft, backleft);
    private final MotorControllerGroup right = new MotorControllerGroup(frontright, backright);

    private final DifferentialDrive drive = new DifferentialDrive(left, right);

    public DriveSubsystem() {   
    }

    public void drive(double forward, double turn) { 
        drive.arcadeDrive(forward, turn);
    }
}
