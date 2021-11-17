package org.firstinspires.ftc.teamcode.auto

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.*
import org.firstinspires.ftc.teamcode.hardware.Drivetrain
import org.firstinspires.ftc.teamcode.hardware.Scoop
import org.firstinspires.ftc.teamcode.hardware.Slide
import org.openftc.easyopencv.OpenCvWebcam

@Autonomous(name="Duck Detection & Dump Auto", group="Auto")
class DuckDumpAuto : LinearOpMode() {
    lateinit var slide: Slide
    lateinit var webcam: OpenCvWebcam
    lateinit var pipeline: DuckDetectionPipelineJava
    lateinit var drivetrain: Drivetrain
    lateinit var scoop: Scoop
    lateinit var imu: IMU

    override fun runOpMode() {
        pipeline = DuckDetectionPipelineJava(telemetry)
        slide = Slide(hardwareMap, telemetry)
        scoop = Scoop(hardwareMap, telemetry)
        webcam = getWebcam(hardwareMap, telemetry)
        imu = IMU(hardwareMap, telemetry)
        drivetrain = Drivetrain(hardwareMap, telemetry, this, imu)

        telemetry.addLine("Calibrating gyro...")
        telemetry.update()
        // make sure the gyro is calibrated before continuing
        while (!isStopRequested && imu.imu.isGyroCalibrated) {
            sleep(50)
            idle()
        }
        telemetry.update()

        webcam.setPipeline(pipeline)

        drivetrain.setRunMode(Motor.RunMode.PositionControl)

        waitForStart()

        sleep(1000)
        val level = pipeline.level
        telemetry.addData("Level", level)
        telemetry.update()


        drivetrain.forwardByDistance(0.1, 12.0)

        drivetrain.turnToHeading(0.25, 90.0)

        webcam.stopStreaming()
    }
}