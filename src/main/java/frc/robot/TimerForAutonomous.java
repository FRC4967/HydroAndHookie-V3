package frc.robot;

public class TimerForAutonomous 
{
    
    private int step;
    private long endTime;
    private boolean timerRunning;

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

    public void setTimer(long time)
    {
        endTime = System.currentTimeMillis() + time;
        timerRunning = true;
    }

    public void setTimerAndIncreaseStep(long time)
    {
        setTimer(time);
        nextStep();
    }

    public void stopTimer()
    {
        setTimer(1000000); 
    }

    public void stopTimerAndIncreaseStep()
    {
        stopTimer();
        nextStep();
    }

    public void increaseStepWhenTimerExpired()
    {
        if (timeExpired() && timerRunning)
        {
            step++;
            timerRunning = false;
        }
    }

    public boolean timeExpired()
    {
        return endTime < System.currentTimeMillis();
    }

    public int getStep()
    {
        return step;
    }

    public void setStep(int theStep)
    {
        step = theStep;
    }

    public void nextStep()
    {
        step++;
    }

    public double getMillisecondsLeft()
    {
        return endTime - System.currentTimeMillis();
    }

    public double getSecondsLeft()
    {
        return (endTime - System.currentTimeMillis()) / 1000;
    }
}
