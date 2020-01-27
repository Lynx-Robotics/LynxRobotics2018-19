package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.lang.reflect.Type;

@Autonomous(name = "ENCODER_BLUE_ONE_STONE_FULL")
public class EncoderBlueOneStoneFull extends autoBaseV2 {
    double distanceForward = distance2encoder(62.5);
    double distanceToBlock = distance2encoder(7.5);
    double distanceInchFor = distance2encoder(3.5);
    double distanceBack = distance2encoder(12.4);
    double distanceToBridge = distance2encoder(48);
    double distanceToFoundation = distance2encoder(95);

    double distanceToBackWall = distance2encoder(46);

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);



        waitForStart();
        //Phase 1
        goToPosition(chart.elevMotor, 300, 1.0);

        //Phase 2
        goToPositionSpecial(chart.TL, chart.TR, chart.BL, chart.BR, distanceForward, .3);

        //Phase 3
        strafe(-0.3);
        while(opModeIsActive() && !SkyStoneSpotted(chart.colorSensorRight)){

        }

        //Phase 4
        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distance2encoder(5), 0.2);
        goToPositionDown(chart.elevMotor, 13, -1.0);
        goToPosition(chart.TL, chart.TR, chart.BL, chart.BR, distanceInchFor, 0.2);

        //Phase 5
        dropDL();

        //Phase 6
        goToPosition(chart.elevMotor, 80, 1.0);
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distanceBack, -0.5);

        //Phase 7
        goToPositionStrafeLeft(chart.TL, chart.TR, chart.BL, chart.BR, distanceToFoundation, -1.0);

        //Phase 8
        driveForward(0.23);
        while(opModeIsActive() && FoundationDetected(chart.colorSensorLeft)){
            telemetry.addData("Going to the foundation", FoundationDetected(chart.colorSensorLeft));
            telemetry.update();
        }
        rest();

        //Phase 9
        goToPosition(chart.elevMotor, 1, -1.0);
        raiseDL();

        //Phase 10
        goToPositionBack(chart.TL, chart.TR, chart.BL, chart.BR, distanceToBackWall, -1.0);

        //Phase 11
        goToPositionStrafeRight(chart.TL, chart.TR, chart.BL, chart.BR, distanceToBridge, 1.0);

    }

    public boolean objDetected(ColorSensor cs){
        if(opModeIsActive()&&cs.alpha() > 800){
            return false;
        }
        else {
            return true;
        }
    }

    public void goToPositionSpecial(DcMotor motor1, DcMotor motor2, DcMotor motor3, DcMotor motor4, double position, double power) {
        int motorPosition = motor1.getCurrentPosition();

        motor1.setPower(power);
        motor2.setPower(power);
        motor3.setPower(power);
        motor4.setPower(power);

        while ((motorPosition <= position) && objDetected(chart.colorSensorLeft)) {
            telemetry.addData("Current Position: ", motor1.getCurrentPosition());
            telemetry.update();

            motorPosition = motor1.getCurrentPosition();
        }
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);

        chart.DebugSwitch = true;
    }
}
