package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Intake 
{

    private static Intake intakeInstance;

    private static VictorSPX intakeLow;
    private static VictorSPX intakeHigh;


    private Intake()
    {
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

    public static void start()
    {
        intakeLow.set(ControlMode.PercentOutput, -.75);
        intakeHigh.set(ControlMode.PercentOutput, .6);
    }

    public static void reverse()
    {
        intakeLow.set(ControlMode.PercentOutput, .75);
        intakeHigh.set(ControlMode.PercentOutput, -.6);
    }

    public static void stop()
    {
        intakeLow.set(ControlMode.PercentOutput, 0);
        intakeHigh.set(ControlMode.PercentOutput, 0);
    }
}

