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

import javafx.application.Platform
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.*
import kotlin.system.exitProcess

/**
 * The main view which is shown when configuring the program.
 */
class MainView : View() {

    lateinit var condorText: TextField
    lateinit var reviveText: TextField
    lateinit var saveButton: Button

    val application by lazy { FX.application as MainApplication }

    override val root: Parent = vbox {

        alignment = Pos.CENTER
        spacing = 15.0

        paddingTop = 20
        paddingBottom = 40
        paddingLeft = 20
        paddingRight = 20

        row {
            label("Condor2 install location:")
            condorText = textfield()
        }
        row {
            label("Revive install location:")
            reviveText = textfield()
        }
        row {
            label("Install browser extension:")
            button("Firefox") {
                action {
                    browseTo("https://addons.mozilla.org/en-GB/firefox/addon/condor2extension/")
                }
            }
            button("Chrome") {
                action { browseTo("https://chrome.google.com/webstore/detail/condor2-extension/joipnfhikghfdcpkngglhkjbjhkjnlhb") }
            }
        }
        row {
            label("Create desktop shortcut to launch Condor in single-player mode via Revive:")
            button("Create shortcut") {
                action {
                    createDesktopShortcut(Prefs()) {
                        text = "Success!"
                    }
                }
            }
        }
        row {
            saveButton = button("Save and exit") {
                action {
                    application.save()
                    Platform.exit()
                    exitProcess(0)
                }
            }
        }
    }
}

inline fun VBox.row(crossinline func: HBox.() -> Unit): HBox {
    return hbox {
        alignment = Pos.CENTER
        spacing = 15.0
        padding = Insets(10.0, 20.0, 0.0, 20.0)
        func()
    }
}