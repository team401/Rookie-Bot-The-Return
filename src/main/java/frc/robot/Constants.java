// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.ArmFeedforward;

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
        public static final int frontLeftID = 0;
        public static final int frontRightID = 1;
        public static final int backLeftID = 2;
        public static final int backRightID = 3;
    }

    public static final class ArmConstants {
        public static final int armMotorID = 4;
        public static final int intakeMotorID = 5;

        //TODO: Tune

        public static final double shooterSpeed = 0.5;

        public static final double armGearRatio = 1;

        public static final ArmFeedforward armFeedForward = new ArmFeedforward(0, 0, 0, 0);

        public static final double lowerPosition = 100;
    }

    public static final class ClimbConstants {
        public static final int leftClimbID = 6;
        public static final int rightClimbID = 7;

        public static final int climbArmID = 6;
        
        //TODO: Tune

        public static final double leftUpperLimit = 100;
        public static final double rightUpperLimit = 100;

        public static final double upperLimit = 100;
        public static final double lowerLimit = 0;
    }
}
