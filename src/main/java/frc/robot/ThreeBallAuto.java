package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Timer;

public class ThreeBallAuto extends BaseAutonomous
{
   
    private static ThreeBallAuto instance;

    private static Timer timer;

    private static double position;

    private static AutoVisionAlign autoVisionAlign;
    
    public ThreeBallAuto()
    {
        super();
        timer = new Timer();
        position = 0;

        autoVisionAlign = new AutoVisionAlign();
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
                    // stop and reset timer
                    timer.stop();
                    timer.reset();
                    // Vision line up and starting the shooter
                    autoVisionAlign.start();
                    if (Limelight.isRobotLinedUp())
                    {
                        advanceStep();
                    }
                    break;
                case 1:
                    // indexer start
                    Indexer.beltForward();
                    Indexer.indexerForward();
                    // timer start
                    timer.start();
                    advanceStep();
                    break;
                case 2:
                    // if the timer is greater than 3 seconds, we are assumming all balls are out
                    // we then proceed to stop everything
                    if (timer.get() > 3)
                    {
                        timer.stop();
                        Indexer.beltStop();
                        Indexer.indexerStop();
                        Shooter.stopShooting();
                        advanceStep();
                    }
                    break;
                case 3:
                    // set up trap move
                    TrapezoidalMove.SetAll(15, 15, 30, 40);
                    DriveTrain.resetDriveEncoders();
                    // Stop and Reset Timer
                    timer.stop();
                    timer.reset();
                    timer.start();
                    advanceStep();
                    break;
                case 4:
                    // get position along the trap movement
                    // using negative sign in front of trap to make sure we drive backwards
                    position = - TrapezoidalMove.Position((float) timer.get());
                    // command drive train to drive to the position
                    DriveTrain.drive(position, position);
                    // once the timer has exceeded the trap movements total time then we move on
                    // hence drive is completed
                    if (timer.get() > TrapezoidalMove.GetTotalTime())
                    {
                        timer.stop();
                        advanceStep();
                    }
                    break;
                case 5:
                    stop();
                    break;
            }
        }
    }
}
