package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class OperatorControl extends CommandBase {
    private DriveSubsystem drive;

    private DoubleSupplier forward;
    private DoubleSupplier rotate;

    public OperatorControl(DriveSubsystem subsystem, DoubleSupplier fwd, DoubleSupplier rot){
        drive = subsystem;

        forward = fwd;
        rotate = rot;

        addRequirements(drive);
    }
    
    @Override
    public void execute() {
        drive.arcadeDrive(forward.getAsDouble(), rotate.getAsDouble() - 0.2);
    }
}