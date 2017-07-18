package org.usfirst.frc1891.lib;

import java.util.ArrayList;

/**
 * @author Edgar Schafer
 *
 */
public class Looper {
	
	ArrayList<Loopable> loops = new ArrayList<Loopable>();
	Thread thread = new Thread((Runnable) -> {
		for (int index = 0, index < loops.size(), index++) {
			loops.get(index).loop();
		}
		
	});
	
	
	/**
	 * @param loop
	 */
	public void addLoop(Loopable loop) {
		loops.add(loop);
	}
	
	/**
	 * 
	 */
	public void start() {
		
	}
	
	/**
	 * 
	 */
	public void stop() {
		
	}
}
