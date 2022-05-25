package Zero

import java.util.*

object Balancer {

    var previousURL : String = "";

    fun NextURL () : String {


        if (PNP_URL.PNP_URL != "") {
            return PNP_URL.PNP_URL
        }




        var toReturn : String = ""

        val Length : Int = PNP_URL.PNP_URLS.size;


        do {

            val random : Int = Random().nextInt(0, Length)
            toReturn = PNP_URL.PNP_URLS.get(random)
            println("Random: $random, URL: $toReturn, Matches previous URL: ${toReturn == previousURL}")


        } while (toReturn == previousURL)


        previousURL = toReturn

        return previousURL
    }


}