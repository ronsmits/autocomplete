package no.tornadofx.view

import no.tornadofx.AutoCompleteTextField
import tornadofx.*

class MainView : View("Hello TornadoFX Application") {
    override val root = hbox {
        label(title) {
            addClass(heading)
        }
        add(AutoCompleteTextField { listOf("AA", "bb", "cc") })
    }
}