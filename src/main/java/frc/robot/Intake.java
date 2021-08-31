package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Intake 
{

    private static Intake intakeInstance;


    public Intake()
    {

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

    }

    public static void intakeRunForwards()
    {

    }

    public static void intakeRunBackwards()
    {

    }

    public static void stopIntake()
    {

    }
}

