package team.gif.commands;

import java.text.DecimalFormat;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static team.gif.Robot.chassis;

/**
 * @author PatrickUbelhor
 */
public class DriveStraight extends Command {
	
	private double angleSetpoint;
	private double timeOut;
	private double initTime;
	private double Kp;
	private double error;
	private double baseSpeed;
	private double leftSpeed;
	private double rightSpeed;
	private final boolean userSpecifiedValues;
	private final DecimalFormat df = new DecimalFormat("##.00");

    public DriveStraight() {
        requires(chassis);
        userSpecifiedValues = true;
    }
    
    public DriveStraight(double timeOut, double speed, double Kp) {
    	requires(chassis);
    	this.timeOut = timeOut;
    	baseSpeed = speed;
    	this.Kp = Kp;
    	userSpecifiedValues = false;
    }

    protected void initialize() {
    	if (userSpecifiedValues) {
    		angleSetpoint	= chassis.getAngle() + SmartDashboard.getNumber("DriveStraight offset", 0);
        	baseSpeed		= SmartDashboard.getNumber("DriveStraight speed", 0.35);
        	Kp				= SmartDashboard.getNumber("DriveStraight Kp", 0.015);
        	timeOut			= SmartDashboard.getNumber("DriveStraight timeOut", 4);
    	} else {
    		SmartDashboard.putNumber("DriveStraight offset", angleSetpoint);
    		SmartDashboard.putNumber("DriveStraight speed", baseSpeed);
    		SmartDashboard.putNumber("DriveStraight Kp", Kp);
    		SmartDashboard.putNumber("DriveStraight timeOut", timeOut);
    		angleSetpoint += chassis.getAngle();
    	}
    	
    	SmartDashboard.putNumber("Angle Setpoint", angleSetpoint);
    	initTime = Timer.getFPGATimestamp();
    }

    protected void execute() {
    	error = angleSetpoint - chassis.getAngle();
    	
    	leftSpeed = -baseSpeed - (Kp * error);
    	rightSpeed = -baseSpeed + (Kp * error);
    	
    	chassis.drive(leftSpeed, rightSpeed);
    	SmartDashboard.putNumber("Error", error);
    	SmartDashboard.putNumber("Left speed", leftSpeed);
    	SmartDashboard.putNumber("Right speed", rightSpeed);
    	SmartDashboard.putNumber("DriveStraight timeOut",
    			Double.parseDouble(df.format(timeOut))); // Time remaining
    	
    }

    protected boolean isFinished() {
    	// TODO: Make more efficient by adding initTime to timeOut earlier in code, so the
    	// math operation only runs once. Probably not necessary, but whatever.
        return Timer.getFPGATimestamp() - initTime >= timeOut;
    }

    protected void end() {
    	chassis.drive(0, 0);
    }

    protected void interrupted() {
    	chassis.drive(0, 0);
    }
    
}
