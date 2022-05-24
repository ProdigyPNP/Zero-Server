package Zero

import Zero.api.Response

class Main {

    companion object {

        fun main (args: Array<String>) {

            println("\nRunning Zero on http://localhost:80 and http://localhost:443\n")

            val HTTP : Thread = Thread {
                Runner.main()
            }

            val HTTPS : Thread = Thread {
                RunnerS.main()
            }


            HTTP.start()
            HTTPS.start()

        }

        val xResp : Response = Response(
            PNP_URL.PNP_URL,
            "text/plain",
            "200 OK"
        )


    }

}