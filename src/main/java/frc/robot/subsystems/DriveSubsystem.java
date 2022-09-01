package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.DrivetrainConstants;

public class DriveSubsystem extends SubsystemBase {
    private final WPI_VictorSPX frontLeft = new WPI_VictorSPX(DrivetrainConstants.frontLeftID);
    private final WPI_VictorSPX frontRight = new WPI_VictorSPX(DrivetrainConstants.frontRightID);
    private final WPI_VictorSPX backLeft =  new WPI_VictorSPX(DrivetrainConstants.backLeftID);
    private final WPI_VictorSPX backRight = new WPI_VictorSPX(DrivetrainConstants.backRightID);

    private final MotorControllerGroup left = new MotorControllerGroup(frontLeft, backLeft);
    private final MotorControllerGroup right = new MotorControllerGroup(frontRight, backRight);

    private final DifferentialDrive drive = new DifferentialDrive(left, right);

    public DriveSubsystem() {
        //invert motors if necessary
    }

    public void drive(double forward, double rotation){
        drive.arcadeDrive(forward, rotation);
    }
}
