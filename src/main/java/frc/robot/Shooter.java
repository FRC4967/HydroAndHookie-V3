package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter 
{

    private static CANSparkMax topShooter;
    private static CANSparkMax bottomShooter;

    private static CANEncoder topEncoder;
    private static CANEncoder bottomEncoder;

    private static final double P = 0.0001;
    private static final double I = 0;
    private static final double D = 0;
    private static final double F = 0.000173;

    private static int topVelocity;
    private static int bottomVelocity;

    private static Shooter shooterInstance;

    private static boolean isShooting;
    
    private Shooter()
    {
        topShooter = new CANSparkMax(RobotMap.SHOOTER_MOTOR_TOP_ID, MotorType.kBrushless);
        bottomShooter = new CANSparkMax(RobotMap.SHOOTER_MOTOR_BOTTOM_ID, MotorType.kBrushless);

        topEncoder = new CANEncoder(topShooter);
        bottomEncoder = new CANEncoder(bottomShooter);

        topShooter.getPIDController().setP(P);
        topShooter.getPIDController().setI(I);
        topShooter.getPIDController().setD(D);
        topShooter.getPIDController().setFF(F);

        bottomShooter.getPIDController().setP(P);
        bottomShooter.getPIDController().setI(I);
        bottomShooter.getPIDController().setD(D);
        bottomShooter.getPIDController().setFF(F);

        topVelocity = 3500;
        bottomVelocity = 5500;

        isShooting = false;
    }


    public static Shooter getInstance()
    {
        if (shooterInstance == null)
        {
            shooterInstance = new Shooter();
        }
        return shooterInstance;
    }

    public static void constantShooting()
    {
        topVelocity = 3500;
        bottomVelocity = 5500;
    }

    public static void startShooting()
    {
        isShooting = true;
        topShooter.getPIDController().setReference(topVelocity, ControlType.kVelocity);
        bottomShooter.getPIDController().setReference(bottomVelocity, ControlType.kVelocity);
    }

    public static void stopShooting()
    {
        isShooting = false;
        topShooter.getPIDController().setReference(0, ControlType.kVelocity);
        bottomShooter.getPIDController().setReference(0, ControlType.kVelocity);
    }

    public static void startAndStopShooting()
    {
        if (!isShooting)
        {
            startShooting();
        }
        else if (isShooting)
        {
            stopShooting();
        }

    }

    public static void adjustBottomVelocity(int bottom)
    {
        bottomVelocity = bottom;
    }

    public static void adjustTopVelocity(int top)
    {
        topVelocity = top;
    }

}
