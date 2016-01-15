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
	
	// Constants
	public static final double gyroSensitivity = 0.00667; // volts per degree per second
	public static final double ticksPerInch = 0; // FIXME: Calculate actual value
	public static final double robotWidth = 0; // In ticks. FIXME: need to calculate
	public static final double kinematicMaxSpeed = 0.6;
	
	// Functionality
	public static final boolean leftDriveMotorInverted = false; // FIXME: Integrate this
	public static final boolean rightDriveMotorInverted = false; // FIXME: Integrate this
	public static final boolean leftEncoderInverted = false;
	public static final boolean rightEncoderInverted = false;
	
}
