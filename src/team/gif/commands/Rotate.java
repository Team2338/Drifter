package team.gif.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static team.gif.Robot.chassis;

/**
 * @author PatrickUbelhor
 */
public class Rotate extends Command {
	
	private double error;
	private final double setpoint;
	private final double Kp;

    public Rotate(double setpoint, double Kp) {
        requires(chassis);
        this.setpoint = setpoint;
        this.Kp = Kp;
    }

    protected void initialize() {}

    protected void execute() {
    	error = setpoint - chassis.getAngle();
    	chassis.drive(-Kp * error, Kp * error);
    	SmartDashboard.putNumber("Error", error);
    	SmartDashboard.putNumber("Left speed", Kp * error);
    	SmartDashboard.putNumber("Right speed", Kp * error);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
    
}
