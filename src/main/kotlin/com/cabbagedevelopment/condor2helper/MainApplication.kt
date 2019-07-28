package  com.cabbagedevelopment.condor2helper

import javafx.scene.Parent
import tornadofx.*

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
        CondorLauncher(args[0]).launch()
    } else {
        launch<MainApplication>(args)
    }
}