package org.firstinspires.ftc.teamcode

import com.arcrobotics.ftclib.hardware.motors.Motor
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

@TeleOp(name="Main TeleOp")
class MainTeleOp : OpMode() {
    // the lateinit modifier allows you to wait to initialize a variable in a neat fashion

    // I've made specific classes to control the functionality of the hardware on the robot
    // So I can reuse the code between Auto's and Teleops
    private lateinit var drivetrain: Drivetrain
    private lateinit var carousel: CarouselServo

    override fun init() {
        carousel = CarouselServo(hardwareMap, telemetry)
        drivetrain = Drivetrain(hardwareMap, telemetry, Motor.RunMode.RawPower)
    }

    override fun loop() {
        val forward = gamepad1.left_stick_y
        val lateral = gamepad1.left_stick_x
        val yaw = gamepad1.right_stick_x

        drivetrain.mecanumDrive(forward.toDouble(), lateral.toDouble(), yaw.toDouble())

        if (gamepad1.a) {
            carousel.spin()
        } else if (gamepad1.b) {
            carousel.reverse()
        } else {
            carousel.stop()
        }


        telemetry.update()
    }

}