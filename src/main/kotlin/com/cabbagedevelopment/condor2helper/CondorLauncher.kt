package com.cabbagedevelopment.condor2helper

import java.io.File

class CondorLauncher(val argument: String) {

    val revive = "C:\\Program Files\\Revive\\Revive\\ReviveInjector_x64.exe"
    val condor = "E:\\Program Files\\Condor2\\Condor.exe"
    val dir = "C:\\Program Files\\Revive\\Revive\\"

    fun launch() {
        val proc = ProcessBuilder(revive, "\"$condor $argument\"")
        proc.directory(File(dir))
        proc.start()
    }
}