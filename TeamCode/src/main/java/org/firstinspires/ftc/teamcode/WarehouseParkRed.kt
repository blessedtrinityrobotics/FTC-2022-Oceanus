package org.firstinspires.ftc.teamcode

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.ConfigConstants.turnTime

@Autonomous(name = "Warehouse Park Red")
class WarehouseParkRed : LinearOpMode() {
    lateinit var drivetrain: Drivetrain
    lateinit var carousel: CarouselServo

    override fun runOpMode() {
        drivetrain = Drivetrain(hardwareMap, telemetry)
        carousel = CarouselServo(hardwareMap, telemetry)

        drivetrain.setRunMode(Motor.RunMode.PositionControl)

        waitForStart()

        sleep(500)
        drivetrain.driveByDistance(0.5, 42.0, this)
        sleep(100)
        drivetrain.mecanumDrive(0.0, 0.0, 2.0)
        sleep(turnTime.toLong())
        drivetrain.stop()

    }
}