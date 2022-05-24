package Zero.api

import Zero.Main.Companion.xResp
import java.io.IOException
import java.io.InputStream
import java.net.ServerSocket
import java.net.Socket


class Server(private val PORT: Int, mappings: Mappings) {
    private val server: ServerSocket
    private lateinit var client: Socket
    private val mappings: Mappings

    init {
        server = ServerSocket(PORT)
        this.mappings = mappings
    }

    @Throws(IOException::class)
    fun accept(): Request {
        client = server.accept()
        val `is` : InputStream = client.getInputStream()
        var c: Int
        var raw = ""
        do {
            c = `is`.read()
            raw += c.toChar()
        } while (`is`.available() > 0)
        return Request(raw)
    }

    @Throws(IOException::class)
    fun shut() {
        server.close()
    }

    private fun getResponse(req: Request): Response? {
        val respAbs = mappings.getMap(req.method + "_" + req.url)
            ?: return xResp
        return respAbs.getResponse(req)
    }

    @Throws(IOException::class)
    fun sendResponse(req: Request) {
        val resp = getResponse(req)
        val out = client!!.getOutputStream()
        try {
            out.write(resp.toString().toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}