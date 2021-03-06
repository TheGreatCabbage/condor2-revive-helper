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

import java.io.File

/**
 * A class which handles launching Condor2 via Revive.
 */
class CondorLauncher(val prefs: Prefs, var argument: String) {

    // If this is supplied as an argument, Condor2 will launch via Revive in single-player mode.
    val launchOnly = "--launch"

    fun launch() {
        if (argument == launchOnly) {
            argument = ""
        }
        // Launch Condor2 via Revive with selected argument, setting Revive directory as working directory.
        ProcessBuilder(prefs.reviveInjectorPath, "\"${prefs.condorPath}\\Condor.exe\" $argument").apply {
            prefs.revivePath?.let { directory(File(it)) }
            start()
        }
    }
}