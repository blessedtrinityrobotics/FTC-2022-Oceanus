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
        telemetry.addData("Level", level)
        telemetry.update()

        //drivetrain.driveByDistance(0.25, -30.0, this)
        drivetrain.driveByDistance(0.1, 12.0, this)
    }
}