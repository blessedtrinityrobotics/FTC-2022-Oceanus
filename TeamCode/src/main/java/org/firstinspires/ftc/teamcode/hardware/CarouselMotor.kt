package org.firstinspires.ftc.teamcode.hardware

import android.os.SystemClock.sleep
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.*;
import org.firstinspires.ftc.teamcode.ConfigConstants.*;
import kotlin.concurrent.thread
import kotlin.math.*


class CarouselMotor(hardwareMap: HardwareMap, val telemetry: Telemetry) {

    val motor = Motor(hardwareMap, CAROUSEL_MOTOR_NAME, Motor.GoBILDA.RPM_1620)

    val rotationCycle = 700



    init {
        motor.setRunMode(Motor.RunMode.RawPower)
        // So it stays in place when no power is applied
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE)

        motor.setDistancePerPulse(DISTANCE_PER_PULSE)
        motor.resetEncoder()
    }

    // used for moving duck from Carousel

    //used for when you are on the red alliance
    fun moveRed(){
        while (motor.currentPosition > -rotationCycle){
            if (motor.currentPosition > -rotationCycle/2) {
                motor.set(-0.25)
                telemetry.addData("together we made it", true)
            }
            else {
                motor.set(-0.21)
            }
        }
        motor.stopMotor()
        motor.resetEncoder()
    }

    fun moveBlue(){
        while (motor.currentPosition < rotationCycle){
            if (motor.currentPosition < rotationCycle/2) {
                motor.set(0.25)
                telemetry.addData("together we made it", true)
            }
            else {
                motor.set(0.21)
            }
        }
        motor.stopMotor()
        motor.resetEncoder()
    }

}