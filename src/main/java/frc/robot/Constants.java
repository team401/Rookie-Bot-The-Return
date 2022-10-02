// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DrivetrainConstants {
        public static final int frontLeftID = 1;
        public static final int backLeftID = 2;
        public static final int frontRightID = 3;
        public static final int backRightID = 4;
    }

    public static final class ArmConstants {
        public static final int armMotorID = 1;
        public static final int intakeMotorID = 32;

        //TODO: Tune

        public static final double controllerTolerance = 0.01;

        public static final double shooterSpeed = 0.5;

        public static final double maxVelocity = Units.rotationsPerMinuteToRadiansPerSecond(30);
        public static final double maxAccel = maxVelocity * 4;

        public static final double armGearRatio = 1.0 / 60;

        public static final ArmFeedforward armFeedForward = new ArmFeedforward(0, 0, 0, 0);

        public static final double loweredPosition = 0;
        public static final double raisedPosition = 100;
    }

    public static final class ClimbConstants {
        public static final int leftClimbID = 6;
        public static final int rightClimbID = 7;

        public static final int climbArmID = 6;
        
        //TODO: Tune
        public static final double climbVolts = 4; //Should maaaaybe be a percentage, because SparkMaxes only accept percentages

        public static final double maxPosition = 100;
        public static final double minPosition = 5;
    }
}
