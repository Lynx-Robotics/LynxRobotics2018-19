package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name = "accelerationTesterV2")
public class accelerationTesterV2 extends LinearOpMode {
    DcMotor TL, BL;
    double adjustedPower;
    boolean DebugSwitch=false;

    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        TL = hardwareMap.get(DcMotor.class, "TL");
        BL = hardwareMap.get(DcMotor.class, "BL");

        adjustedPower = sqrtAcceleration(1.0, 7);

        waitForStart();
        runtime.reset();
        goToPosition(10000, adjustedPower);

        while(opModeIsActive() && DebugSwitch){
            telemetry.addData("Current Position: ", TL.getCurrentPosition());
            telemetry.addData("Current Position: ", BL.getCurrentPosition());
            telemetry.update();
        }
    }

    public double sqrtAcceleration(double targetPower, double accelerationTime){
        double VTarg = targetPower;
        double pComplete = (runtime.seconds() / accelerationTime);
        double fixedPComplete = Range.clip(pComplete, 0.0, 1.0);

        double transferVal = Math.sqrt(fixedPComplete);
        double adjustedValue = (VTarg * transferVal);

        return adjustedValue;
    }

    public void resetEncoders(DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motor.setPower(0);
    }

    public void goToPosition(double position, double power) {
        resetEncoders(TL);

        int motorPosition = TL.getCurrentPosition();

        TL.setPower(power);
        BL.setPower(power);

        while ((motorPosition <= position) /*&&  pError>.25*/) {
            telemetry.addData("Current Position: ", TL.getCurrentPosition());
            telemetry.update();

            motorPosition = TL.getCurrentPosition();
            adjustedPower = sqrtAcceleration(1.0, 7);
        }
        TL.setPower(0);
        BL.setPower(0);

        DebugSwitch = true;
    }
}
