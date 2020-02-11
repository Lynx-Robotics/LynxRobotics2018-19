package org.firstinspires.ftc.teamcode;

public class acceleration {

    public double acceleratorCorrector(double targetPower, double currentPower){
        double pError;

        pError = ((currentPower - targetPower)/targetPower);

        if(isInRange(currentPower, 0.05, targetPower)){
            return 0;
        }
        else if(currentPower < targetPower){
            return Math.abs((pError/9));
        }
        else if (currentPower > targetPower){
            return (Math.abs((pError/9)) * -1.0);
        }
        else {
            return 0;
        }
    }

    public boolean isInRange(double number, double tolerance, double targ) {
        if (((targ + tolerance) > number) && ((targ - tolerance) < number)) {

            return true;
        } else {
            return false;
        }
    }
}