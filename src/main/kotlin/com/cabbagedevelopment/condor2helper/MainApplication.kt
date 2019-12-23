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

package  com.cabbagedevelopment.condor2helper

import tornadofx.*


/**
 * Entry point of the application.
 */
fun main(args: Array<String>) {
    // If program run with args, launch Condor2.
    if (args.isNotEmpty()) {
        return CondorLauncher(Prefs(), args.first()).launch()
    }
    // If program run normally, open window to configure settings.
    launch<MainApplication>(args)
}

/**
 * The main application, which is used to configure the program.
 * This class is not used when simply launching Condor2.
 */
class MainApplication : App(MainView::class) {

    val injectorName = "ReviveInjector.exe"
    val prefs = Prefs()
    val view = find<MainView>()

    init {
        // If the required paths are already stored in the preferences,
        // set the text fields to those values. Otherwise, find the
        // required paths.
        prefs.condorPath?.also { view.condorText.text = it } ?: findCondor()
        prefs.revivePath?.also { view.reviveText.text = it } ?: findRevive()
    }

    /**
     * Uses a FileLocator instance to locate a file. When a file path is returned,
     * onResult is called.
     */
    private inline fun findWith(locator: FileLocator, crossinline onResult: (String) -> Unit) {
        runAsync {
            locator.locate()
        } ui { path ->
            path?.let { onResult(it) }
        }
    }

    private fun findCondor() {
        findWith(CondorLocator()) { path ->
            prefs.condorPath = path
            view.condorText.text = path
        }
    }

    private fun findRevive() {
        findWith(ReviveLocator()) { path ->
            // We need the path to the Revive folder inside the Revive installation directory.
            val revivePath = "$path\\Revive"
            prefs.revivePath = revivePath
            prefs.reviveInjectorPath = "$revivePath\\$injectorName"
            view.reviveText.text = revivePath
        }
    }

    /**
     * Saves the contents of the text fields to the preferences.
     */
    fun save() {
        prefs.condorPath = view.condorText.text
        prefs.revivePath = view.reviveText.text
        prefs.reviveInjectorPath = "${prefs.revivePath}\\x64\\$injectorName"
    }
}