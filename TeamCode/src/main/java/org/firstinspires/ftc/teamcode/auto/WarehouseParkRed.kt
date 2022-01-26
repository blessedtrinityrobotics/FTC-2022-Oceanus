package org.firstinspires.ftc.teamcode.auto

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.hardware.CarouselServo
import org.firstinspires.ftc.teamcode.ConfigConstants.turnTime
import org.firstinspires.ftc.teamcode.hardware.*

@Disabled
@Autonomous(name = "Warehouse Park Red")
class WarehouseParkRed : LinearOpMode() {
    lateinit var drivetrain: Drivetrain
    lateinit var carousel: CarouselServo

    override fun runOpMode() {
        drivetrain = Drivetrain(hardwareMap, telemetry, null, null)
        carousel = CarouselServo(hardwareMap, telemetry)

        drivetrain.setRunMode(Motor.RunMode.PositionControl)

        waitForStart()

        sleep(500)
        drivetrain.forwardByDistance(0.5, 42.0)
        sleep(100)
        drivetrain.mecanumDrive(0.0, 0.0, 2.0, 1.0)
        sleep(turnTime.toLong())
        drivetrain.stop()

    }
}