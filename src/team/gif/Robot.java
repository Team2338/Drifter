
package team.gif;

import team.gif.commands.*;
import team.gif.subsystems.Drivetrain;
import java.io.IOException;
import java.text.DecimalFormat;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {

	private static Logger logger;
	private static DecimalFormat df = new DecimalFormat("##.00");
	public static final Drivetrain chassis = new Drivetrain();
	public SendableChooser autoChooser;
	public static OI oi;
	public static final GIFDashboard dash = new GIFDashboard();
	
	private Command autoCommand;
	private Command teleCommand;

    public void robotInit() {
    	try {
			logger = new Logger();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		oi = new OI();
		
		// TODO: Move this to Drivetrain constructor
		chassis.initGyro();
		chassis.setSensitivity();
		
		// TODO: Check if it works if this is on same line as declaration
		autoChooser = new SendableChooser();
		autoChooser.addDefault("AntiAuto", new AntiAuto());
		autoChooser.addObject("DriveStraight", new DriveStraight());
		autoChooser.addObject("TurnBack", new TurnBack());
		autoChooser.addObject("Rotate", new Rotate(270, 0.015, 4));
		autoChooser.addObject("Kinematic", new Kinematic(90, 1000));
		SmartDashboard.putData("Auto Mode", autoChooser);
		
		teleCommand = new TankDrive();
    }
	
	public void disabledPeriodic() {
		update();
	}

    public void autonomousInit() {
    	if (autoChooser.getSelected() != null) {
    		autoCommand = (Command) autoChooser.getSelected();
    		autoCommand.start();
    	}
    }

    public void autonomousPeriodic() {
        update();
    }

    public void teleopInit() {
    	if (autoCommand != null) autoCommand.cancel();
    	teleCommand.start();
    }

    public void teleopPeriodic() {
        update();
        logger.write(df.format(chassis.getAngle()) + ", ");
        logger.write(df.format(chassis.getLeftPosition()) + ",");
        logger.write(df.format(chassis.getRightPosition()) + "\n");
    }
    
    private void update() {
    	Scheduler.getInstance().run();
    	SmartDashboard.putNumber("Angle ", chassis.getAngle());
    }
    
}
