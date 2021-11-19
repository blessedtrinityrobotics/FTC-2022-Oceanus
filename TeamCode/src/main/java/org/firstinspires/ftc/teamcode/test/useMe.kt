package org.firstinspires.ftc.teamcode.test;

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.hardware.*

@Autonomous(name = "Use me", group = "Test")
class useMe : LinearOpMode() {
    lateinit var drivetrain: Drivetrain

    override fun runOpMode() {
        drivetrain = Drivetrain(hardwareMap, telemetry, null, null)

        waitForStart()

        drivetrain.setRunMode(Motor.RunMode.PositionControl)
        drivetrain.forwardByDistance(0.5, 60.0)
//        sleep(2000)
//        drivetrain.stop()
        while (opModeIsActive()) {
            telemetry.addData("isRunning", opModeIsActive())
        }

    }
}
