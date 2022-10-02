package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
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
        left.setInverted(true);
        //frontLeft.setInverted(true);
        // backLeft.setInverted(true);
        
        frontLeft.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);
        
    }

    /**
     * Drives the robot as specified.
     * 
     * This Javadoc? Unecessary?? What could you possibly be talking about?
     * 
     * @param forward forward speed
     * @param rotation rotation value
     */
    public void arcadeDrive(double forward, double rotation){
        drive.arcadeDrive(forward, -rotation);
    }

    public void tankDrive(double left, double right) {
        drive.tankDrive(left, right);
    }

}
