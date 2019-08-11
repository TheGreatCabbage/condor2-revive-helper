/*
 * Condor2 Revive Helper, a program which allows Condor2 to be launched more easily via Revive.
 * Copyright (C) 2019 Sam McCormack
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.cabbagedevelopment.condor2helper

import java.util.prefs.Preferences
import kotlin.reflect.KProperty

/**
 * A preferences class to allow persistent storage of data.
 */
class Prefs {

    private val prefs = Preferences.userRoot().node(javaClass.name)

    var revivePath: String?  by StringProperty("revive_path")
    var reviveInjectorPath: String?  by StringProperty("revive_injector_path")
    var condorPath: String? by StringProperty("condor2_path")

    /**
     * A property delegate used to improve the syntax of saving and recalling values.
     */
    inner class StringProperty(private val key: String, private val default: String? = null) {

        operator fun getValue(thisRef: Any?, property: KProperty<*>): String? {
            return prefs.get(key, default)
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
            prefs.put(key, value)
        }
    }
}

