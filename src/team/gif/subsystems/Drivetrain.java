package team.gif.subsystems;

import team.gif.Globals;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author PatrickUbelhor
 */
public class Drivetrain extends Subsystem {
    
    CANTalon leftMotor	= new CANTalon(Globals.leftDrivePort);
    CANTalon rightMotor	= new CANTalon(Globals.rightDrivePort);
    Gyro gyro			= new Gyro(Globals.gyroPort);
    
    public Drivetrain() {
    	super();
    	leftMotor.reverseOutput(Globals.leftDriveMotorInverted);
    	leftMotor.reverseSensor(Globals.leftEncoderInverted);
    	leftMotor.setPosition(0);
    	rightMotor.reverseOutput(Globals.rightDriveMotorInverted);
    	rightMotor.reverseSensor(Globals.rightEncoderInverted);
    	rightMotor.setPosition(0);
    }
    
    public final void drive(final double leftSpeed, final double rightSpeed) {
    	leftMotor.set(leftSpeed);
    	rightMotor.set(rightSpeed);
    }
    
    public final double getLeftPosition() {
    	return leftMotor.getPosition();
    }
    
    public final double getRightPosition() {
    	return rightMotor.getPosition();
    }
    
    public final void initGyro() {
    	gyro.initGyro();
    }

    public final double getAngle() {
    	return gyro.getAngle();
    }
    
    public final void setSensitivity() {
    	gyro.setSensitivity(Globals.gyroSensitivity); // TODO: Move to constructor?
    }
    
    public final void initDefaultCommand() {}
    
}
