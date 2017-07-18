package org.usfirst.frc1891.lib.controls;

/**
 * @author Edgar Schafer but basically all the code is copied from cheesy poofs because they don't comment their code well and I don't know how the thing works.
 * 
 * Takes user input and turns it into basic drive train commands using team 254 The Cheesy Poofs
 * Cheesy Drive.
 */
public class CheesyDriveInterpreter {
	
	private double mQuickStopAccumulator;
    private double kThrottleDeadband = 0.02;
    private double kWheelDeadband = 0.02;
    private double kTurnSensitivity = 1.0;
    private TankDriveSignal mSignal = new TankDriveSignal(0,0);
    
    /**
     * A Drive Interpreter will calculate drivetrain outputs based on human input. 
     * Cheesy Drive (designed by team 254 the cheesy poofs) Seperates throttle and turn inputs
     * to different sticks, then calculates turns as curving the robot path. It also includes a
     * Quickturn button that allows the robot to rotate in place.
     * 
     * @param throttleDeadband 
     * @param turnDeadband
     */
    public CheesyDriveInterpreter(double throttleDeadband, double turnDeadband) {
    	kThrottleDeadband = throttleDeadband;
    	kWheelDeadband = turnDeadband;
    }
    
    /**
     * @param throttle Throttle stick input ranging from -1 to 1
     * @param wheel Turning stick input ranging from -1 to 1
     * @param isQuickTurn Quick turn button input
     * @return returns The drive signal for the drive system to respond to.
     */
    public TankDriveSignal calculateSignal(double throttle, double wheel, boolean isQuickTurn) {

        wheel = ControlsUtil.handleDeadband(wheel, kWheelDeadband);
        throttle = ControlsUtil.handleDeadband(throttle, kThrottleDeadband);

        double overPower;

        double angularPower;

        if (isQuickTurn) {
        	//This appears to power an advanced adaptive response system that helps you exit really slow tight turns
            if (Math.abs(throttle) < 0.2) {
                double alpha = 0.1;
                mQuickStopAccumulator = (1 - alpha) * mQuickStopAccumulator + alpha * ControlsUtil.limit(wheel, 1.0) * 2;
                // (0.9 * previous) + (0.2 * wheel)
            }
            // This is used below to change motor output for quick turning
            overPower = 1.0;
            // This bypasses the fancy angle handling to just make it angular velocity
            angularPower = wheel;
        } else {
            overPower = 0.0;
            //This fancily handles the angle to make it curve the bot, not just change angular velocity.
            angularPower = (Math.abs(throttle) * wheel * kTurnSensitivity) - mQuickStopAccumulator;
            	// (throttle * wheel * 1) - that
            if (mQuickStopAccumulator > 1) {
                mQuickStopAccumulator -= 1;
            } else if (mQuickStopAccumulator < -1) {
                mQuickStopAccumulator += 1;
            } else {
                mQuickStopAccumulator = 0.0;
            }
        }
        
        // Final important step if there is no quickTurn
        double rightPwm = throttle - angularPower;
        double leftPwm = throttle + angularPower;
        
        //Normalizes output values. 
        //If quick turn is on, decrease the power of the other side as well.
        if (leftPwm > 1.0) {
            rightPwm -= overPower * (leftPwm - 1.0);
            leftPwm = 1.0;
        } else if (rightPwm > 1.0) {
            leftPwm -= overPower * (rightPwm - 1.0);
            rightPwm = 1.0;
        } else if (leftPwm < -1.0) {
            rightPwm += overPower * (-1.0 - leftPwm);
            leftPwm = -1.0;
        } else if (rightPwm < -1.0) {
            leftPwm += overPower * (-1.0 - rightPwm);
            rightPwm = -1.0;
        }
        
        mSignal.rightMotor = rightPwm;
        mSignal.leftMotor = leftPwm;
        return mSignal;
    }
    
    /**
     * @return
     */
    public TankDriveSignal getSignal() {
    	return mSignal;
    }
}
