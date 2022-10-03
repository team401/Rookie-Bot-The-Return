package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class OperatorControl extends CommandBase{
    private final DriveSubsystem drive;
    private final double forward;
    private final double rotation;

    public OperatorControl(DriveSubsystem drivein, DoubleSupplier fwd, DoubleSupplier rot) {
        drive = drivein;
        forward = fwd.getAsDouble();
        rotation = rot.getAsDouble();
        addRequirements(drive);   
    }

    @Override
    public void execute() {
        drive.drive(forward, rotation);
    }


}
