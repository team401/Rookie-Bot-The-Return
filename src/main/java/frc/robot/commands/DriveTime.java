package frc.robot.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveTime extends CommandBase {
    private final DriveSubsystem drive;
    private final double timeS;
    private final Timer timer = new Timer();

    public DriveTime(DriveSubsystem drive, double timeS) {
        this.drive = drive;
        this.timeS = timeS;
    }
    
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }
    
    @Override
    public void execute() {
        drive.arcadeDrive(-DrivetrainConstants.autoDrivePercent, 0);
    }
    
    @Override
    public boolean isFinished() {
        return timer.hasElapsed(timeS);
    }

    @Override
    public void end(boolean isFinished) {
        drive.arcadeDrive(0, 0);
    }
    
}
