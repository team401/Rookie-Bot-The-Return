package frc.robot.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.ArmSubsystem;

public class MoveArm extends ProfiledPIDCommand {

    public MoveArm(ArmSubsystem arm, double goal) {
        
        super(
            new ProfiledPIDController(
                25, 0, 0,
                new TrapezoidProfile.Constraints(
                    ArmConstants.maxVelocity,
                    ArmConstants.maxAccel)),
                    // Close loop on heading
                    arm::getPosition,
                    // Set reference to target
                    goal,
                    // Pipe output to turn robot
                    (output, setpoint) -> {arm.setPercent(output); SmartDashboard.putNumber("Setpoint", setpoint.position);},
                    // Require the drive
                    arm);
        
        SmartDashboard.putNumber("Goal", goal);
        
        getController().setTolerance(0.01);

    }

}

/*
public class MoveArm extends CommandBase {

    private final ArmSubsystem arm;
    private final double setpoint;
    private final ProfiledPIDController controller = new ProfiledPIDController(3, 0, 0, 
                                                    new TrapezoidProfile.Constraints(ArmConstants.maxVelocity, ArmConstants.maxAccel));

    public MoveArm(ArmSubsystem arm, double setpoint) {

        this.arm = arm;
        this.setpoint = setpoint;

        controller.reset(arm.getPosition());//

        addRequirements(arm);

    }

    @Override
    public void execute() {

        double output = controller.calculate(arm.getPosition(), setpoint);
        double ff = 1.4 * Math.cos(Units.rotationsToRadians(arm.getPosition())) / 12;
        ff = ff < 0 ? 0 : ff;
        arm.setPercent(output + ff);
        SmartDashboard.putNumber("Output", output);
        SmartDashboard.putNumber("FeedForward", ff);
        
    }

    
}
*/