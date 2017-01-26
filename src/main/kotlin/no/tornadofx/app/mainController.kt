package no.tornadofx.app

import tornadofx.Controller
import javax.json.JsonArray

/**
 * Created by ronsmi on 1/26/2017.
 */

class mainController : Controller(){
    val array : JsonArray = resources.jsonArray("/countries.json")
}
