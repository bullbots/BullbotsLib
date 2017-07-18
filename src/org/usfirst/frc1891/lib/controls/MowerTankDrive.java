package org.usfirst.frc1891.lib.controls;

/**
 * 
 *
 * Experimental control method based off of a skid steering lawn mower I drive. Instead of mapping
 * speed to joysticks, I'm seeing what mapping acceleration feels like.
 * A drive would push sticks slightly forward to start, then move them back to the dead band, then to
 * drive straight (a major difficulty using normal tank) they would just adjust one stick for a second, instead of
 * trying to manage the position of both sticks at once. 
 * <p>
 * I'm not certain this experiment will work, or be better than alternatives such as cheesy drive, but I'm interested in it.
 * 
 * @author Edgar Schafer
 */
public class MowerTankDrive {
	
	
	private double kDeadband = 0.05;
    private TankDriveSignal mSignal = new TankDriveSignal(0,0);
    
    //TODO If idea pans out, try improving how ramprates work and feel, Deadbands for feel, maybe make ramprate only at fixed values (i.e. roundup function)
    
    /**
     * @param leftJoystick Left stick input ranging from -1 to 1
     * @param rightJoystick Right stick input ranging from -1 to 1
     * @return Calculate drive signal
     */
    public TankDriveSignal calculateSignal(double leftJoystick, double rightJoystick) {
    	
    	// Deadband joysticks in ensure no change to speed when driver doesn't want it.
    	leftJoystick = ControlsUtil.handleDeadband(leftJoystick, kDeadband);
    	rightJoystick = ControlsUtil.handleDeadband(rightJoystick, kDeadband);
    	
    	// Turn joystick values into a small ramp value that is used to ramp up the power over time
    	double leftRamp = leftJoystick / 100;
    	double rightRamp = rightJoystick / 100;
    	
    	// limit the output to a maximum of 1 to - 1
    	double leftPower = ControlsUtil.limit(mSignal.leftMotor + leftRamp, 1);
    	double rightPower = ControlsUtil.limit(mSignal.rightMotor + rightRamp, 1);
    	
    	// deadbands low motor power to make stopping easier, TODO test this
    	mSignal.leftMotor = ControlsUtil.handleDeadband(leftPower, kDeadband);
    	mSignal.rightMotor = ControlsUtil.handleDeadband(rightPower, kDeadband);
    	
    	return mSignal;
    }
    
    /**
     * @return last calculated drive signal
     */
    public TankDriveSignal getSignal(){
    	return mSignal;
    }
}
