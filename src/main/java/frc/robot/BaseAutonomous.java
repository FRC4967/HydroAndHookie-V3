package frc.robot;

public abstract class BaseAutonomous 
{

    private static TimerForAutonomous timer;
    private static boolean isRunning;


    private BaseAutonomous()
    {
        timer = TimerForAutonomous.getInstance();
        isRunning = false;
    }

    public abstract void periodic();

    public static void start()
    {
        timer.setStep(0);
        isRunning = true;
    }

    public static void stop()
    {
        isRunning = false;
    }
    
    public static boolean isRunning()
    {
        return isRunning;
    }

    public static boolean hasCompleted()
    {
        return !isRunning;
    }

    public static int getStep()
    {
        return timer.getStep();
    }

    public static void setStep(int desiredStep)
    {
        timer.setStep(desiredStep);
    }

    public static void advanceStep()
    {
        timer.stopTimerAndIncreaseStep();
    }

    public static void setTimerAndAdvanceStep(long milliseconds)
    {
        timer.setTimer(milliseconds);
    }
}
