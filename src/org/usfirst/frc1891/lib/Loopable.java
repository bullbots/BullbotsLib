package org.usfirst.frc1891.lib;

/**
 * A loopable is where the majority of robot processing happens. A loopable may be associated
 * with a subsystem, or may run on its own to manage something like vision processing
 * 
 * @author Edgar Schafer
 *
 */
public abstract class Loopable {
	
	public abstract void onStart();
	
	public abstract void loop();
	
	public abstract void onStop();
}
