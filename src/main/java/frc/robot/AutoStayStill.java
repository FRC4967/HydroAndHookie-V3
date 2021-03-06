package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class AutoStayStill extends BaseAutonomous
{
    public AutoStayStill()
    {
        super();
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
                    DriveTrain.drive(20, 20);
                    // advanceStep();
                    break;
                case 1:
                    setStep(0);
                    break;
            }
        }
        
    }
}
