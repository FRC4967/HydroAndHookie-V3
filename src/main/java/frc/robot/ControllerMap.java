package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ControllerMap 
{

    public Joystick leftJoystick;
    public Joystick rightJoystick;

    private Joystick manipulatorJoystick;

    // PRIMARY DRIVER CONTROLS

    private boolean intakeRunIn;
    private boolean intakeRunOut;
    private boolean climberReelIn;
    private boolean climberReelOut;

    // primary actual drive controls

    private double getRightSpeed;
    private double getLeftSpeed;

    // MANIPULATOR CONTROLS

    private boolean indexForward;
    private boolean indexBackwards;
    private boolean constantShooting;
    private boolean startAndStopShooting;
    private boolean engageClimberFailSafe;
    private boolean raiseClimberHook;
    private boolean climberHookDown;

    public ControllerMap()
    {
        leftJoystick  = new Joystick(0);
        rightJoystick = new Joystick(1);

        manipulatorJoystick = new Joystick(2);

        intakeRunIn = rightJoystick.getRawButton(1);
        intakeRunOut = leftJoystick.getRawButton(1);
        climberReelIn = rightJoystick.getRawButton(9);
        climberReelOut = rightJoystick.getRawButton(6);

        getRightSpeed = rightJoystick.getRawAxis(1);
        getLeftSpeed = leftJoystick.getRawAxis(1);

        indexForward = manipulatorJoystick.getRawButton(5);
        indexBackwards = manipulatorJoystick.getRawButton(3);
        constantShooting = manipulatorJoystick.getRawButton(12);
        startAndStopShooting = manipulatorJoystick.getRawButtonPressed(1);
        engageClimberFailSafe = manipulatorJoystick.getRawButton(11);
        raiseClimberHook = manipulatorJoystick.getRawButton(7);
        climberHookDown = manipulatorJoystick.getRawButton(9);
    }

    // periodic

    public void periodic()
    {
        intakeRunIn = rightJoystick.getRawButton(1);
        intakeRunOut = leftJoystick.getRawButton(1);
        climberReelIn = rightJoystick.getRawButton(9);
        climberReelOut = rightJoystick.getRawButton(6);

        getRightSpeed = rightJoystick.getRawAxis(1);
        getLeftSpeed = leftJoystick.getRawAxis(1);

        indexForward = manipulatorJoystick.getRawButton(5);
        indexBackwards = manipulatorJoystick.getRawButton(3);
        constantShooting = manipulatorJoystick.getRawButton(12);
        startAndStopShooting = manipulatorJoystick.getRawButtonPressed(1);
        engageClimberFailSafe = manipulatorJoystick.getRawButton(11);
        raiseClimberHook = manipulatorJoystick.getRawButton(7);
        climberHookDown = manipulatorJoystick.getRawButton(9);
        // System.out.println(intakeRunIn);
    }

    // FUNCTIONS TO RETREIVE BUTTONS

    // PRIMARY DRIVER

    public boolean intakeIn()
    {
        System.out.println(intakeRunIn);
        return intakeRunIn;
    }

    public boolean intakeOut()
    {
        return intakeRunOut;
    }

    public boolean reelInClimber()
    {
        return climberReelIn;
    }

    public boolean reelOutClimber()
    {
        return climberReelOut;
    }

    // actual drive controls

    public double getRightDriveSpeed()
    {
        return getRightSpeed;
    }

    public double getLeftDriveSpeed()
    {
        return getLeftSpeed;
    }

    // MANIPULATOR DRIVER

    public boolean indexForward()
    {
        return indexForward;
    }

    public boolean indexBackward()
    {
        return indexBackwards;
    }

    public boolean constantShooting()
    {
        return constantShooting;
    }

    public boolean startAndStopShooting()
    {
        return startAndStopShooting;
    }

    public boolean engageClimberFailSafe()
    {
        return engageClimberFailSafe;
    }

    public boolean fishingPoleUp()
    {
        return raiseClimberHook;
    }

    public boolean fishingPoleDown()
    {
        return climberHookDown;
    }

}
