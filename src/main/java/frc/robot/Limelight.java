package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight 
{

    private static final double CAMERA_HEIGHT = 0; // CALIBRATE
    private static final double CAMERA_ANGLE = 0; // CALIBRATE
    private static final double TARGET_HEIGHT = 89.75;
    private static final double CAMERA_DISTANCE_FROM_CENTER_OF_ROBOT = 0; // CALIBRATE

    private static NetworkTable table;

    private static Limelight limelightInstance;



    private Limelight()
    {
        table = NetworkTableInstance.getDefault().getTable("limelight");

        LookUpTable.getInstance();
    }

    public static Limelight getInstance()
    {
        if (limelightInstance == null)
        {
            limelightInstance = new Limelight();
        }
        return limelightInstance;
    }

    public static double getAngleOffTarget()
    {
        System.out.println(table.getEntry("tx").getDouble(0));
        return table.getEntry("tx").getDouble(0);
    }

    public static double getDistanceFromTarget()
    {
        double ty = 0;
        double degrees = 0;
        double distance = 0;

        ty = table.getEntry("ty").getDouble(0);
        degrees = CAMERA_ANGLE + ty;
        distance = (TARGET_HEIGHT - CAMERA_HEIGHT) / Math.tan(Math.toRadians(degrees));

        return distance;
    }

    public static double getDistanceAdjustedByAngle (double distance, double angleOff)
    {
        double finalDistance = 0;

        if (angleOff > 0)
        {
            finalDistance = (distance - (CAMERA_DISTANCE_FROM_CENTER_OF_ROBOT - ((Math.cos(Math.toRadians(angleOff))) * CAMERA_DISTANCE_FROM_CENTER_OF_ROBOT)));
        }
        else if (angleOff < 0)
        {
            finalDistance = (distance + (CAMERA_DISTANCE_FROM_CENTER_OF_ROBOT - ((Math.cos(Math.toRadians(angleOff))) * CAMERA_DISTANCE_FROM_CENTER_OF_ROBOT)));
        }
        else
        {
            finalDistance = distance;
        }
        return finalDistance;
    }

    public static int getBottomShooterVelocity(double dist)
    {
        double distance = dist;
        double originalDistance = distance;
        double upperVal = 0;
        double desiredVelocityOne = 1;
        double desiredVelocityTwo = 1;
        double averageDeisredVelocityPerInch = 0;
        double finalVelocity = 1;

        distance = Math.floor(distance);
        upperVal = distance + 1;

        if (distance > LookUpTable.getHighestKey())
        {
            distance = LookUpTable.getHighestKey();
        }

        if (upperVal > LookUpTable.getHighestKey())
        {
            upperVal = LookUpTable.getHighestKey();
        }

        desiredVelocityOne = LookUpTable.getKey((int)distance);
        desiredVelocityTwo = LookUpTable.getKey((int)upperVal);

        averageDeisredVelocityPerInch = (desiredVelocityTwo - desiredVelocityOne) / 12;
        
        finalVelocity = (averageDeisredVelocityPerInch * (originalDistance - (distance * 12))) + desiredVelocityOne;

        return (int)finalVelocity;
    }

    public static void turnLedsOn()
    {
        table.getEntry("ledMode").setNumber(3);
    }

    public static void turnLedsOff()
    {
        table.getEntry("ledMode").setNumber(1);
    }

    public static void flashLeds()
    {
        table.getEntry("ledMode").setNumber(2);
    }

    public static boolean isTargetVisible()
    {
        return table.getEntry("tv").getDouble(0) > 0;
    }

    public static boolean isRobotLinedUp()
    {
        return (isTargetVisible() && Math.abs(getAngleOffTarget()) <= 1);
    }

}