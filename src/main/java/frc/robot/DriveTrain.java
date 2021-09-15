package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

public class DriveTrain 
{
    private static CANSparkMax leftMotor;
    private static CANSparkMax leftFollower;
    private static CANSparkMax rightMotor;
    private static CANSparkMax rightFollower;

    private static CANEncoder rightMotorEncoder;
    private static CANEncoder leftMotorEncoder;

    private static CANPIDController rightDrivePID;
    private static CANPIDController leftDrivePID;

    private static DriveTrain driveTrainInstance;

    private static final double P = 0.001; // CALIBRATE
    private static final double I = 0; // CALIBRATE
    private static final double D = 10; // CALIBRATE
    private static final double I_ZONE = 15; // CALIBRATE



    private DriveTrain()
    {
        leftMotor = new CANSparkMax(RobotMap.LEFT_MOTOR_ID, MotorType.kBrushless);
        leftFollower = new CANSparkMax(RobotMap.LEFT_FOLLOWER_ID, MotorType.kBrushless);
        rightMotor = new CANSparkMax(RobotMap.RIGHT_MOTOR_ID, MotorType.kBrushless);
        rightFollower = new CANSparkMax(RobotMap.RIGHT_FOLLOWER_ID, MotorType.kBrushless);

        leftFollower.follow(leftMotor);
        rightFollower.follow(rightMotor);

        leftMotor.restoreFactoryDefaults();
        rightMotor.restoreFactoryDefaults();

        leftMotor.setOpenLoopRampRate(0.1);
        rightMotor.setOpenLoopRampRate(0.1);

        leftMotor.setSmartCurrentLimit(40);
        rightMotor.setSmartCurrentLimit(40);

        leftMotor.setIdleMode(IdleMode.kBrake);
        rightMotor.setIdleMode(IdleMode.kBrake);

        leftMotorEncoder = leftMotor.getEncoder();
        rightMotorEncoder = rightMotor.getEncoder();

        leftDrivePID = leftMotor.getPIDController();
        rightDrivePID = rightMotor.getPIDController();

        leftDrivePID.setP(P);
        leftDrivePID.setI(I);
        leftDrivePID.setD(D);
        leftDrivePID.setIZone(I_ZONE);
        leftDrivePID.setFF(0);

        rightDrivePID.setP(P);
        rightDrivePID.setI(I);
        rightDrivePID.setD(D);
        rightDrivePID.setIZone(I_ZONE);
        rightDrivePID.setFF(0);
    }

    public static DriveTrain getInstance()
    {
        if (driveTrainInstance == null)
        {
            driveTrainInstance = new DriveTrain();
        }
        return driveTrainInstance;
    }

    public static void teleopDrive()
    {
        leftMotor.setIdleMode(IdleMode.kBrake);
        rightMotor.setIdleMode(IdleMode.kBrake);
        rightFollower.follow(rightMotor);
        leftFollower.follow(leftMotor);
        leftMotor.setInverted(true);
    }

    public static void turn(double degrees)
    {
        resetDriveEncoders();
        double arcLength = RobotMap.ROBORADIUS * (Math.toRadians(degrees));
        drive(arcLength, -arcLength);
    }

    public static void drive(double leftPos, double rightPos)
    {
        leftPos = inchesToTicks(leftPos);
        rightPos = inchesToTicks(rightPos);
        
        leftDrivePID.setReference(leftPos, ControlType.kPosition);
        rightDrivePID.setReference(rightPos, ControlType.kPosition);
    }

    public static double inchesToTicks(double inches)
    {
        double inchesToTicks = inches * ((RobotMap.WHEEL_CIRC / (RobotMap.TICKS_PER_REVOLUTION * RobotMap.GEARING_RATIO)));
        inchesToTicks = Math.round(inchesToTicks);

        return inchesToTicks;
    }

    public static void driveTeleop(double leftPercentage, double rightPercentage)
    {
        System.out.println(leftPercentage);
        System.out.println(rightPercentage);
        leftMotor.set(leftPercentage);
        rightMotor.set(rightPercentage);
    }

    public static double getLeftDriveEncoder()
    {
        return leftMotorEncoder.getPosition();
    }

    public static double getRightDriveEncoder()
    {
        return rightMotorEncoder.getPosition();
    }

    public static void resetDriveEncoders()
    {
        leftMotorEncoder.setPosition(0);
        rightMotorEncoder.setPosition(0);
    }


}
