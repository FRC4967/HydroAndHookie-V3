package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ControllerMap 
{

    private static Joystick leftJoystick  = new Joystick(0);
    private static Joystick rightJoystick = new Joystick(1);

    private static Joystick manipulatorJoystick = new Joystick(2);

    // PRIMARY DRIVER CONTROLS

    private static boolean intakeRunIn = rightJoystick.getRawButton(1);
    private static boolean intakeRunOut = leftJoystick.getRawButton(1);
    private static boolean climberReelIn = rightJoystick.getRawButton(9);
    private static boolean climberReelOut = rightJoystick.getRawButton(6);

    // primary actual drive controls

    private static double getRightSpeed = rightJoystick.getRawAxis(1);
    private static double getLeftSpeed = leftJoystick.getRawAxis(1);

    // MANIPULATOR CONTROLS

    private static boolean indexForward = manipulatorJoystick.getRawButton(5);
    private static boolean indexBackwards = manipulatorJoystick.getRawButton(3);
    private static boolean constantShooting = manipulatorJoystick.getRawButton(12);
    private static boolean startAndStopShooting = manipulatorJoystick.getRawButton(1);
    private static boolean engageClimberFailSafe = manipulatorJoystick.getRawButton(11);
    private static boolean raiseClimberHook = manipulatorJoystick.getRawButton(7);
    private static boolean climberHookDown = manipulatorJoystick.getRawButton(9);

    // FUNCTIONS TO RETREIVE BUTTONS

    // PRIMARY DRIVER

    public static boolean intakeIn()
    {
        return intakeRunIn;
    }

    public static boolean intakeOut()
    {
        return intakeRunOut;
    }

    public static boolean reelInClimber()
    {
        return climberReelIn;
    }

    public static boolean reelOutClimber()
    {
        return climberReelOut;
    }

    // actual drive controls

    public static double getRightDriveSpeed()
    {
        return getRightSpeed;
    }

    public static double getLeftDriveSpeed()
    {
        return getLeftSpeed;
    }

    // MANIPULATOR DRIVER

    public static boolean indexForward()
    {
        return indexForward;
    }

    public static boolean indexBackward()
    {
        return indexBackwards;
    }

    public static boolean constantShooting()
    {
        return constantShooting;
    }

    public static boolean startAndStopShooting()
    {
        return startAndStopShooting;
    }

    public static boolean engageClimberFailSafe()
    {
        return engageClimberFailSafe;
    }

    public static boolean fishingPoleUp()
    {
        return raiseClimberHook;
    }

    public static boolean fishingPoleDown()
    {
        return climberHookDown;
    }

}
