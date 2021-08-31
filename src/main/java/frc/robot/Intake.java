package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Intake 
{

    private static Intake intakeInstance;

    private static VictorSPX intakeLow;
    private static VictorSPX intakeHigh;

    private static boolean intakeForward;
    private static boolean intakeBackward;
    private static boolean intakeStop;


    public Intake()
    {
        intakeForward = false;
        intakeBackward = false;
        intakeStop = true;
        intakeLow = new VictorSPX(RobotMap.INTAKE_MOTOR_LOW);
        intakeHigh = new VictorSPX(RobotMap.INTAKE_MOTOR_HIGH);
    }

    public static Intake getInstance()
    {
        if (intakeInstance == null)
        {
            intakeInstance = new Intake();
        }
        return intakeInstance;
    }

    public static void periodic()
    {
        if (intakeForward)
        {
            intakeLow.set(ControlMode.PercentOutput, -.75);
            intakeHigh.set(ControlMode.PercentOutput, .6);
        }
        if (intakeBackward)
        {
            intakeLow.set(ControlMode.PercentOutput, .75);
            intakeHigh.set(ControlMode.PercentOutput, -.6);
        }
        if (intakeStop)
        {
            intakeLow.set(ControlMode.PercentOutput, 0);
            intakeHigh.set(ControlMode.PercentOutput, 0);
        }
    }
    

    public static void intakeRunForwards()
    {
        intakeStop = false;
        intakeBackward = false;
        intakeForward = true;
    }

    public static void intakeRunBackwards()
    {
        intakeForward = false;
        intakeStop = false;
        intakeBackward = true;
    }

    public static void stopIntake()
    {
        intakeBackward = false;
        intakeForward = false;
        intakeStop = true;
    }
}

