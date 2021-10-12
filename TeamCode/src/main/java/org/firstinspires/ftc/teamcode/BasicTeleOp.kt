package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor

@TeleOp(name="Basic Kotlin TeleOp") // Compatible with Java annotations
class BasicTeleOp : OpMode() {
    // the lateinit modifier allows you to wait to initialize a variable in a neat fashion
    private lateinit var frontLeftDrive: DcMotor
    private lateinit var frontRightDrive: DcMotor
    private lateinit var backLeftDrive: DcMotor
    private lateinit var backRightDrive: DcMotor

    override fun init() {
        frontLeftDrive = hardwareMap.dcMotor.get("front_left_drive")
        frontRightDrive = hardwareMap.dcMotor.get("front_right_drive")
        backLeftDrive = hardwareMap.dcMotor.get("back_left_drive")
        backRightDrive = hardwareMap.dcMotor.get("back_right_drive")
    }

    override fun loop() {
        val leftPower = gamepad1.left_stick_y
        val rightPower = gamepad1.right_stick_y

        // Instead of using getters/setters, just let kotlin do it for you!
        frontLeftDrive.power = leftPower.toDouble()
        frontRightDrive.power = -rightPower.toDouble()
        backLeftDrive.power = -leftPower.toDouble()
        backRightDrive.power = rightPower.toDouble()

        // This part is basically synonymous with java
        telemetry.addData("Left Power", leftPower)
        telemetry.addData("Right Power", rightPower)
        telemetry.update()
    }

}