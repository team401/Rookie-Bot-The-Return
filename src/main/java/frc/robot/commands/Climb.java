package frc.robot.commands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.subsystems.ClimbSubsystem;

public class Climb extends CommandBase {

    private final ClimbSubsystem climb;
    private final double setpoint;
    private final ProfiledPIDController controller = 
        new ProfiledPIDController(0, 0, 0, new TrapezoidProfile.Constraints(ClimbConstants.maxVelocity, ClimbConstants.maxAccel));

    public Climb(ClimbSubsystem climb, double setpoint) {

        this.climb = climb;
        this.setpoint = setpoint;

        addRequirements(climb);

    }
    
    @Override
    public void execute() {
        
        double output = controller.calculate(climb.getPosition(), setpoint);
        climb.setPercent(output);
        
    }
    
}
