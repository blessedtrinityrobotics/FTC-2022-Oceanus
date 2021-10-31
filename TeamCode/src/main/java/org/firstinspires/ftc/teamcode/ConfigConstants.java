package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

import org.opencv.core.Scalar;

/**
 * Used by FTC dashboard to change values on the fly
 */
@Config
class ConfigConstants {
    public static double motorMult = 0.5;
    public static double dumpAngle = 0.7;
    public static double neutralAngle = 0.9;
    public static double pickupAngle = 0.95;
    public static double hue = 30;
    public static double sat = 255;
    public static double val = 230;
    public static double lowHue = 15;
    public static double lowSat = 75;
    public static double lowVal = 150;
    public static double turnTime = 2500;
}
