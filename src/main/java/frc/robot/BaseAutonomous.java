package frc.robot;

import frc.robot.TimerForAutonomous;

public abstract class BaseAutonomous 
{

    private TimerForAutonomous timer;
    private boolean isRunning;


    public BaseAutonomous()
    {
        timer = TimerForAutonomous.getInstance();
        isRunning = false;
    }

    public abstract void periodic();

    public void start()
    {
        timer.setStep(0);
        isRunning = true;
    }

    public void stop()
    {
        isRunning = false;
    }
    
    public boolean isRunning()
    {
        return isRunning;
    }

    public boolean hasCompleted()
    {
        return !isRunning;
    }

    public int getStep()
    {
        return timer.getStep();
    }

    public void setStep(int desiredStep)
    {
        timer.setStep(desiredStep);
    }

    public void advanceStep()
    {
        timer.stopTimerAndIncreaseStep();
    }

    public void setTimerAndAdvanceStep(long milliseconds)
    {
        timer.setTimer(milliseconds);
    }
}
