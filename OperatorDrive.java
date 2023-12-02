package org.firstinspires.ftc.teamcode;

@TeleOp(name="OperatorDrive", group="Linear Opmode")
public class ManuelDrive extends LinearOpMode {

    private Blinker control_Hub;
    private DcMotor FrontLeft;
    private DcMotor FrontRight;
    private DcMotor BackLeft;
    private DcMotor BackRight;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        BackLeft = hardwareMap.get(DcMotor.class, "BackLeft");
        BackRight = hardwareMap.get(DcMotor.class, "BackRight");

        FrontLeft.setDirection(DcMotor.Direction.FORWARD);
        BackLeft.setDirection(DcMotor.Direction.FORWARD);
        FrontRight.setDirection(DcMotor.Direction.REVERSE);
        BackRight.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double max;
        double twist = -gamepad1.right_stick_y;
        double strafe = gamepad1.left_stick_x * 1.5;
        double drive = gamepad1.right_stick_x;

        // Check if any joystick input is non-zero before moving
        if (twist != 0.0 || strafe != 0.0 || drive != 0.0) {
            double[] speeds = {
                    (twist + strafe + drive),
                    (twist - strafe - drive),
                    (twist - strafe + drive),
                    (twist + strafe - drive)
            };

            max = Math.max(Math.abs(speeds[0]), Math.abs(speeds[1]));
            max = Math.max(max, Math.abs(speeds[2]));
            max = Math.max(max, Math.abs(speeds[3]));

            if (max > 1.0) {
                speeds[0] /= max;
                speeds[1] /= max;
                speeds[2] /= max;
                speeds[3] /= max;
            }

            FrontLeft.setPower(speeds[0]);
            FrontRight.setPower(speeds[1]);
            BackLeft.setPower(speeds[2]);
            BackRight.setPower(speeds[3]);
        } else {
            // If no joystick input, stop the robot
            FrontLeft.setPower(0.0);
            FrontRight.setPower(0.0);
            BackLeft.setPower(0.0);
            BackRight.setPower(0.0);
        }

        telemetry.update();
    }
}
