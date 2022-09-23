package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

/*
 * Temporarily: DO NOT USE. We need to work out logic for ending this command when a new setpoint is
 * needed. Use arm.runArmPID inside button binding for now.
 */


public class MoveArm extends CommandBase {
    private ArmSubsystem arm;

    private double setpoint;

    public MoveArm(ArmSubsystem arm, double setpoint) {
        this.arm = arm;
        this.setpoint = setpoint;
        addRequirements(this.arm);
    }

    @Override
    public void execute() {
        arm.runArmPID(setpoint);
    }
}