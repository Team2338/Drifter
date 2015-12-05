package team.gif.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author PatrickUbelhor
 */
public class TurnBack extends CommandGroup {
    
    public  TurnBack() {
        addSequential(new DriveStraight(90, 4.5));
        addSequential(new DriveStraight(180, 6));
    }
}
