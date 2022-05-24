package Zero.api

import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException

class Mappings {
    var urlMappings: HashMap<String, AbstractResponse>

    init {
        urlMappings = HashMap()
    }

    fun getMap(key: String): AbstractResponse? {
        return urlMappings[key]
    }

    fun addMap(method: String, url: String, resp: AbstractResponse) {
        urlMappings[method + "_" + url] = resp
    }

    @Throws(IOException::class)
    fun addMap(method: String, url: String, filepath: String?) {
        urlMappings[method + "_" + url] = object : AbstractResponse() {
            override fun getResponse(req: Request): Response {
                var res = ""
                try {
                    val fr = FileReader(filepath)
                    var c: Int
                    c = fr.read()
                    while (c != -1) {
                        res += c.toChar()
                        c = fr.read()
                    }
                } catch (fnfe: FileNotFoundException) {
                    return Response(
                        "<html><body>Unable to find resource [$url]</body></html>",
                        "text/html",
                        "404 Not Found"
                    )
                } catch (ioe: IOException) {
                    return Response(
                        "<html><body>Unable to read resource [$url]</body></html>",
                        "text/html",
                        "404 Not Found"
                    )
                }
                res = replaceRequestAttribute(res, req)
                return Response(res, "text/html", "200 OK")
            }
        }
    }

    private fun replaceRequestAttribute(res: String, req: Request): String {
        var res = res
        val itr = req.attributeIterator
        while (itr!!.hasNext()) {
            val key = itr.next() as String
            val `val` = req.getAttribute(key)
            res = res.replace("\${$key}", `val`!!)
        }
        return res
    }

    private fun indexOfAfter(str: String, toSearch: String, after: Int): Int {
        var str = str
        str = str.substring(after)
        return after + str.indexOf(toSearch)
    }
}