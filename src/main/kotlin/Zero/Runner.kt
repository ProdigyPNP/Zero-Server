package Zero

import Zero.Reqs.Companion.increm
import Zero.api.*

object Runner {

    private val mappings: Mappings = Mappings()
    private val PORT = 80


    fun main () {


        mappings.addMap("GET", "/", "index.html")



        mappings.addMap("GET", "/domain", object : AbstractResponse() {
            override fun getResponse(req: Request): Response {
                increm(1)
                return Response(
                    Balancer.NextURL(),
                    "text/plain",
                    "200 OK"
                )
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
