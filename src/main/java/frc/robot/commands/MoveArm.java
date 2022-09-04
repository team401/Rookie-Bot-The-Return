package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;


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
        arm.runArmPID(setpoint); //TODO: learn how this works
    }


}