/*
 * Created by FTC team Delta Robotics #9351
 *  Source code licensed under the MIT License
 *  More info at https://choosealicense.com/licenses/mit/
 */

package com.deltarobotics9351.kotlin.deltaevent

import com.deltarobotics9351.kotlin.deltaevent.event.Event

interface Super {

    var events : ArrayList<Event>

    /**
     * Register an event
     * @param event the Event to register
     */
    fun registerEvent(event: Event): Super

    /**
     * Register an event with an int parameter
     * @param event the Event to register
     */
    fun registerEvent(event: Event, parameterInt1: Int): Super

    /**
     * Register an event with a double parameter
     * @param event the Event to register
     */
    fun registerEvent(event: Event, parameterDouble1: Double): Super

    /**
     * Register an event with a boolean parameter
     * @param event The event to register
     * @return itself
     */
    fun registerEvent(event: Event, parameterBoolean1: Boolean): Super

    /**
     * Register an event with int & boolean parameters
     * @param event the Event to register
     */
    fun registerEvent(event: Event, parameterInt1: Int, parameterBoolean1: Boolean): Super

    /**
     * Register an event with double & boolean parameters
     * @param event the Event to register
     */
    fun registerEvent(event: Event, parameterDouble1: Double, parameterBoolean1: Boolean): Super

    /**
     * Unregister all the events
     */
    fun unregisterEvents()

    /**
     * Update the pressed buttons and execute all the events.
     * This method should be placed at the end or at the start of your repeat in your OpMode
     */
    fun update()


}