/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltaevent.event.manager

import com.deltarobotics9351.kotlin.asyncExecute
import com.deltarobotics9351.kotlin.deltaevent.timer.SuperAsyncTimer
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar
import java.util.*

    var asyncTimersManager: Thread? = null

    var asyncTimers = ArrayList<SuperAsyncTimer>()

    var alreadyInitialized = false

    @OpModeRegistrar //annotation for the FTC SDK to execute this method every time the robot initializes
    fun initialize(manager: OpModeManager?) {
        removeAsyncTimers()
        if (!asyncTimersManager!!.isAlive) asyncTimersManager!!.start()
    }

    fun removeAsyncTimers() {
        for (timer in asyncTimers) {
            asyncExecute { timer.destroy() }
            asyncTimers.remove(timer)
        }
    }

    fun addAsyncTimer(timer: SuperAsyncTimer) {
        if (!asyncTimers.contains(timer)) {
            asyncTimers.add(timer)
        }
    }

    fun removeAsyncTimer(timer: SuperAsyncTimer) {
        if (asyncTimers.contains(timer)) {
            asyncExecute { timer.destroy() }
            asyncTimers.remove(timer)
        }
    }

    private class AsyncTimersManagerRunnable : Runnable {
        override fun run() {
            while (!Thread.interrupted()) {
                val safeAsyncTimers = asyncTimers.toTypedArray() as Array<SuperAsyncTimer>
                for (asyncTimer in safeAsyncTimers) {
                    asyncExecute { asyncTimer.update() }
                }
            }
        }
    }