package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "strafeTest", group = "TEST")
@Disabled
public class strafeTest extends LinearOpMode {

    public DcMotor TL, TR, BL, BR;
    public boolean aBtn;
    public int state = 0;
    public double powerUp, powerDown;
    double globalAngle, power = .30, correction, rotation;
    Orientation lastAngles = new Orientation();
    BNO055IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);

        BL = hardwareMap.get(DcMotor.class, "BL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        TR = hardwareMap.get(DcMotor.class, "TR");
        TL = hardwareMap.get(DcMotor.class, "TL");

        TL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

        TL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        aBtn = gamepad1.a;
        powerUp = 0.5;
        powerDown = -0.5;

        ElapsedTime runtime = new ElapsedTime();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("POWER LEVEL: ", "powerUp is at " + powerUp);
            telemetry.addData("POWER LEVEL: ", "powerDown is at " + powerDown);

            if(gamepad1.a){
                runtime.reset();
            }

            if (gamepad2.a){
                resetAngle();
            }

            if(gamepad2.dpad_right){
                strafeCorrection();
                while(gamepad2.dpad_right){
                    TL.setPower(powerUp);
                    TR.setPower(powerDown);
                    BL.setPower(powerDown);
                    BR.setPower(powerUp);
                }
            }

            if(gamepad2.dpad_left){
                runtime.reset();
                while(gamepad2.dpad_left){
                    TL.setPower(powerDown);
                    TR.setPower(powerUp);
                    BL.setPower(powerUp);
                    BR.setPower(powerDown + (-joltControl(runtime, 1)));
                }
            }

            if (gamepad1.dpad_right) {
                TL.setPower(powerUp + joltControl(runtime));
                TR.setPower(powerDown);
                BL.setPower(powerDown);
                BR.setPower(powerUp);
            } else if (gamepad1.dpad_left) {
                TL.setPower(powerDown);
                TR.setPower(powerUp);
                BL.setPower(powerUp);
                BR.setPower(powerDown + (-joltControl(runtime)));
            } else {
                TL.setPower(gamepad1.left_stick_y);
                TR.setPower(gamepad1.right_stick_y);
                BL.setPower(gamepad1.left_stick_y);
                BR.setPower(gamepad1.right_stick_y);
            }


            telemetry.addData("POWER LEVEL: ", "TL - " + (TL.getPower()));
            telemetry.update();
        }

    }

    private void resetAngle() {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    public void strafeCorrection() {
        double pulseStrength = 0.05;

        if (getAngle() > 5) {
            TL.setPower(TL.getPower() + pulseStrength);
        }
        if (getAngle() < -5) {
            BL.setPower(BL.getPower() + pulseStrength);
        } else {
            pulseStrength = 0;
        }

    }

    private double getAngle() {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    public double joltControl(ElapsedTime runtime, int i) {

        if (runtime.seconds() < 1.2) {
            return 0.05;
        } else {
            return 0.0;
        }
    }

    public double joltControl(ElapsedTime runtime) {
        if (runtime.seconds()<=1){
            return 0.05;
        }
        else {
            return 0.0;
        }

    }

}
