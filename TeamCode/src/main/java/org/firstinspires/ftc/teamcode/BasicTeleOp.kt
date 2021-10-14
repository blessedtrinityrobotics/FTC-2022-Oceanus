package org.firstinspires.ftc.teamcode

import com.arcrobotics.ftclib.util.MathUtils.clamp
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

@TeleOp(name="Basic Kotlin TeleOp") // Compatible with Java annotations
class BasicTeleOp : OpMode() {
    // the lateinit modifier allows you to wait to initialize a variable in a neat fashion
    private lateinit var frontLeftDrive: DcMotor
    private lateinit var frontRightDrive: DcMotor
    private lateinit var backLeftDrive: DcMotor
    private lateinit var backRightDrive: DcMotor
    private lateinit var carousel: CRServo

    override fun init() {
        frontLeftDrive = hardwareMap.dcMotor.get(FL_MOTOR_NAME)
        frontRightDrive = hardwareMap.dcMotor.get(FR_MOTOR_NAME)
        backLeftDrive = hardwareMap.dcMotor.get(BL_MOTOR_NAME)
        backRightDrive = hardwareMap.dcMotor.get(BR_MOTOR_NAME)
        carousel = hardwareMap.crservo.get(CAROUSEL_SERVO_NAME)

        frontLeftDrive.direction = DcMotorSimple.Direction.FORWARD
        backLeftDrive.direction = DcMotorSimple.Direction.FORWARD

        frontRightDrive.direction = DcMotorSimple.Direction.REVERSE
        backRightDrive.direction = DcMotorSimple.Direction.REVERSE
    }

    override fun loop() {
        val forward = gamepad1.left_stick_y
        val lateral = gamepad1.right_stick_x
        val yaw = gamepad1.left_stick_y

        val r = hypot(gamepad1.left_stick_x, gamepad1.left_stick_y) * ConfigConstants.motorMult
        val robotAngle = atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4
        val rightX = gamepad1.right_stick_x
        val v1 = clamp(r * cos(robotAngle) + rightX, -1.0, 1.0)
        val v2 = clamp(r * sin(robotAngle) - rightX, -1.0, 1.0)
        val v3 = clamp(r * sin(robotAngle) + rightX, -1.0, 1.0)
        val v4 = clamp(r * cos(robotAngle) - rightX, -1.0, 1.0)

        // Instead of using getters/setters, just let kotlin do it for you!
        frontLeftDrive.power = v1.toDouble()
        frontRightDrive.power = v2.toDouble()
        backLeftDrive.power = v3.toDouble()
        backRightDrive.power = v4.toDouble()

        if (gamepad1.a) {
            carousel.power = 1.0
        } else if (gamepad1.b) {
            carousel.power = -1.0
        } else {
            carousel.power = 0.0
        }

        // This part is basically synonymous with java
        telemetry.addData("FL Power", v1)
        telemetry.addData("FR Power", v2)
        telemetry.addData("BL Power", v3)
        telemetry.addData("BR Power", v4)
        telemetry.update()
    }

}