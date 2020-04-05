/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltaevent.timer

import com.deltarobotics9351.kotlin.asyncExecute
import com.deltarobotics9351.kotlin.deltaevent.Super
import com.deltarobotics9351.kotlin.deltaevent.event.Event
import com.deltarobotics9351.kotlin.deltaevent.event.manager.removeAsyncTimer
import com.deltarobotics9351.kotlin.deltaevent.event.timer.TimerEvent
import com.deltarobotics9351.kotlin.restartAppCausedByError
import com.qualcomm.robotcore.hardware.HardwareMap
import java.util.*
import kotlin.collections.ArrayList

open class SuperSyncTimer : Super{

    private val eventsTime = HashMap<TimerEvent, TimerDataPacket>()

    var destroying = false

    override var events: ArrayList<Event> = ArrayList()

    private var finishedDestroying = false

    val msStuckInDestroy: Long = 1000

    private var hardwareMap: HardwareMap? = null

    constructor (hardwareMap: HardwareMap) {
        this.hardwareMap = hardwareMap
    }

    override fun registerEvent(event: Event): SuperSyncTimer {
        require(event is TimerEvent) { "Event is not TimerEvent" }
        events.add(event)
        eventsTime[event] = TimerDataPacket(0, 0, false)
        return this
    }

    override fun registerEvent(event: Event, repeat: Boolean): SuperSyncTimer {
        require(event is TimerEvent) { "Event is not TimerEvent" }
        events.add(event)
        eventsTime[event] = TimerDataPacket(0, 0, repeat)
        return this
    }

    override fun registerEvent(event: Event, timeSeconds: Int): SuperSyncTimer {
        require(event is TimerEvent) { "Event is not TimerEvent" }
        events.add(event)
        eventsTime[event] = TimerDataPacket((timeSeconds * 1000).toLong(), 0, false)
        return this
    }

    override fun registerEvent(event: Event, timeSeconds: Double): SuperSyncTimer {
        require(event is TimerEvent) { "Event is not TimerEvent" }
        events.add(event)
        eventsTime[event] = TimerDataPacket(timeSeconds.toLong() * 1000, 0, false)
        return this
    }

    override fun registerEvent(event: Event, timeSeconds: Int, repeat: Boolean): SuperSyncTimer {
        require(event is TimerEvent) { "Event is not TimerEvent" }
        events.add(event)
        eventsTime[event] = TimerDataPacket(timeSeconds.toLong() * 1000, 0, repeat)
        return this
    }

    override fun registerEvent(event: Event, timeSeconds: Double, repeat: Boolean): SuperSyncTimer {
        require(event is TimerEvent) { "Event is not TimerEvent" }
        events.add(event)
        eventsTime[event] = TimerDataPacket(timeSeconds.toLong() * 1000, 0, repeat)
        return this
    }

    override fun unregisterEvents() {
        eventsTime.clear()
    }

    /**
     * Update method, which is not needed to call manually because it is called in another thread.
     */
    override fun update() {
        if (destroying) return
        val evtToRemove = ArrayList<TimerEvent>()
        for ((evt, evtTimeDataPacket) in eventsTime) {
            if (evtTimeDataPacket.msLastSystemTime == 0L) {
                evt.startEvent()
                evtTimeDataPacket.msStartSystemTime = System.currentTimeMillis()
                evtTimeDataPacket.msLastSystemTime = System.currentTimeMillis()
            }
            if (evt.isCancelled()) {
                evtToRemove.add(evt)
                continue
            }
            evt.loopEvent(TimerDataPacket(evtTimeDataPacket))
            if (evtTimeDataPacket.msEventTime < 1) {
                evt.timeoutEvent()
                if (!evtTimeDataPacket.repeat) {
                    evtToRemove.add(evt)
                } else {
                    evtTimeDataPacket.msStartSystemTime = 0
                    evtTimeDataPacket.msElapsedTime = 0
                    evtTimeDataPacket.msLastSystemTime = 0
                }
            } else {
                val msElapsed = System.currentTimeMillis() - evtTimeDataPacket.msStartSystemTime
                evtTimeDataPacket.msElapsedTime = msElapsed
                evtTimeDataPacket.msLastSystemTime = System.currentTimeMillis()
                if (evtTimeDataPacket.msEventTime >= evtTimeDataPacket.msElapsedTime) {
                    evt.timeoutEvent()
                    if (!evtTimeDataPacket.repeat) {
                        evtToRemove.add(evt)
                    } else {
                        evtTimeDataPacket.msStartSystemTime = 0
                        evtTimeDataPacket.msElapsedTime = 0
                        evtTimeDataPacket.msLastSystemTime = 0
                    }
                }
            }
            eventsTime.remove(evt)
            eventsTime[evt] = evtTimeDataPacket
        }
        for (evt in evtToRemove) {
            eventsTime.remove(evt)
        }
    }

    /**
     * Destroy this SuperSyncTimer synchronously.
     * IT NEEDS TO BE CALLED AT THE END OF YOUR OPMODE
     */
    fun destroy() {
        destroying = true
        asyncExecute() {
            val msStartDestroying = System.currentTimeMillis()
            val msMaxTimeDestroying = msStartDestroying + msStuckInDestroy
            while (!Thread.interrupted() && System.currentTimeMillis() < msMaxTimeDestroying);
            if (!finishedDestroying) {
                restartAppCausedByError(hardwareMap!!, "User SuperTimer stuck in destroy(). Restarting robot controller app.", "SuperTimer stuck in destroy(). Restarting robot controller app.")
            }
        }
        if (this is SuperAsyncTimer) removeAsyncTimer(this as SuperAsyncTimer)
        cancelAllEvents()
    }


    fun cancelAllEvents() {
        val safeEvents = events.toTypedArray() as Array<Event>
        for (evt in safeEvents) {
            val e = evt as TimerEvent
            e.cancel()
        }
        if (destroying) finishedDestroying = true
    }

    fun hasFinishedDestroying(): Boolean {
        return finishedDestroying
    }


}