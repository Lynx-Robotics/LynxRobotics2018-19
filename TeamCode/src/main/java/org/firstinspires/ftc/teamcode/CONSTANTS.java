package org.firstinspires.ftc.teamcode;

public class CONSTANTS {
    private double ticksPerRev = 1120;
    private double circumference = 2 * (3.141593) * 5.08;

    double powerDown = -0.5, powerUp = 0.5;

    public double ticksPerCm = (double) ticksPerRev/circumference;

}
