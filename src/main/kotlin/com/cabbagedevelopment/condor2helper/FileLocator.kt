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
import javax.swing.filechooser.FileSystemView

const val programFiles = "Program Files"

/**
 * A class which allows finding a particular folder in
 * "Program Files" or "Program Files (x86)" for all local
 * disks in Windows.
 */
open class FileLocator(private val lookFor: String) {

    /**
     * Gets all local disks in Windows.
     */
    private fun getDrives(): List<File> {
        return FileSystemView.getFileSystemView().let { fsv ->
            File.listRoots().filter { fsv.getSystemTypeDescription(it) == "Local Disk" }
        }
    }

    /**
     * Locates the folder and returns the absolute path.
     *
     * If the folder is not found, returns null.
     *
     * If there is more than one folder with the same name in
     * different directories, the first folder found
     * will be returned.
     */
    fun locate(): String? {
        return getDrives()
            .subDirectories()
            .filter { it.name.contains(programFiles) }
            .subDirectories()
            .firstOrNull() { it.name == lookFor }?.absolutePath
    }

}

fun List<File>.subDirectories() = flatMap { it.listFiles()?.toList() ?: listOf() }

class CondorLocator : FileLocator("Condor2")
class ReviveLocator : FileLocator("Revive")