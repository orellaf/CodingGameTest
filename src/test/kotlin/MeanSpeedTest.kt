import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MeanSpeedTest {

    private fun convertRawDistanceInMeter(rawDistance: String): Double =
        rawDistance.toDouble() / 1_000

    private fun convertRawTimeInHour(rawTime: List<String>): Double =
        rawTime[0].toDouble() + rawTime[1].toDouble() / 60 + rawTime[2].toDouble() / 3600

    private fun computeDistance(x1: Double, x2: Double, y1: Double, y2: Double): Double =
        Math.sqrt(Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0))

    private fun computeSpeed(distance: Double, time: Double) = distance / time

//    Enoncé
//
//    Dans ce challenge, vous devez calculer la vitesse moyenne d'un véhicule.
//
//    À partir d’un relevé topographique de coordonnées X, Y, t ( avec X et Y
//    en mètres et t un instant exprimé sous forme d'un timestamp au format HH:MM:SS),
//    on cherche à estimer la vitesse moyenne du véhicule.
//
//    Le programme doit lire sur l’entrée standard une séquence de relevés — un par
//    ligne — composés de deux nombres entiers (les coordonnées X et Y exprimées en mètres)
//    et d’un timestamp au format HH:MM:SS. Vous devez calculer et écrire sur la sortie standard
//    la vitesse moyenne en kilomètres/heure avec deux chiffres après la virgule arrondie au
//    centième le plus proche.
//
//    On rappelle que la distance entre deux points est donnée par la formule :
//
//
//    Données
//
//    Entrée
//    Une série de lignes contenant chacune un relevé de position et de temps représenté
//    par deux entiers et un timestamp au format HH:MM:SS. Les deux entiers et le timestamp
//    sont séparés par des espaces. Les relevés apparaissent par ordre chronologique (du premier au dernier).
//
//
//    Sortie
//    Un nombre décimal avec deux chiffres après la virgule représentant la vitesse moyenne.
    fun solve(speeds: Array<String>): Double {
        var speed = 0.0
        val firstData = speeds[0].split(" ")
        var x1 = convertRawDistanceInMeter(firstData[0])
        var y1 = convertRawDistanceInMeter(firstData[1])
        val time = firstData[2].split(":")
        var timeInHour1 = convertRawTimeInHour(time)

        for (index in 1 until speeds.size) {
            val data = speeds[index].split(" ")
            val x2 = convertRawDistanceInMeter(data[0])
            val y2 = convertRawDistanceInMeter(data[1])
            val time = data[2].split(":")


            val timeInHour = convertRawTimeInHour(time)

            val distance = computeDistance(x1, x2, y1, y2)

            println("x1= $x1 km, y1= $y1 km, time1= $timeInHour1 heures")
            println("x2= $x2 km, y2= $y2 km, time2= $timeInHour heures")
            println("distance= $distance km")

            speed += computeSpeed(distance, (timeInHour - timeInHour1))
            println("speed= $speed km/h")

            x1 = x2
            y1 = y2
            timeInHour1 = timeInHour


        }

        return speed / (speeds.size - 1)
    }


    @Test
    fun `case 1`() {
        val speed = solve(
            arrayOf(
                "0 500 07:28:56",
                "0 6500 07:34:56",
                "0 7500 08:28:56"
            )
        )

        Assertions.assertEquals(
            7.0
            , speed
        )

    }


}