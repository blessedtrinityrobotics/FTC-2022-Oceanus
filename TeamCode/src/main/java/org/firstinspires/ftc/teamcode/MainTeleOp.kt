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
    private  lateinit var slide: Slide
    private lateinit var scoop: Scoop

    override fun init() {
        carousel = CarouselServo(hardwareMap, telemetry)
        drivetrain = Drivetrain(hardwareMap, telemetry, Motor.RunMode.RawPower)
        slide = Slide(hardwareMap, telemetry)
        scoop = Scoop(hardwareMap, telemetry)

        drivetrain.setRunMode(Motor.RunMode.RawPower)
    }

    override fun loop() {
        val forward = gamepad1.left_stick_x
        val lateral = gamepad1.left_stick_y
        val yaw = -gamepad1.right_stick_x

        drivetrain.mecanumDrive(forward.toDouble(), lateral.toDouble(), yaw.toDouble())

        if (gamepad1.right_bumper) {
            carousel.spin()
        } else if (gamepad1.left_bumper) {
            carousel.reverse()
        } else {
            carousel.stop()
        }

        if (gamepad1.dpad_up) {
            slide.move(0.3)
        } else if (gamepad1.dpad_down) {
            slide.move(-0.3)
        } else {
            slide.move(0.0)
        }

        if (gamepad1.y)
            scoop.pickup()
        else if (gamepad1.x)
            scoop.reset()
        else if (gamepad1.a)
            scoop.dump()

        telemetry.update()
    }

}