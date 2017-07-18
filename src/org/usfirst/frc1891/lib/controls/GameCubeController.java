package org.usfirst.frc1891.lib.controls;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GamepadBase;
import edu.wpi.first.wpilibj.hal.HAL;

/**
 * @author Edgar Schafer
 *
 */
public class GameCubeController extends GamepadBase {
	//TODO button specific methods, trigger methods, tune analog signals to be 1 at max and 0 at min,
	
	/*
	 * Sneaky private gamecube controller data
	 * 
	 * Main stick axis
	 * x-0
	 * y-1
	 * 
	 * C stick axis
	 * x-2
	 * y-
	 * 
	 * Trigger Analog axis
	 * Left-
	 * Right-
	 * 
	 * POV (D-Pad, also shows up as buttons)
	 * 
	 * Button Numbers
	 * A-2
	 * B-3
	 * X-1
	 * Y-4
	 * Z-8
	 * L (Hard Trigger)-5
	 * R (Hard Trigger)-6
	 * Start-10
	 * D-pad Up-13
	 * D-pad Down-
	 * D-pad Left-
	 * D-pad Right-
	 * 
	 */
	
	private DriverStation m_ds = DriverStation.getInstance();
	private int m_outputs;
	private short m_leftRumble;
	private short m_rightRumble;
	
	/**
	 * Construct an instance of a GameCube controller.
	 * 
	 * @param port Driver Stations port for controller
	 */
	public GameCubeController(int port) {
		super(port);
	}

	
	@SuppressWarnings("javadoc")
	@Override
	public double getRawAxis(int axis) {
		return m_ds.getStickAxis(getPort(), axis);
	}

	@SuppressWarnings("javadoc")
	@Override
	public boolean getBumper(Hand hand) {
		if (hand.equals(Hand.kLeft)) {
			return getRawButton(5);
		}
		else {
			return getRawButton(6);
		}
	}
	

	/** 
	 * Not applicable for gamecube controller. Only here to satisfy GamePadBase interface.
	 * 
	 * @param hand 
	 * @return always false
	 */
	@Override
	public boolean getStickButton(Hand hand) {
		return false;
	}

	@SuppressWarnings("javadoc")
	@Override
	public boolean getRawButton(int button) {
		return m_ds.getStickButton(getPort(), (byte) button);
	}

	@SuppressWarnings("javadoc")
	@Override
	public int getPOV(int pov) {
		return m_ds.getStickPOV(getPort(), pov);
	}

	@SuppressWarnings("javadoc")
	@Override
	public int getPOVCount() {
		return m_ds.getStickPOVCount(getPort());
	}

	@SuppressWarnings("javadoc")
	@Override
	public HIDType getType() {
		return HIDType.values()[m_ds.getJoystickType(getPort())];
	}

	@SuppressWarnings("javadoc")
	@Override
	public String getName() {
		return m_ds.getJoystickName(getPort());
	}

	@SuppressWarnings("javadoc")
	@Override
	public void setOutput(int outputNumber, boolean value) {
		m_outputs = (m_outputs & ~(1 << (outputNumber - 1))) | ((value ? 1 : 0) << (outputNumber - 1));
	    HAL.setJoystickOutputs((byte) getPort(), m_outputs, m_leftRumble, m_rightRumble);

	}

	@SuppressWarnings("javadoc")
	@Override
	public void setOutputs(int value) {
		m_outputs = value;
	    HAL.setJoystickOutputs((byte) getPort(), m_outputs, m_leftRumble, m_rightRumble);
	}

	@SuppressWarnings("javadoc")
	@Override
	public void setRumble(RumbleType type, double value) {
		//TODO figure out if this works, and write javadoc on how it works
		if (value < 0) {
			value = 0;
		} else if (value > 1) {
			value = 1;
		}
		if (type == RumbleType.kLeftRumble) {
			m_leftRumble = (short) (value * 65535);
		} else {
			m_rightRumble = (short) (value * 65535);
		}
		HAL.setJoystickOutputs((byte) getPort(), m_outputs, m_leftRumble, m_rightRumble);
	}

	/** 
	 * 
	 * @param hand 
	 */
	@Override
	public double getX(Hand hand) {
		if (hand.equals(Hand.kLeft))
		{
			return getRawAxis(0);
		}
		else {
			return getRawAxis(2);
		}
	}

	
	/**
	 * 
	 * @param hand
	 */
	@Override
	public double getY(Hand hand) {
		if (hand.equals(Hand.kLeft))
		{
			return getRawAxis(1);
		}
		else {
			return getRawAxis(3); //TODO test if this is the right axis
		}
	}
	
	/* Gamecube specific methods */
	
	/**
	 * @return
	 */
	public boolean getAButton() {
		return getRawButton(2);
	}
	
	/**
	 * @return
	 */
	public boolean getBButton() {
		return getRawButton(3);
	}
	
	/**
	 * @return
	 */
	public boolean getXButton() {
		return getRawButton(1);
	}
	
	/**
	 * @return
	 */
	public boolean getYButton() {
		return getRawButton(4);
	}
	
	/**
	 * @return
	 */
	public boolean getStartButton() {
		return getRawButton(10);
	}
	
	/**
	 * @return
	 */
	public boolean getZButton() {
		return getRawButton(8);
	}
	
	//TODO bumper hard vs soft triggers?
}
