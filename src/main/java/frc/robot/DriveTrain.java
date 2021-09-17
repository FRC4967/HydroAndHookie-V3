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

    public static CANPIDController rightDrivePID;
    public static CANPIDController leftDrivePID;

    private static DriveTrain driveTrainInstance;

    public static  double P = 0.05; // CALIBRATE
    public static  double I = 0.005; // CALIBRATE
    public static  double D = 0; // CALIBRATE
    public static  double I_ZONE = 1; // CALIBRATE



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

        
        leftMotor.setInverted(true);
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
        System.out.println(arcLength);
        drive(-arcLength, arcLength);
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
        double inchesToTicks = inches * (((RobotMap.TICKS_PER_REVOLUTION * RobotMap.GEARING_RATIO) / RobotMap.WHEEL_CIRC));
        inchesToTicks = Math.round(inchesToTicks);

        return inchesToTicks;
    }

    public static double ticksToInches(double ticks)
    {
        double ticksToInches = ticks / (((RobotMap.TICKS_PER_REVOLUTION * RobotMap.GEARING_RATIO) / RobotMap.WHEEL_CIRC));
        return ticksToInches;
    }

    public static void driveTeleop(double leftPercentage, double rightPercentage)
    {
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
