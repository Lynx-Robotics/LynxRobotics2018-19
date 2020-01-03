package org.firstinspires.ftc.teamcode;

public class CONSTANTS {
    public double OPENPOSITION = 0.9;
    public double CLOSEPOSITION = 0.1;
    public double STALKPOWER = 0.3, STRAFEPOWER = 0.7, HALFPOWER = 0.5;
    public String VuforiaKey = "AYNlKWT/////AAABmUOJpYR5Ckn+j2H4V2NICucswBSgY0/bl0ZpMZYB7P30Me22HMLRjpIPDhq0k8mfaA/nHnYfYymiKJmb0x5mGJpFftppbCSQJhq8mQ0+MSliYMMZkC1kevVxkzHT25sDPptgAukVI2JGCz/+cEtLf8FsFUfYXlFTFCSLp4x9R0UWqa4VBBYFEhdyjRekayYy0qPiMP/RqXBgWJENVbHfqTfZRhnMcVfD3KsGpYB/bnTTE3kO37RTgVFLxuYAwRiw6eSXN/1A9v3fnpCempHT9MGF1LXnTDcQpUwkHzMhegLHUbxAg/KAeGcIV/yxBdKt7dO1/UZY89cXo1yLxGFHxeKOYp/R6S1SvGOnjjUSScYC";

    private boolean speedToggle;
    private boolean positionToggle;

    public double speedMultip = 1;

    public boolean isPositionToggle() {
        return positionToggle;
    }

    public void setPositionToggle(boolean positionToggle) {
        this.positionToggle = positionToggle;
    }

    public boolean isSpeedToggle() {
        return speedToggle;
    }

    public void setSpeedToggle(boolean speedToggle) {
        this.speedToggle = speedToggle;
    }
}
