// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
	public static final class DrivetrainConstants {
		public static final int frontLeftID = 1;
		public static final int backLeftID = 2;
		public static final int frontRightID = 3;
		public static final int backRightID = 4;

		public static final int pigeonID = 20;

		public static final double autoDrivePercent = 0.6;
	}

	public static final class ArmConstants {
		public static final int armMotorID = 1;
		public static final int intakeMotorID = 32;

		public static final double controllerTolerance = 0.01;

		public static final double shooterSpeed = -1;
		public static final double intakeSpeed = 0.6;

		public static final double maxVelocity = 0.6;
		public static final double maxAccel = 0.3;

		public static final double armGearRatio = 1.0 / 75;

		public static final double loweredPosition = -0.02;
		public static final double raisedPosition = 0.31;
	}

	public static final class ClimbConstants {

		public static final int climbArmID = 11;

        // 5ft 6in is 53000
        public static final double offset = 0;//13450;
        public static final double maxPosition = 44000;
		public static final double minUnconfirmedPosition = 24000;
        public static final double minPosition = 2200;

		public static final double maxVelocity = 1;
		public static final double maxAccel = maxVelocity * 2;
	}
}
