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
	// TODO: Make this terminate within a tolerance, NOT a time-out value
	// TODO: Distance PID should replace base speed!
	
	private double timeOut;
	private double finishTime;
	private double angleP;
	private double angleSetpoint;
	private double angleError;
	private double distP;
	private double distSetpoint; // FIXME: Implement this.
	private double distError;
	private double leftSpeed;
	private double rightSpeed;
	private final boolean userSpecifiedValues;
	private final DecimalFormat df = new DecimalFormat("##.00");

    public DriveStraight() {
        requires(chassis);
        userSpecifiedValues = true;
    }
    
    public DriveStraight(double timeOut, double distance,
    					 double angleP,
    					 double distP) {
    	requires(chassis);
    	this.timeOut	= timeOut;
    	this.angleP		= angleP;
    	this.distP		= distP;
    	distSetpoint	= distance;
    	userSpecifiedValues = false;
    }

    // Initializes variables needed for PID loop.
    protected void initialize() {
    	if (userSpecifiedValues) {
    		angleSetpoint	= chassis.getAngle();
        	angleP			= SmartDashboard.getNumber("DriveStraight angleP", 0.015);
        	distP			= SmartDashboard.getNumber("DriveStraight distP", 0);
        	timeOut			= SmartDashboard.getNumber("DriveStraight timeOut", 4);
        	distSetpoint	= SmartDashboard.getNumber("DriveStraight distance", 1000) +
        					  ((chassis.getLeftPosition() + chassis.getRightPosition()) / 2);
    	} else {
    		SmartDashboard.putNumber("DriveStraight angleP", angleP);
    		SmartDashboard.putNumber("DriveStraight timeOut", timeOut);
    		SmartDashboard.putNumber("DrieStraight distance", distSetpoint);
    		angleSetpoint = chassis.getAngle();
    	}
    	
    	finishTime = Timer.getFPGATimestamp() + timeOut;
    }

    protected void execute() {
    	angleError = angleSetpoint - chassis.getAngle();
    	distError = distSetpoint - ((chassis.getLeftPosition() + chassis.getRightPosition()) / 2);
    	
    	leftSpeed = (distP * distError) + (angleP * angleError);
    	rightSpeed = (distP * distError) - (angleP * angleError);
    	
    	chassis.drive(leftSpeed, rightSpeed);
    	SmartDashboard.putNumber("Error", angleError);
    	SmartDashboard.putNumber("Left speed", leftSpeed);
    	SmartDashboard.putNumber("Right speed", rightSpeed);
    	SmartDashboard.putNumber("DriveStraight timeOut", Double.parseDouble(df.format(timeOut)));
    	
    }

    protected boolean isFinished() {
        return Timer.getFPGATimestamp() >= finishTime;
    }

    protected void end() {
    	chassis.drive(0, 0);
    }

    protected void interrupted() {
    	chassis.drive(0, 0);
    }
    
}
