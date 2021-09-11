package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoStayStill extends BaseAutonomous
{

    private static ThreeBallAuto instance;
    
    public AutoStayStill()
    {
        super();
    }

    public static ThreeBallAuto getInstance()
    {
        if (instance == null)
        {
            instance = new ThreeBallAuto();
        }
        return instance;
    }
    public void start()
    {
        super.start();
    }
    public void stop()
    {
        super.stop();
    }


    @Override
    public void periodic() 
    {
        if(isRunning())
        {
            SmartDashboard.putNumber("Auton Current Step", getStep());
            switch(getStep())
            {
                case 0:
                    advanceStep();
                    break;
                case 1:
                    stop();
                    break;
            }
        }
        
    }
}