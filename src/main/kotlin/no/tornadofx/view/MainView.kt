package no.tornadofx.view

import no.tornadofx.AutoCompleteTextField
import no.tornadofx.app.mainController
import tornadofx.*
import javax.json.JsonObject

class MainView : View("Hello TornadoFX Application") {
    val controller : mainController by inject()
    override val root = hbox {
        label(title) {
            addClass(heading)
        }
        add(AutoCompleteTextField {enteredText->
            controller.array
                    .map { it as JsonObject }
                    .filter { x -> x.getString("name").startsWith(enteredText, true) }
                    .map { it.getString("name") }
        })
    }
}
