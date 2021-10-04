package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.openftc.easyopencv.OpenCvWebcam

@Autonomous(name = "Test Auto", group = "Test")
class TestAuto : LinearOpMode() {
    lateinit var webcam: OpenCvWebcam
    lateinit var pipeline: DuckDetectionPipelineJava

    override fun runOpMode() {
        val f = getWebcam(hardwareMap, telemetry)
        webcam = f.first
        pipeline = f.second

        waitForStart()

        while (opModeIsActive()) {


            telemetry.update()
        }

    }
}