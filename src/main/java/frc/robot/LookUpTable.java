package frc.robot;

import java.util.HashMap;

public class LookUpTable 
{
    
    private static HashMap<Integer, Integer> lookUp;
    private static LookUpTable lookUpTableInstance;
    private static int highestKey;

    private LookUpTable()
    {
        // INSERT HASHMAP STARTING HERE

        // END OF HASHMAP

        highestKey = 0; // HIGHEST KEY OF HASHMAP
    }

    public static LookUpTable getInstance()
    {
        if (lookUpTableInstance == null)
        {
            lookUpTableInstance = new LookUpTable();
        }
        return lookUpTableInstance;
    }

    public static int getKey(int key)
    {
        return lookUp.get(key);
    }

    public static int getHighestKey()
    {
        return highestKey;
    }



}
