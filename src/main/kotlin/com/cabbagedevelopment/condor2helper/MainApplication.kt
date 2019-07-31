package  com.cabbagedevelopment.condor2helper

import javafx.scene.Parent
import tornadofx.*
import java.io.File

class MainApplication : App(MainView::class) {

}

class MainView : View() {
    override val root: Parent = vbox {
        label("Testing")
    }
}

/**
 * Entry point of the application.
 */
fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        CondorLauncher(args.first()).launch()
    } else {
        launch<MainApplication>(args)
    }
}