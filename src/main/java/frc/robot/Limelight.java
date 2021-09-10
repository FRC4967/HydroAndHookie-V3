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

    public static double getAngleOffTarget()
    {
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
        return (distance + (CAMERA_DISTANCE_FROM_CENTER_OF_ROBOT - ((Math.cos(Math.toRadians(angleOff))) * CAMERA_DISTANCE_FROM_CENTER_OF_ROBOT)));
    }

    public static int getBottomShooterVelocity()
    {
        double distance = getDistanceAdjustedByAngle(getDistanceFromTarget(), getAngleOffTarget());
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
}