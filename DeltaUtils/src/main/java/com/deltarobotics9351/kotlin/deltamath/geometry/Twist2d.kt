/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltamath.geometry

class Twist2d {

    var tw : Vec2d = Vec2d()
    var th : Rot2d = Rot2d()

    constructor (x : Double, y : Double, theta : Rot2d) {
        this.tw = Vec2d(x, y)
        this.th = Rot2d(theta)
    }

    constructor (vec : Vec2d, theta : Rot2d){
        this.tw = Vec2d(vec)
        this.th = Rot2d(theta)
    }

    constructor (o: Twist2d){
        this.tw = o.vec()
        this.th = o.rot();
    }

    fun vec() : Vec2d { return tw }

    fun rot() : Rot2d { return th; }

    fun x() : Double { return tw.x() }

    fun y() : Double { return tw.y() }

    fun theta() : Double { return th.getRadians() }

}