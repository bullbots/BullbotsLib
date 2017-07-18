/**
 * 
 */
package org.usfirst.frc1891.lib;

/**
 * A template for breaking robots into subsystems.
 * Each subsystem is in charge of a set of related components on the robot.
 * Subsystems can be nested (i.e. A shooter system has an intake subsystem and a flywheel.
 * When used as such, the containing subsystem is considered a supersystem.
 * <p>
 * Subsystems usually define a loopable object which will be run in a seperate thread to run the
 * subsystems idle or active tasks. If the subsystem is simple enough that a constant loop 
 * is unnecessary, then subsystem work can be done in the thread actions are called in. However, 
 * if this is the case, then the time it takes to do the work should be very short (i.e. Don't
 * put code that waits for 10 seconds in a method like this, or even one that waits for 0.1 seconds!).
 * <p>
 * A subsystem may be controlled a number of ways. One is having client classes set a public
 * "wanted state" enum. The subsystem would then take necessary action to change to the new state.
 * A subsystem might also have methods to request instantaneous action, such as a shooter fire command.
 * Also, a system may have a set state, but require addition information passed to it periodically, such as drive system.
 * For this, setting the state and calling an update data method would be appropriate.
 * <p>
 * Top level subsystems should be created as singletons to ensure multiple drive systems 
 * are not created, or other such redundancy.
 * 
 * @author Edgar Schafer
 * 
 * 
 */
public abstract class Subsystem {
	
	/**
	 * Stop all system output for safety or just general stopping
	 */
	public abstract void stop();
	/**
	 * Zero all sensors for initial calibration or recalibration
	 */
	public abstract void zeroSensors();
	public abstract void outputToSmartDashboard(); // TODO Do I put this?
	
}
