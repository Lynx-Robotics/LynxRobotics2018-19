package org.firstinspires.ftc.teamcode;

public class PController {
    private double Kp; //gain for proportional controller
    private int input;
    private boolean enabled = false;
    private double error;

    public PController(double Kp){
        this.Kp = Kp;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public double doP(int targetEncPos){
        error = targetEncPos - input;
        return Kp * error;
    }


}
