package Zero.api

class Request(req: String?) {
    private var req: String? = null
    var method: String? = null
        private set
    var url: String? = null
        private set
    var httpVersion: String? = null
        private set
    private val attributes: HashMap<String, String>

    init {
        this.req = req
        attributes = HashMap()
        parse()
    }

    private fun parse() {
        val temp = req!!.split("\r\n".toRegex()).toTypedArray()
        val firstLine = temp[0]
        val firstLineSplit = firstLine.split(" ".toRegex()).toTypedArray()
        if (firstLineSplit.size == 3) {
            method = firstLineSplit[0]
            httpVersion = firstLineSplit[2]
            if (method == "POST") {
                url = firstLineSplit[1]
                setAttributes(temp[temp.size - 1])
            } else if (method == "GET") {
                val arr = firstLineSplit[1].split("[?]".toRegex()).toTypedArray()
                if (arr.size == 2) {
                    url = arr[0]
                    setAttributes(arr[1])
                } else {
                    url = firstLineSplit[1]
                }
            } else {
                url = firstLineSplit[1]
            }
        }
    }

    val attributeIterator: Iterator<*>
        get() = attributes.keys.iterator()

    private fun setAttributes(rawAttributes: String) {
        val attribs = rawAttributes.split("&".toRegex()).toTypedArray()
        for (i in attribs.indices) {
            val attr = attribs[i].split("=".toRegex()).toTypedArray()
            if (attr.size == 2) {
                setAttribute(attr[0], attr[1].replace("+", " "))
            }
        }
    }

    fun getAttribute(key: String): String {
        return attributes[key] ?: return "null"
    }

    fun setAttribute(key: String, value: String) {
        attributes[key] = value
    }

    override fun toString(): String {
        return req!!
    }
}