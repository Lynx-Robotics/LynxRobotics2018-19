package org.firstinspires.ftc.teamcode;

public class TeleOp_MT extends TeleBase {

    double speedMultip;
    int speedSelector = 0;
    int intakeCap = 1, rotationPos = 1, elevDown = 1, internalPhase = 1;

    double DOWN_POS_CAP = 0.0;

    boolean grabState = false;

    @Override
    public void runOpMode() throws InterruptedException {
        map.init(hardwareMap);

        Runnable odometrySubSystem = new odometrySubSystem();
        Thread oss = new Thread(odometrySubSystem);
        oss.start();

        waitForStart();
        while (opModeIsActive()) {
            /*
            Driver Controls
             */

            //wheel drive code;
            map.TL.setPower((-gamepad1.right_stick_y /*+ correctionTL*/) * speedMultip);
            map.BL.setPower((-gamepad1.right_stick_y /*+ correctionBL*/) * speedMultip);
            map.TR.setPower((-gamepad1.left_stick_y /*+ correctionBR*/) * speedMultip);
            map.BR.setPower((-gamepad1.left_stick_y /*+ correctionBR*/) * speedMultip);

            //speed selector Range Setter
            if (gamepad1.left_bumper) {
                speedSelector--;
                if (speedSelector < 0) {
                    speedSelector = 0;
                }
                while (gamepad1.left_bumper) ;
            }

            //speedSelector Range Setter
            if (gamepad1.right_bumper) {
                speedSelector++;
                if (speedMultip > 4) {
                    speedSelector = 0;
                }
                while (gamepad1.right_bumper) ;
            }

            //speedSelector setter for speeds
            if (speedSelector == 0) {
                speedMultip = 0.25;
            } else if (speedSelector == 1) {
                speedMultip = 0.40;
            } else if (speedSelector == 2) {
                speedMultip = 0.60;
            } else if (speedSelector == 3) {
                speedMultip = 0.70;
            } else if (speedSelector == 4) {
                speedMultip = 1.0;
            }

            if (gamepad1.dpad_right) {
                map.TL.setPower((-speedSelector + correctionTL));
                map.BL.setPower((speedSelector - correctionBL));
                map.TR.setPower((speedSelector - correctionBR));
                map.BR.setPower((-speedSelector + correctionBR));
                while (gamepad1.dpad_right) ;
            }

            if (gamepad1.dpad_left) {
                map.TL.setPower((speedSelector - correctionTL));
                map.BL.setPower((-speedSelector + correctionBL));
                map.TR.setPower((-speedSelector + correctionBR));
                map.BR.setPower((speedSelector - correctionBR));
                while (gamepad1.dpad_left) ;
            }

            if (map.TAPEMOTOR.getCurrentPosition() > -15) {
                intakeCap = 0;
            } else {
                intakeCap = 1;
            }

            if (gamepad1.a) {
                rotationPos++;
                if (rotationPos == 4) {
                    rotationPos = 1;
                }
                if (rotationPos == 1) {
                    map.TAPEROT.setPosition(0.25);
                } else if (rotationPos == 2) {
                    map.TAPEROT.setPosition(0.65);
                } else if (rotationPos == 3) {
                    map.TAPEROT.setPosition(0.96);
                }
                while (gamepad1.a) ;
            }

            if (gamepad1.dpad_down && gamepad2.dpad_down) {
                map.capServo.setPosition(DOWN_POS_CAP);
                while (gamepad1.dpad_down) ;
            }

            map.TAPEMOTOR.setPower((gamepad1.left_trigger * intakeCap) + (-gamepad1.right_trigger));

            /*
            Auxilliary Driver
             */

            if ((map.elevMotor.getCurrentPosition() < 10) && !gamepad2.right_bumper) {
                elevDown = 0;
            } else if (gamepad2.right_bumper) {
                elevDown = 1;
                while (gamepad2.right_bumper) {

                }
                resetEncoders(map.elevMotor);
            }

            if (gamepad2.a) {
                toggleGlobalVar(grabState);
                internalPhase++;
                while (gamepad2.a) ;
            }

            if (grabState == false && internalPhase == 1) {
                map.middleGrab.setPosition(0.8);
            } else if (grabState == false) {
                map.middleGrab.setPosition(0.8);
            } else if (grabState == true) {
                map.middleGrab.setPosition(0.0);
            }

            map.elevMotor.setPower((-gamepad2.left_trigger * elevDown) + (gamepad2.right_trigger));
        }
    }
}
