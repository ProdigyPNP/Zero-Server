package Zero

import Zero.Main.Companion.xResp
import Zero.api.*

object RunnerS {

    private var mappings: Mappings? = Mappings()
    private val PORT = 443


    fun main () {


        mappings!!.addMap("GET", "/", object : AbstractResponse() {
            override fun getResponse(req: Request): Response {
                return xResp
            }
        })



        // --------------------------------------------------
        // Server loop
        var server: Server
        while (true) {
            server = Server(PORT, mappings!!)
            val req = server.accept()
            server.sendResponse(req)
            server.shut()
        }
    }
}
