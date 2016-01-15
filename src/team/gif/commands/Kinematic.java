package team.gif.commands;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.Globals;
import static team.gif.Robot.chassis;

/**
 * @author PatrickUbelhor
 */
public class Kinematic extends Command {
	
	private double initAngle;
	private double initLeftPos;
	private double initRightPos;
	private double leftArcLength;
	private double rightArcLength;
	private double leftKp = 0.5;
	private double rightKp = 0.5;
	private double angleKp = 0.002;
	private double leftError = 0;
	private double rightError = 0;
	private double angleError = 0;
	private double leftSpeed = 0;
	private double rightSpeed = 0;
	private final double anglePerLeftTick;

	/**
	 * @param deltaAngle	Desired change of angle of the robot during the turn
	 * @param radius		Radius of the turn from the inner side of the robot (ticks)
	 * 
	 * If deltaAngle is 0, the robot will drive in a straight line for the length of the radius.
	 */
    public Kinematic(final double deltaAngle, final double radius) {
        requires(chassis);
        if (deltaAngle > 0) {
        	leftArcLength	= radius * Math.toRadians(deltaAngle);
        	rightArcLength	= (radius + Globals.robotWidth) * Math.toRadians(deltaAngle);
        	anglePerLeftTick = Math.toDegrees(1 / radius);
        } else if (deltaAngle < 0) {
        	rightArcLength	= radius * Math.toRadians(deltaAngle);
        	leftArcLength	= (radius + Globals.robotWidth) * Math.toRadians(deltaAngle);
        	anglePerLeftTick = Math.toDegrees(1 / (radius + Globals.robotWidth));
        } else {
        	leftArcLength	= radius;
        	rightArcLength	= radius;
        	anglePerLeftTick = 0;
        }
    }
    
    protected void initialize() {
    	initAngle		= chassis.getAngle();
    	initLeftPos		= chassis.getLeftPosition();
    	initRightPos	= chassis.getRightPosition();
    }

    protected void execute() {
    	leftError = (initLeftPos + leftArcLength) - chassis.getLeftPosition();
    	rightError = (initRightPos + rightArcLength) - chassis.getRightPosition();
    	angleError = (initAngle + (anglePerLeftTick * (chassis.getLeftPosition() - initLeftPos))) -
    					(chassis.getAngle() - initAngle);
    	
    	leftSpeed	= leftError	* leftKp;
    	rightSpeed	= rightError * rightKp;
    	if (Math.abs(leftSpeed) > 1)	leftSpeed = leftSpeed / Math.abs(leftSpeed);
    	if (Math.abs(rightSpeed) > 1)	rightSpeed = rightSpeed / Math.abs(rightSpeed);
    	
    	leftSpeed += angleError * angleKp;
    	rightSpeed -= angleError * angleKp;
    	
    	chassis.drive(leftSpeed, rightSpeed);
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
    
}
