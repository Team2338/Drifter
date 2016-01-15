package team.gif;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import team.gif.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private static final Joystick leftStick = new Joystick(0);
	private static final Joystick rightStick = new Joystick(1);
	
	private final Button rightTrigger;
	@SuppressWarnings("unused")
	private final Button right2;
	@SuppressWarnings("unused")
	private final Button right3;
    
	public OI() {
		rightTrigger = new JoystickButton(rightStick, 1);
		right2 = new JoystickButton(rightStick, 2);
		right3 = new JoystickButton(rightStick, 3);
		
		rightTrigger.whenPressed(new TankDrive());
		
	}
	
	public static final double getLeftStickY() {
		return -leftStick.getY();
	}
	
	public static final double getRightStickY() {
		return -rightStick.getY();
	}
	
}
