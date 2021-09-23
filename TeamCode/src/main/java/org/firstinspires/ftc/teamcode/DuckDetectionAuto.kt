package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvWebcam
import org.openftc.easyopencv.OpenCvCameraRotation

import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCamera.AsyncCameraOpenListener


@Autonomous(name = "OpenCV Webcam Test", group = "Tests")
class DuckDetectionAuto : LinearOpMode() {
    lateinit var webcam: OpenCvWebcam

    override fun runOpMode() {
        val cameraMonitorViewId = hardwareMap.appContext.resources.getIdentifier(
            "cameraMonitorViewId",
            "id",
            hardwareMap.appContext.packageName
        )
        webcam = OpenCvCameraFactory.getInstance().createWebcam(
            hardwareMap.get(
                WebcamName::class.java, "Webcam 1"
            ), cameraMonitorViewId
        )

        webcam.setPipeline(DuckDetectionPipeline())

        webcam.openCameraDeviceAsync(object : AsyncCameraOpenListener {
            override fun onOpened() {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT)
            }

            fun onError(errorCode: Int) {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        })

        telemetry.addLine("Waiting for start")
        telemetry.update()

        waitForStart()

        while (opModeIsActive()) {
            telemetry.addData("Frame Count", webcam.frameCount);
            telemetry.addData("FPS", String.format("%.2f", webcam.fps));
            telemetry.addData("Total frame time ms", webcam.totalFrameTimeMs);
            telemetry.addData("Pipeline time ms", webcam.pipelineTimeMs);
            telemetry.addData("Overhead time ms", webcam.overheadTimeMs);
            telemetry.addData("Theoretical max FPS", webcam.currentPipelineMaxFps);
            telemetry.update();
        }
    }
}