package team.gif.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static team.gif.Robot.chassis;

/**
 * @author PatrickUbelhor
 */
public class Rotate extends Command {
	
	private double error;
	private double setpoint;
	private double finalTime;
	private final double timeOut;
	private final double Kp;

    public Rotate(double setpoint, double timeOut, double Kp) {
        requires(chassis);
        this.setpoint = setpoint;
        this.timeOut = timeOut;
        this.Kp = Kp;
    }

    protected void initialize() {
    	setpoint += chassis.getAngle();
    	finalTime = timeOut + Timer.getFPGATimestamp();
    }

    protected void execute() {
    	error = setpoint - chassis.getAngle();
    	chassis.drive(Kp * error, -Kp * error);
    	SmartDashboard.putNumber("Error", error);
    	SmartDashboard.putNumber("Left speed", Kp * error);
    	SmartDashboard.putNumber("Right speed", Kp * error);
    }

    protected boolean isFinished() {
    	// TODO: Set an actual condition for when the error is within tolerance
        return Timer.getFPGATimestamp() >= finalTime;
    }

    protected void end() {
    	chassis.drive(0, 0);
    }

    protected void interrupted() {
    	chassis.drive(0, 0);
    }
    
}
