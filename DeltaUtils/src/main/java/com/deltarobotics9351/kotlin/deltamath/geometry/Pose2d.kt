/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltamath.geometry

import com.deltarobotics9351.java.deltamath.geometry.Pose2d
import com.deltarobotics9351.java.deltamath.geometry.Vec2d

class Pose2d {

    private var vec: Vec2d? = null
    private var heading = 0.0

    constructor() {
        vec = Vec2d(0.0, 0.0)
        heading = 0.0
    }

    constructor(x: Double, y: Double, heading: Double) {
        vec = Vec2d(x, y)
        this.heading = heading
    }

    constructor(vec: Vec2d?, heading: Double) {
        this.vec = vec
        this.heading = heading
    }

    constructor(o: Pose2d) {
        vec = o.position
        heading = o.heading
    }

    fun getPosition(): Vec2d? {
        return vec
    }

    fun getHeading(): Double {
        return heading
    }

    fun add(o: Pose2d) {
        vec!!.add(o.position)
        heading += o.heading
    }

    fun divide(by: Double) {
        vec!!.divide(by)
        heading /= by
    }

    fun invert() {
        vec!!.invert()
        heading = -heading
    }

    fun multiply(by: Double) {
        vec!!.multiply(by)
        heading *= by
    }

    fun rotate(by: Double) {
        heading += by
    }

    override fun toString(): String {
        val v = vec.toString()
        val h = heading.toString()
        return "Pose2d($v, $h)"
    }

}