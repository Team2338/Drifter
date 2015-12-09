package team.gif.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author PatrickUbelhor
 */
public class TurnBack extends CommandGroup {
    
    public  TurnBack() {
        addSequential(new DriveStraight(0, 1.3, 0.35, 0.015));
        addSequential(new DriveStraight(90, 1.5, 0, 0.015));
        addSequential(new DriveStraight(0, 1.3, 0.35, 0.015));
        addSequential(new DriveStraight(90, 1.5, 0, 0.015));
        addSequential(new DriveStraight(0, 1.3, 0.35, 0.015));
    }
}
