package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.DuckDetectionPipelineJava
import org.firstinspires.ftc.teamcode.getWebcam
import org.openftc.easyopencv.OpenCvWebcam

@Autonomous(name = "Test Auto", group = "Test")
class TestAuto : LinearOpMode() {
    lateinit var webcam: OpenCvWebcam
    lateinit var pipeline: DuckDetectionPipelineJava

    override fun runOpMode() {
        pipeline = DuckDetectionPipelineJava(telemetry)
        webcam = getWebcam(hardwareMap, telemetry)

        webcam.setPipeline(pipeline)


        waitForStart()

        while (opModeIsActive()) {
            telemetry.update()
        }

    }
}