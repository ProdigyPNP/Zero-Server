package Zero

import Zero.api.Response

class Main {

    companion object {

        fun main (args: Array<String>) {

            println("\nRunning Zero on http://localhost:${Runner.PORT}")
            Runner.main()
        }

        val xResp : Response = Response(
            PNP_URL.PNP_URL,
            "text/plain",
            "200 OK"
        )


    }

}