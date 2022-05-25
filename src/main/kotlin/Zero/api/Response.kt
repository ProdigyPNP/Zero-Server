package Zero.api

import java.util.*


class Response(resp: String, contentType: String, code: String) {
    private var resp: String? = null

    init {
        val date = Date()
        val start = "HTTP/1.1 $code\r\n" // 200 OK
        var header = "Date: $date\r\n"
        header += "Cache-Control: no-cache\r\n" // text/html
        header += "Content-Type: $contentType\r\n" // text/html
        header += """
            Content-length: ${resp.length}
            
            """.trimIndent()
        header += "\r\n"
        this.resp = start + header + resp
    }

    override fun toString(): String {
        return resp!!
    }
}