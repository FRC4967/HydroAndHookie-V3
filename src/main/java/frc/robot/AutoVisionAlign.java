package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

public class AutoVisionAlign extends BaseAutonomous
{

    private static double distanceFromTarget;
    private static double angleOffTarget;

    private static Timer timer;

    public AutoVisionAlign()
    {
        super();

        distanceFromTarget = 0;
        angleOffTarget = 0;

        timer = new Timer();
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
                    Limelight.turnLedsOn();
                    distanceFromTarget = Limelight.getDistanceFromTarget();
                    angleOffTarget = Limelight.getAngleOffTarget();
                    distanceFromTarget = Limelight.getDistanceAdjustedByAngle(distanceFromTarget, angleOffTarget);
                    advanceStep();
                    break;
                case 1:
                    Shooter.adjustBottomVelocity(Limelight.getBottomShooterVelocity(distanceFromTarget));
                    Shooter.startShooting();
                    advanceStep();
                    break;
                case 2:
                    DriveTrain.turn(angleOffTarget);
                    if (Limelight.isRobotLinedUp())
                    {
                        Limelight.flashLeds();
                        timer.start();
                        advanceStep();
                    }
                    break;
                case 3:
                    if (timer.get() > 0.25)
                    {
                        Limelight.turnLedsOff();
                        stop();
                    }
                    break;
            }
        }
        
    }
}
