package org.firstinspires.ftc.teamcode

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.openftc.easyopencv.OpenCvWebcam

@Autonomous(name="Duck Detection & Dump Auto", group="Auto")
class DuckDumpAuto : LinearOpMode() {
    lateinit var slide: Slide
    lateinit var webcam: OpenCvWebcam
    lateinit var pipeline: DuckDetectionPipelineJava
    lateinit var drivetrain: Drivetrain
    lateinit var scoop: Scoop

    override fun runOpMode() {
        pipeline = DuckDetectionPipelineJava(telemetry)
        slide = Slide(hardwareMap, telemetry)
        drivetrain = Drivetrain(hardwareMap, telemetry)
        scoop = Scoop(hardwareMap, telemetry)
        webcam = getWebcam(hardwareMap, telemetry)

        webcam.setPipeline(pipeline)

        drivetrain.setRunMode(Motor.RunMode.PositionControl)

        waitForStart()

        sleep(1000)
        val level = pipeline.level

        drivetrain.mecanumDrive(1.0, 0.0, 0.0)
        sleep(1000)
        drivetrain.stop()

        drivetrain.mecanumDrive(0.0, 1.0, 0.0)
        sleep(1000)
        drivetrain.stop()

        /*
            For the ducks, far right is level 1,
            middle is level 2,
            far left is level 3
         */

        slide.move(0.5)
        sleep(level.toLong() * 1000)
        slide.move(0.0)

        sleep(500)
        scoop.dump()
        sleep(100)
        scoop.pickup()

        drivetrain.mecanumDrive(-1.0, 0.0, 0.0)
        sleep(500)
        drivetrain.stop()
    }
}