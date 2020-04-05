/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltaevent.event.timer

import com.deltarobotics9351.kotlin.deltaevent.timer.TimerDataPacket
import com.deltarobotics9351.kotlin.deltaevent.event.Event
import java.util.*

class TimerEvent : Event {

    private var cancelled = false

    override fun execute(arg1: Any, arg2: Any) {
        throw UnsupportedOperationException("This method is not supported in TimerEvent")
    }

    override fun execute(arg1: Any) {
        throw UnsupportedOperationException("This method is not supported in TimerEvent")
    }

    override fun execute(args: ArrayList<Any>) {
        throw UnsupportedOperationException("This method is not supported in TimerEvent")
    }

    override fun execute(args: HashMap<Any, Any>) {
        throw UnsupportedOperationException("This method is not supported in TimerEvent")
    }

    open fun cancel() {
        cancelled = true
        cancelEvent()
    }

    open fun isCancelled(): Boolean {
        return cancelled
    }

    open fun startEvent() {}

    open fun timeoutEvent() {}

    open fun cancelEvent() {}

    open fun loopEvent(evtTime: TimerDataPacket) {}


}