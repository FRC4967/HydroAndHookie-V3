package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Indexer 
{
    private static Indexer indexerInstance;

    private static VictorSPX indexer;
    private static VictorSPX belt;

    public Indexer()
    {
        indexer = new VictorSPX(RobotMap.INDEX_MOTOR_ID);
        belt = new VictorSPX(RobotMap.BELT_MOTOR_ID);
    }

    public static Indexer getInstance()
    {
        if (indexerInstance == null)
        {
            indexerInstance = new Indexer();
        }
        return indexerInstance;
    }

    public static void indexerForward()
    {
        indexer.set(ControlMode.PercentOutput, 0.3);
    }

    public static void indexerBackward()
    {
        indexer.set(ControlMode.PercentOutput, -0.3);
    }

    public static void indexerStop()
    {
        indexer.set(ControlMode.PercentOutput, 0);
    }

    public static void beltForward()
    {
        belt.set(ControlMode.PercentOutput, 0.5);
    }

    public static void beltBackward()
    {
        belt.set(ControlMode.PercentOutput, -0.5);
    }

    public static void beltStop()
    {
        belt.set(ControlMode.PercentOutput, 0);
    }
}
