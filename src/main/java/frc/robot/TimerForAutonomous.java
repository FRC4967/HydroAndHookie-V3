package frc.robot;

public class TimerForAutonomous 
{
    
    private static int step;
    private static long endTime;
    private static boolean timerRunning;

    private static TimerForAutonomous timerForAutonomousInstance;

    private TimerForAutonomous()
    {
        timerRunning = false;
    }

    public static TimerForAutonomous getInstance()
    {
        if (timerForAutonomousInstance == null)
        {
            timerForAutonomousInstance = new TimerForAutonomous();
        }
        return timerForAutonomousInstance;
    }

    public static void setTimer(long time)
    {
        endTime = System.currentTimeMillis() + time;
        timerRunning = true;
    }

    public static void setTimerAndIncreaseStep(long time)
    {
        setTimer(time);
        nextStep();
    }

    public static void stopTimer()
    {
        setTimer(1000000); 
    }

    public static void stopTimerAndIncreaseStep()
    {
        stopTimer();
        nextStep();
    }

    public static void increaseStepWhenTimerExpired()
    {
        if (timeExpired() && timerRunning)
        {
            step++;
            timerRunning = false;
        }
    }

    public static boolean timeExpired()
    {
        return endTime < System.currentTimeMillis();
    }

    public static int getStep()
    {
        return step;
    }

    public static void setStep(int theStep)
    {
        step = theStep;
    }

    public static void nextStep()
    {
        step++;
    }

    public static double getMillisecondsLeft()
    {
        return endTime - System.currentTimeMillis();
    }

    public static double getSecondsLeft()
    {
        return (endTime - System.currentTimeMillis()) / 1000;
    }
}
