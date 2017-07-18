package org.usfirst.frc1891.lib.controls;

/**
 * @author Edgar Schafer
 *
 */
public class ControlsUtil {

	
	/**
     * @param val
     * @param deadband
     * @return
     */
    public static double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }
    
    /**
     * Limits the given input to the given magnitude.
     * @param v 
     * @param limit 
     * @return 
     */
    public static double limit(double v, double limit) {
        return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
    }
}
