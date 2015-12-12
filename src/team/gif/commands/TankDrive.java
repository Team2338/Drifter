package team.gif.commands;

import team.gif.OI;
import team.gif.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author PatrickUbelhor
 */
public class TankDrive extends Command {

    public TankDrive() {
        requires(Robot.chassis);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.chassis.drive(OI.getLeftStickY(), OI.getRightStickY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.chassis.drive(0, 0);
    }

    protected void interrupted() {
    	Robot.chassis.drive(0, 0);
    }
    
}
