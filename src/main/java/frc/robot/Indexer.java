package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Indexer 
{
    private static Indexer indexerInstance;

    private static VictorSPX indexer;
    private static VictorSPX belt;

    private static boolean indexerForward;
    private static boolean indexerBackward;
    private static boolean indexerStop;

    private static boolean beltForward;
    private static boolean beltBackward;
    private static boolean beltStop;

    public Indexer()
    {
        indexer = new VictorSPX(RobotMap.INDEX_MOTOR_ID);
        belt = new VictorSPX(RobotMap.BELT_MOTOR_ID);

        indexerForward = false;
        indexerBackward = false;
        indexerStop = true;

        beltForward = false;
        beltBackward = false;
        beltStop = true;
    }

    public static Indexer getInstance()
    {
        if (indexerInstance == null)
        {
            indexerInstance = new Indexer();
        }
        return indexerInstance;
    }

    public static void periodic()
    {
        if (indexerForward)
        {
            indexer.set(ControlMode.PercentOutput, 0.3);
        }

        if (indexerBackward)
        {
            indexer.set(ControlMode.PercentOutput, -0.3);
        }
        
        if (indexerStop)
        {
            indexer.set(ControlMode.PercentOutput, 0);
        }

        if (beltForward)
        {
            belt.set(ControlMode.PercentOutput, 0.5);
        }

        if (beltBackward)
        {
            belt.set(ControlMode.PercentOutput, -0.5);
        }
        
        if (beltStop)
        {
            belt.set(ControlMode.PercentOutput, 0);
        } 
        
    }

    public static void indexerRunForwards()
    {
        indexerStop = false;
        indexerBackward = false;
        indexerForward = true;
    }

    public static void indexerRunBackwards()
    {
        indexerForward = false;
        indexerStop = false;
        indexerBackward = true;
    }

    public static void indexerStop()
    {
        indexerForward = false;
        indexerBackward = false;
        indexerStop = true;
    }

    public static void beltRunForwards()
    {
        beltStop = false;
        beltBackward = false;
        beltForward = true;
    }

    public static void beltRunBackwards()
    {
        beltForward = false;
        beltStop = false;
        beltBackward = true;
    }

    public static void beltStop()
    {
        beltForward = false;
        beltBackward = false;
        beltStop = true;
    }
}
