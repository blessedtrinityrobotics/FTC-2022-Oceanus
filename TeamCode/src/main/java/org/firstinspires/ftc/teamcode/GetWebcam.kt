package org.firstinspires.ftc.teamcode

import com.acmerobotics.dashboard.FtcDashboard
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.openftc.easyopencv.*

/**
 * Creates webcam instance, sets the pipeline to the Duck Detector,
 * and opens the camera. You can see the camera stream by pressing 'init' on the DC,
 * going to the top right, and pressing 'Camera Stream'
 */
fun getWebcam(hardwareMap: HardwareMap, telemetry: Telemetry): OpenCvWebcam {
    val cameraMonitorViewId = hardwareMap.appContext.resources.getIdentifier(
        "cameraMonitorViewId",
        "id",
        hardwareMap.appContext.packageName
    )
    val webcam = OpenCvCameraFactory.getInstance().createWebcam(
        hardwareMap.get(
            WebcamName::class.java, CAM_NAME
        ), cameraMonitorViewId
    )

    webcam.openCameraDeviceAsync(object : OpenCvCamera.AsyncCameraOpenListener {
        override fun onOpened() {
            webcam.startStreaming(CAM_RES_X, CAM_RES_Y, OpenCvCameraRotation.UPRIGHT)
        }
        override fun onError(code: Int) {}
    })
    FtcDashboard.getInstance().startCameraStream(webcam, 0.0)
    return webcam
}