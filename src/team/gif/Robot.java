
package team.gif;

import team.gif.commands.*;
import team.gif.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {

	public static final Drivetrain chassis = new Drivetrain();
	public SendableChooser autoChooser;
	public static OI oi;
	
	private Command autoCommand;
	private Command teleCommand;

    public void robotInit() {
		oi = new OI();
		
		// TODO: Move this to Drivetrain constructor
		chassis.initGyro();
		chassis.setSensitivity();
		
		// TODO: Check if it works if this is on same line as declaration
		autoChooser = new SendableChooser();
		autoChooser.addDefault("AntiAuto", new AntiAuto());
		autoChooser.addObject("DriveStraight", new DriveStraight());
		autoChooser.addObject("TurnBack", new TurnBack());
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
    }
    
    private void update() {
    	Scheduler.getInstance().run();
    	SmartDashboard.putNumber("Angle ", chassis.getAngle());
    	SmartDashboard.putNumber("DriveStraight offset", 0);
    	SmartDashboard.putNumber("DriveStraight speed", 0.35);
    	SmartDashboard.putNumber("DriveStraight Kp", 0.015);
    	SmartDashboard.putNumber("DriveStraight timeOut", 4);
    }
    
}
