package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.ctre.phoenix.motorcontrol.ControlMode;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Climber 
{
    
    private static CANSparkMax fishingPole;
    private static VictorSPX climberS;
    private static VictorSPX climberF;

    private static boolean climberButton;

    private static Climber climberInstance;

    public Climber()
    {
        fishingPole = new CANSparkMax(18, MotorType.kBrushed);
        climberS = new VictorSPX(RobotMap.CLIMBER_S_ID);
        climberF = new VictorSPX(RobotMap.CLIMBER_F_ID);

        climberButton = false;
    }

    public static Climber getInstance()
    {
        if (climberInstance == null)
        {
            climberInstance = new Climber();
        }
        return climberInstance;
    }

    public static void extendFishPole()
    {
        fishingPole.set(-0.7);
    }

    public static void retractFishPole()
    {
        fishingPole.set(0.7);
    }

    public static void stopFishPole()
    {
        fishingPole.set(0);
    }

    public static void climberButtonEngaged()
    {
        climberButton = true;
    }

    public static void climberButtonDisengaged()
    {
        climberButton = false;
    }

    public static void climberElevate()
    {
        climberF.set(ControlMode.PercentOutput, -1);
        climberS.set(ControlMode.PercentOutput, 1);    
    }

    public static void climberDescend()
    {
        climberF.set(ControlMode.PercentOutput, 1);
        climberS.set(ControlMode.PercentOutput, -1);
    }

    public static void climberStop()
    {
        climberF.set(ControlMode.PercentOutput, 0);
        climberS.set(ControlMode.PercentOutput, 0);
    }

}
