package team.gif;

/**
 * This file is a list of global variables and port mappings.
 * Editing these variables will change the behavior of the robot.
 */
public final class Globals {
	
	// PWM
	public static final int leftDrivePort = 1;
	public static final int rightDrivePort = 2;
	
	// Analog Input
	public static final int gyroPort = 1; // Must be either 0 or 1
	
	// DIO
	// TODO: Attach encoders to the robot
//	public static final int leftEncoderPort = 1;
//	public static final int rightEncoderPort = 2;
	
	// Other
	public static final double gyroSensitivity = 0.00667; // volts per degree per second
	
}
