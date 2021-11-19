package org.firstinspires.ftc.teamcode.test

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.hardware.*
import org.firstinspires.ftc.teamcode.DuckDetectionPipelineJava
import org.firstinspires.ftc.teamcode.getWebcam
import org.openftc.easyopencv.OpenCvWebcam

@Autonomous(name = "Test Auto", group = "Test")
class TestAuto : LinearOpMode() {
    lateinit var webcam: OpenCvWebcam
    lateinit var pipeline: DuckDetectionPipelineJava
    lateinit var drivetrain: Drivetrain

    override fun runOpMode() {
        pipeline = DuckDetectionPipelineJava(telemetry)
        webcam = getWebcam(hardwareMap, telemetry)
        drivetrain = Drivetrain(hardwareMap, telemetry, null, null)

        webcam.setPipeline(pipeline)

        waitForStart()
        drivetrain.setRunMode(Motor.RunMode.PositionControl)

        drivetrain.forwardByDistance(0.25, 12.0)

        while (opModeIsActive()) {
            telemetry.update()
        }

    }
}