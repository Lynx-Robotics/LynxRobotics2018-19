package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "operationControl", group = "smart")
public class operationControl extends TeleBase {

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            aBtn = gamepad1.a;
            bBtn = gamepad1.b;
            telemetry.addData("Automatic Grabbing State: ", autoGrabState);
            //telemetry.addData("Grab State: ", grabberState);
            //telemetry.addData("Tape Spotted: ", !tapeSpotted());
            telemetry.addData("Are we on the scoring side? ", crossSide());
            //telemetry.addData("Here is the speedMultip: ", getSpeedMultip(gamepad1.right_trigger));
            telemetry.addData("Percent Change Color: ", percentChange(chart.bottomColorSensor));
            telemetry.addData("tapeSpotted: ", tapeSpottedPercentBase(35));
            telemetry.addData("Left Color Sensor Percent Change: ", percentChange(chart.colorSensorLeft));
            telemetry.addData("Right Color Sensor Percent Change: ", percentChange(chart.colorSensorRight));
            telemetry.addData("Skystone Spotted: ", SkyStoneBase(chart.colorSensorLeft));
            telemetry.addData("SkyStone Spotted: ", SkyStoneBase(chart.colorSensorRight));
            telemetry.addData("Is the SkyStone Spotted: ", SkystonePercent(chart.colorSensorLeft));

            toggleAutoGrab(aBtn);

            chart.TL.setPower(gamepad1.right_stick_y * getSpeedMultip(gamepad1.right_trigger));
            chart.BL.setPower(gamepad1.right_stick_y * getSpeedMultip(gamepad1.right_trigger));
            chart.TR.setPower(gamepad1.left_stick_y * getSpeedMultip(gamepad1.right_trigger));
            chart.BR.setPower(gamepad1.left_stick_y * getSpeedMultip(gamepad1.right_trigger));

            smartGrab();

            telemetry.update();
        }
    }
}
