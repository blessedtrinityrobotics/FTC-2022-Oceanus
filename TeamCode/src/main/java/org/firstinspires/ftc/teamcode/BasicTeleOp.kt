package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
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

    override fun init() {
        frontLeftDrive = hardwareMap.dcMotor.get(FL_MOTOR_NAME)
        frontRightDrive = hardwareMap.dcMotor.get(FR_MOTOR_NAME)
        backLeftDrive = hardwareMap.dcMotor.get(BL_MOTOR_NAME)
        backRightDrive = hardwareMap.dcMotor.get(BR_MOTOR_NAME)

        frontLeftDrive.direction = DcMotorSimple.Direction.REVERSE
        backLeftDrive.direction = DcMotorSimple.Direction.REVERSE
    }

    override fun loop() {
        val forward = gamepad1.left_stick_y
        val lateral = gamepad1.right_stick_x
        val yaw = gamepad1.left_stick_y

        val r = hypot(gamepad1.left_stick_x, gamepad1.left_stick_y) * ConfigConstants.motorMult
        val robotAngle = atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4
        val rightX = gamepad1.right_stick_x
        val v1 = r * cos(robotAngle) + rightX
        val v2 = r * sin(robotAngle) - rightX
        val v3 = r * sin(robotAngle) + rightX
        val v4 = r * cos(robotAngle) - rightX

        // Instead of using getters/setters, just let kotlin do it for you!
        frontLeftDrive.power = v1.toDouble()
        frontRightDrive.power = v2.toDouble()
        backLeftDrive.power = v3.toDouble()
        backRightDrive.power = v4.toDouble()

        // This part is basically synonymous with java
        telemetry.update()
    }

}