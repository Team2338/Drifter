package team.gif.subsystems;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author PatrickUbelhor
 */
public class Drivetrain extends Subsystem {
    
    Talon left = new Talon(1);
    Talon right = new Talon(2);
    Gyro gyro = new Gyro(1);
    
    public Drivetrain() {
    	super();
    }
    
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
    	gyro.setSensitivity(0.00667);
    }
    
    public void initDefaultCommand() {}
    
}
