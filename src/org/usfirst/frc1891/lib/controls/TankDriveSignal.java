package org.usfirst.frc1891.lib.controls;

/**
 * Defines an object for sending drive train instructions from user input.
 * Tank Drive simply requires a left side output and a right side output.
 * 
 * @author Edgar Schafer
 *
 */
public class TankDriveSignal {
	public double leftMotor;
	public double rightMotor;
	
	/**
	 * @param leftSidePower Power to be applied to right drive motors, 
	 * @param rightSidePower
	 */
	public TankDriveSignal(double leftSidePower, double rightMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}
}
