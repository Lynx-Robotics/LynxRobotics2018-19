package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "newDriveTrain", group = "REBORN")
public class newDriveTrain extends TeleBase {
    CONSTANTS constants = new CONSTANTS();
    private double speedMultip = 1;

    DcMotor TL, TR, BL, BR;

    boolean aBtn2 = false;
    boolean leftBumper2 = false;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);


        /*TL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        TR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/

        aBtn2 = gamepad2.right_bumper;
        leftBumper2 = gamepad2.left_bumper;

        waitForStart();
        while (opModeIsActive()){

            toggleGrabberNew(gamepad2.left_bumper);

            if(grabberState){
                chart.middleGrab.setPosition(1.0);
            }
            else {
                chart.middleGrab.setPosition(0.0);
            }

            /*if(gamepad1.left_bumper){
                dropDL();
            }
            else{
               raiseDL();
            }*/

            if(gamepad1.dpad_left){
                chart.TL.setPower(constants.powerDown);
                chart.BL.setPower(constants.powerUp);
                chart.TR.setPower(constants.powerUp);
                chart.BR.setPower(constants.powerDown);
            }
            if(gamepad1.dpad_right){
                chart.TL.setPower(constants.powerUp);
                chart.BL.setPower(constants.powerDown);
                chart.TR.setPower(constants.powerDown);
                chart.BR.setPower(constants.powerUp);
            }
            else{
                chart.TL.setPower(gamepad1.left_stick_y * getSpeedMultip(gamepad1.right_trigger));
                chart.BL.setPower(gamepad1.left_stick_y * getSpeedMultip(gamepad1.right_trigger));
                chart.TR.setPower(gamepad1.right_stick_y * getSpeedMultip(gamepad1.right_trigger));
                chart.BR.setPower(gamepad1.right_stick_y * getSpeedMultip(gamepad1.right_trigger));
            }
        }
    }

    public double getSpeedMultip(double controlPower){
        return speedMultip - (controlPower);
    }

    public void dropDL() {
        chart.hookLeft.setPosition(0.1);
        chart.hookRight.setPosition(0.1);
    }
    public void raiseDL() {
        chart.hookLeft.setPosition(0.9);
        chart.hookRight.setPosition(0.9);
        chart.middleGrab.setPosition(0.0);
    }
}
