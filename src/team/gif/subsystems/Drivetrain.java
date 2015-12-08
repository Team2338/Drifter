package team.gif.subsystems;

import team.gif.Globals;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author PatrickUbelhor
 */
public class Drivetrain extends Subsystem {
    
    Talon left = new Talon(Globals.leftDrivePort);
    Talon right = new Talon(Globals.rightDrivePort);
    Gyro gyro = new Gyro(Globals.gyroPort);
    
    public Drivetrain() {
    	super();
    }
    
    // TODO: set motor invert in constructor w/ global variable, rather than hardcoded in like this.
    public void drive(double leftSpeed, double rightSpeed) {
    	left.set(leftSpeed);
    	right.set(-rightSpeed);
    }
    
    public void initGyro() {
    	gyro.initGyro();
    }

    public double getAngle() {
    	return gyro.getAngle();
    }
    
    public void setSensitivity() {
    	gyro.setSensitivity(Globals.gyroSensitivity);
    }
    
    public void initDefaultCommand() {}
    
}
