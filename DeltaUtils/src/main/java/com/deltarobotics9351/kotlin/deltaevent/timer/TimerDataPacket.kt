/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltaevent.timer

class TimerDataPacket {

    var msEventTime: Long = 0
    var msElapsedTime: Long = 0
    var msLastSystemTime: Long = 0
    var msStartSystemTime: Long = 0

    var repeat = false

    constructor (msEventTime: Long, msElapsedTime: Long, repeat: Boolean) {
        this.msElapsedTime = msElapsedTime
        this.msEventTime = msEventTime
        this.repeat = repeat
    }

    constructor (o: TimerDataPacket) {
        msEventTime = o.msEventTime
        msElapsedTime = o.msElapsedTime
        msLastSystemTime = o.msLastSystemTime
        msStartSystemTime = o.msStartSystemTime
    }


}