package Zero

import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.lang.Integer.parseInt

/** Reqs stands for requests, not requirements btw. */
class Reqs {

    companion object {


        val Threshold : Int = 15



        var Temp : Int = 0

        fun increm (x : Int) {
            Temp += x

            if (Temp >= Threshold) {
                add(Temp)
                Temp = 0
            }
        }


        fun add (amount : Int) : Unit {

            val file : File = File("reqs.txt")

            val reader : FileReader = FileReader(file)

            val content : String = reader.readText()

            val reqs : Int = Integer.parseInt(content)

            val add : Int = reqs + amount

            val output : String = add.toString()

            val writer : FileWriter = FileWriter(file)

            writer.write(output)
            writer.close()
        }


        fun get () : Int {

            val file : File = File("reqs.txt")

            val reader : FileReader = FileReader(file)

            val content : String = reader.readText()

            val reqs : Int = Integer.parseInt(content)

            val out = reqs + Temp

            return out
        }



    }

}