package org.usfirst.frc1891.lib.controls;

public class GTADrive {
	private double kDeadband = 0.05;
    private TankDriveSignal mSignal = new TankDriveSignal(0,0);
        
    /**
     * @param leftJoystick Left stick input ranging from -1 to 1
     * @param rightJoystick Right stick input ranging from -1 to 1
     * @return Calculate drive signal
     */
    public TankDriveSignal calculateSignal(double throttleJoystick, double forwardTrigger, double backwardTrigger) {
    	
    	
    	
   
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
