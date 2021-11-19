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

    val TICKS_REVOLUTION = 103.8
    val WHEEL_DIAMETER = 2.625 // Inches6
    val MOTOR_SPIN = WHEEL_DIAMETER * PI * TICKS_PER_REVOLUTION
    val CAROUSEL_DIAMETER = 15
    val  CAROUSELSPIN = CAROUSEL_DIAMETER * PI * TICKS_REVOLUTION


    init {
        motor.setRunMode(Motor.RunMode.RawPower)
        // So it stays in place when no power is applied
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE)

        motor.setDistancePerPulse(DISTANCE_PER_PULSE)
        motor.resetEncoder()
    }

    //
    fun move() {
        var speed = 0.1
        var relativePosition = 0.0;
        var count = 0.0

        while ( motor.currentPosition <= 4712) {
            motor.set(speed)
            relativePosition = (motor.currentPosition -  100 * count)
            if (relativePosition >= 100) {
                speed = speed + 0.1
                count++
            }
        }
        motor.stopMotor()
        motor.resetEncoder()
    }

    fun move2() {
        while (motor.currentPosition > -700){
            if (motor.currentPosition/175  <= -1) {
                motor.set(-0.21)
            }
            else if (motor.currentPosition/175 >= -2){
                motor.set(-0.25)
            }
        }
        motor.stopMotor()
        motor.resetEncoder()
    }

    fun test()
    {

        motor.set(carousel_power)


    }



}