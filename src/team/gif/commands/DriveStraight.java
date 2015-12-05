package team.gif.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static team.gif.Robot.chassis;

/**
 * @author PatrickUbelhor
 */
public class DriveStraight extends Command {
	
	private double desiredAngle = 0;
	private double offset = 0;
	private double initTime = 0;
	private double error = 0;
	private double leftSpeed = 0.5;
	private double rightSpeed = 0.5;
	private final double Kp = 0.015;
	private final double desiredTime;

    public DriveStraight(double offset, double time) {
        requires(chassis);
        this.offset = offset;
        desiredTime = time;
    }

    protected void initialize() {
    	desiredAngle = chassis.getAngle() + offset;
    	SmartDashboard.putNumber("Desired ", desiredAngle);
    	initTime = Timer.getFPGATimestamp();
    }

    protected void execute() {
    	error = chassis.getAngle() - desiredAngle;
    	
    	leftSpeed = -0.35 + (Kp * error);
    	rightSpeed = -0.35 - (Kp * error);
    	
    	chassis.drive(leftSpeed, rightSpeed);
    	SmartDashboard.putNumber("Error ", error);
    	SmartDashboard.putNumber("left ", leftSpeed);
    	SmartDashboard.putNumber("right ", rightSpeed);
    	
    }

    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - initTime >= desiredTime;
    }

    protected void end() {
    	chassis.drive(0, 0);
    }

    protected void interrupted() {
    	chassis.drive(0, 0);
    }
    
}
