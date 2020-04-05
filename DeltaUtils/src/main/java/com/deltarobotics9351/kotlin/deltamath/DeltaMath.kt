/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltamath

/**
 * Class with WPILib FRC Lib math methods and extra custom ones.
 */
class DeltaMath {

    /**
     * Returns value clamped between low and high boundaries.
     *
     * @param value Value to clamp.
     * @param low   The lower boundary to which to clamp value.
     * @param high  The higher boundary to which to clamp value.
     */
    fun clamp(value: Int, low: Int, high: Int): Int {
        return Math.max(low, Math.min(value, high))
    }

    /**
     * Returns value clamped between low and high boundaries.
     *
     * @param value Value to clamp.
     * @param low   The lower boundary to which to clamp value.
     * @param high  The higher boundary to which to clamp value.
     */
    fun clamp(value: Double, low: Double, high: Double): Double {
        return Math.max(low, Math.min(value, high))
    }

    /**
     * Get the difference (delta) between two angles (in degrees)
     * @param angle1 The angle to be subtracted by the angle2
     * @param angle2 The angle to be subtracted to angle1
     * @return The result of the angles difference, considering the -360 to 360 range.
     */
    fun deltaDegrees(angle1: Double, angle2: Double): Double {
        var deltaAngle = angle1 - angle2
        if (deltaAngle < -180) deltaAngle += 360.0 else if (deltaAngle > 180) deltaAngle -= 360.0
        return deltaAngle
    }

}