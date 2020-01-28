package org.firstinspires.ftc.teamcode;

public class CONSTANTS {
    private double ticksPerRev = 1120;
    private double circumference = ((3.141593) * 4.00);

    public double greenBlack = 480;
    public double blueBlack = 238;
    public double redBlack = 252;
    public double alphaBlackIdeal = 965;
    public double hueBlackIdeal = 0;

    public double greenYellow = 1726;
    public double blueYellow = 368.5;
    public double redYellow = 1184;
    public double alphaYellowIdeal = 3247;
    public double hueYellowIdeal = 0;

    public double greenFoundation = 859;
    public double blueFoundation = 478;
    public double redFoundation = 444;
    public double alphaFoundationIdeal = 1764;
    public double hueFoundationIdeal = 0;

    /*
    Revised values
     */

    public double avgGreenBlackFAR = 399;
    public double avgBlueBlackFAR = 196.67;
    public double avgRedBlackFAR = 217.667;
    public double avgAlphaFAR = 810.667;

    public double avgGreenBlackMID = 502;
    public double avgBlueBlackMID = 255;
    public double avgRedBlackMID = 271;
    public double avgAlphaMID = 1019;

    public double avgGreenBlackCLOSE = 596;
    public double avgBlueBlackCLOSE = 291;
    public double avgRedBlackCLOSE = 326.5;
    public double avgAlphaCLOSE = 1200;



    public double distance2encoder = 26.7;

    public double distance2encoderNew = 40.922;

    public double distance2encoderFullVoltage = 43.4475;

    public double accelerationTime = 10;

    double powerDown = -0.5, powerUp = 0.5;

    public double ticksPerCm = (double) ticksPerRev/circumference;

}