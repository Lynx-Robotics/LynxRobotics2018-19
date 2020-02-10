package org.firstinspires.ftc.teamcode;


import android.graphics.drawable.GradientDrawable;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.internal.android.dx.io.instructions.OneRegisterDecodedInstruction;

@Autonomous(name = "imuImplementation")
public class imuImp extends autoBaseV3 {

    BNO055IMU imu;
    Orientation angle;
    Orientation lastAngles = new Orientation();
    double globalAngle;
    PIDController pidRotate, pidDrive;

    @Override
    public void runOpMode() throws InterruptedException {
        chart.init(hardwareMap);
     BNO055IMU.Parameters parameters=new BNO055IMU.Parameters();
     parameters.angleUnit=BNO055IMU.AngleUnit.DEGREES;
     waitForStart();

     while(opModeIsActive()){
         angle=imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
         double angleNeeded=angle.firstAngle;
         encoderStrafeRight(distance2encoder(50),0.7);

         angle=imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
         telemetry.addData("Head",angle.firstAngle);
         telemetry.addData("Roll", angle.secondAngle);
         telemetry.addData("Pitch", angle.thirdAngle);
         telemetry.update();
         Thread.sleep(100);

         //angle=imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
         Orientation angleNow=imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
         //double angleNeeded=angle.firstAngle;
         double angleThis=angleNow.firstAngle;
         double rightAngle=angleThis-angleNeeded;
         double leftAngle=angleNeeded-angleThis;
         if(angleThis>angleNeeded){
             do {

                 double power = pidRotate.performPID(rightAngle); // power will be - on right turn.
                 corrRight(power);
             } while (opModeIsActive() && !pidRotate.onTarget());

         }

         if(angleThis<angleNeeded){
             do {
                 double power = pidRotate.performPID(leftAngle); // power will be - on right turn.
                 corrLeft(power);
             } while (opModeIsActive() && !pidRotate.onTarget());

         }
     }
    }

}
