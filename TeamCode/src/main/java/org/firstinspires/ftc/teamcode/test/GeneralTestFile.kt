package org.firstinspires.ftc.teamcode.test

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.hardware.CarouselMotor
import org.firstinspires.ftc.teamcode.hardware.Drivetrain
import java.security.acl.Group

@Autonomous(name  = "GENTESTFILE", group = "test")
class GeneralTestFile : LinearOpMode() {
    lateinit var drivetrain: Drivetrain
    lateinit var carouselMotor: CarouselMotor

    override fun runOpMode() {
        drivetrain = Drivetrain(hardwareMap, telemetry, null, null)
        carouselMotor = CarouselMotor(hardwareMap, telemetry)

        waitForStart()

        carouselMotor.motor.setRunMode(Motor.RunMode.PositionControl)

        while (opModeIsActive()) {
            carouselMotor.motor.setTargetDistance(90.0)
            carouselMotor.motor.set(0.9)
            telemetry.addData("POS:", carouselMotor.motor.currentPosition)
            telemetry.update()
        }

    }


}