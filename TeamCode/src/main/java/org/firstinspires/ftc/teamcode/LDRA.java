package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "")
public class LDRA extends LinearOpMode {
    /*
    Project LDRA (Level Driven Response Algorithm) operates through the use of a non-responsive
    encoder driven program
     */

    public DcMotor armE;
    public DcMotor armR;


    @Override
    public void runOpMode() throws InterruptedException {
        armE = hardwareMap.get(DcMotor.class, "armE");
        armR = hardwareMap.get(DcMotor.class, "armR");
        armE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armE.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        while (opModeIsActive()) {
            armE.setPower(gamepad2.right_stick_y / 2);
            armR.setPower(gamepad2.left_stick_y / 2);

            telemetry.addData("ArmR", armR.getCurrentPosition());
            telemetry.addData("ArmE", armE.getCurrentPosition());
            telemetry.update();
        }
    }
}
