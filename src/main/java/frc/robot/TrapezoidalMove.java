/*
 * TrapezoidalMove.h
 *
 *  Created on: Feb 17, 2017
 *      Author: *not* Joey *actually just aidan :)*
 */
package frc.robot;

import java.lang.Math;

public class TrapezoidalMove 
{
    private static double m_accel, m_decel, m_max_speed, m_distance;
    private static double m_t1, m_t2, m_t3;

    public TrapezoidalMove() {
        m_accel = 0.1; // ft/s/s
        m_decel = 0.13; // ft/s/s
        m_max_speed = 0.5; // ft/s
        m_distance = 3; // ft
        m_t1 = -99; // seconds
        m_t2 = -99; // seconds
        m_t3 = -99; // seconds
        // this->CalcParams();

    }

    public static void TrapezoidalMove(double Accel, double Decel, double MaxSpeed, double Distance) 
    {
        m_t1 = -99; // seconds
        m_t2 = -99; // seconds
        m_t3 = -99; // seconds
        m_accel = Accel;
        m_decel = Decel;
        m_max_speed = MaxSpeed;
        m_distance = Distance;
        // this->CalcParams();
    }

    public static void SetAccel(double value) 
    {
        m_accel = value;
    }

    public static void SetDecel(double value) 
    {
        m_decel = value;
    }

    public static void SetMaxSpeed(double value) 
    {
        m_max_speed = value;
    }

    public static void SetDistance(double value) 
    {
        m_distance = value;
        CalcParams();
    }

    public static void SetAll(double accel, double decel, double maxSpeed, double distance) 
    {
        m_accel = accel;
        m_decel = decel;
        m_max_speed = maxSpeed;
        m_distance = distance;
        CalcParams();
    }

    public static void CalcParams() 
    {
        double accel_time, decel_time, min_dist, accel_dist, decel_dist;

        accel_time = m_max_speed / m_accel;
        decel_time = m_max_speed / m_decel;
        accel_dist = 0.5 * m_accel * accel_time * accel_time;
        decel_dist = 0.5 * m_decel * decel_time * decel_time;
        min_dist = accel_dist + decel_dist;
        System.out.println("Min Distance" + min_dist + "\n");
        if (min_dist > m_distance) // never gets up to speed.
        {
            m_t1 = Math.pow(2.0 * m_distance / (m_accel + (m_accel * m_accel) / m_decel), 0.5);
            System.out.println("T1 is " + m_t1 + "\n");
            m_t2 = m_t1; // no constant velocity time
            m_t3 = m_accel / m_decel * m_t1 + m_t1;
        } 
        else 
        {
            m_t1 = accel_time;
            m_t2 = (m_distance - min_dist) / m_max_speed + m_t1; // time at end of constanct velocity
            m_t3 = decel_time + m_t2;
            System.out.println("Accel Distance: " + accel_dist + "\n");
            System.out.println("Decel Distance: " + decel_dist + "\n");
            System.out.println("T1 Time: " + m_t1 + "\n");
            System.out.println("T2 Time: " + m_t2 + "\n");
            System.out.println("T3 Time: " + m_t3 + "\n");
        }
        ;
        // SmartDashboard::PutNumber("T1", m_t1);
        // SmartDashboard::PutNumber("T2", m_t2);
        // SmartDashboard::PutNumber("T3", m_t3);
        // SmartDashboard::PutNumber("accel dist", accel_dist);
        // SmartDashboard::PutNumber("accel time", accel_time);
        // SmartDashboard::PutNumber("decel_dist", decel_dist);
    }

    public static double Position(double time) 
    {
        double position = 0.0;
        if (time < m_t1)
            position = 0.5 * m_accel * time * time;
        else if (time < m_t2)
            position = 0.5 * m_accel * m_t1 * m_t1 + (time - m_t1) * m_max_speed;
        else if (time <= m_t3)
            position = 0.5 * m_accel * m_t1 * m_t1 + (m_t2 - m_t1) * m_max_speed
                    + 0.5 * m_decel * (m_t3 - m_t2) * (m_t3 - m_t2) - 0.5 * m_decel * (m_t3 - time) * (m_t3 - time);
        else
            position = m_distance;

        return position;
    }

    public static double GetTotalTime() 
    {
        return m_t3;
    }

}