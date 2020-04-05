/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltaevent.event

import java.util.*

interface Event {


    abstract fun execute(arg1: Any, arg2: Any)

    abstract fun execute(arg1: Any)

    abstract fun execute(args: ArrayList<Any>)

    abstract fun execute(args: HashMap<Any, Any>)


}