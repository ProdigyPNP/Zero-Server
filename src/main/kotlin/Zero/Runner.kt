package Zero

import Zero.Main.Companion.xResp
import Zero.api.*

object Runner {

    private val mappings: Mappings = Mappings()
    private val PORT = 80


    fun main () {


        mappings.addMap("GET", "/", "index.html")



        mappings.addMap("GET", "/domain", object : AbstractResponse() {
            override fun getResponse(req: Request): Response {
                return xResp
            }
        })



        // --------------------------------------------------
        // Server loop
        var server: Server
        while (true) {
            server = Server(PORT, mappings)
            val req = server.accept()
            server.sendResponse(req)
            server.shut()
        }
    }
}
