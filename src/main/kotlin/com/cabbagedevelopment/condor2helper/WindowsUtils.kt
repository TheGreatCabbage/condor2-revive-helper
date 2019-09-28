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

import mslinks.ShellLink
import java.awt.Desktop
import java.io.File
import java.net.URI
import javax.swing.filechooser.FileSystemView

/**
 * Opens the default web browser with a given URL.
 */
fun browseTo(url: String) {
    if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().browse(URI(url))
    }
}

/**
 * Creates a desktop shortcut that will launch Condor2 via Revive
 * in single-player mode.
 */
fun createDesktopShortcut(prefs: Prefs, onSuccess: () -> Unit) {
    val targetDir = File(".").absolutePath
    val iconPath = "${prefs.condorPath}\\Condor.exe"
    val desktop = getDesktopPath()
    val shortcutName = "Condor 2 VR.lnk"

    val target = "$targetDir\\Condor2Helper.exe"
    ShellLink.createLink(target).apply {
        iconLocation = iconPath
        cmdArgs = "--launch"
        saveTo("$desktop\\$shortcutName")
    }

    // Check if shortcut was created.
    File(desktop).listFiles()
        ?.firstOrNull { it.name == shortcutName }
        ?.let { onSuccess() }
}

/**
 * Returns the path to the Desktop folder.
 */
fun getDesktopPath(): String {
    return FileSystemView.getFileSystemView().homeDirectory.path
}