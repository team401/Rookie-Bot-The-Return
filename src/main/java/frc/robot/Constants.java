// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.ArmFeedforward;

public final class Constants {
    //joystick ports
    public static final int joystickDrivePort = 0;
    public static final int joystickRotatePort = 1;
    public static final int xboxControllerPort = 2;

    //drive motor controllers
    public static final int frontLeftID = 0;
    public static final int frontRightID = 1;
    public static final int backLeftID = 2;
    public static final int backRightID = 3;


    public static final int climbingArmID = 4;
    
    public static final int intakeMotorID = 5;
    public static final int armMotorID = 6;
    
    public static final double armGearRatio = 1/16;
    public static final double topOfArm = Math.PI/2;
    public static final ArmFeedforward feedfoward = new ArmFeedforward(1, 0, 1);
    public static final double feedforwardVelocity = 1;
    
    public static final double shooterSpeed = 0.5;

    


}
