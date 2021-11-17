package org.firstinspires.ftc.teamcode.hardware

import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.SLIDE_MOTOR_NAME
import kotlin.math.sign

class Slide(hardwareMap: HardwareMap, val telemetry: Telemetry) {
    val motor = Motor(hardwareMap, SLIDE_MOTOR_NAME, Motor.GoBILDA.RPM_223)

    val MAX_TICKS: Double = 3100.0
    val MIN_TICKS: Double = -10.0

    init {
        motor.setRunMode(Motor.RunMode.RawPower)
        // So it stays in place when no power is applied
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE)

        motor.resetEncoder()
    }

    fun move(power: Double) {
        // checks if we are within range
        val s = sign(power)
        if (s == 1.0 && motor.currentPosition <= MAX_TICKS)
            motor.set(power)
        else if (s == -1.0 && motor.currentPosition >= MIN_TICKS)
            motor.set(power)
        else
            motor.set(0.0)
    }

    fun test(power: Double) {
        motor.set(power)
    }

    fun logPosition() {
        telemetry.addData("Slide Position", motor.currentPosition)
    }

}