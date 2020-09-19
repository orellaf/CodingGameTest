import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MapTest {
//    Objectif
//
//    En montagne, on utilise des cartes topographiques qui contiennent des courbes de niveau.
//    Tous les points d'une courbe de niveau donnée sont à la même altitude. Par conséquent,
//    si une courbe de niveau en croise une autre cela constitue une anomalie (sinon le point
//    de croisement aurait deux altitudes différentes). Dans ce challenge, vous allez devoir
//    déterminer si une carte contient ou non une anomalie.
//
//    Pour simplifier le problème, on va considérer que les courbes de niveau sont des
//    cercles dont on vous fournit les coordonnées du centre ainsi que le rayon. Toutes
//    les courbes de niveau que l'on vous fournit représentent des altitudes différentes
//    donc si deux lignes se touchent, c'est aussi une anomalie.
//
//    Petit rappel mathématique, la distance entre 2 points de coordonnées
//    (x1,y1) et (x2,y2) est donnée par :
//
//
//
//    Données
//
//    Entrée
//    Ligne 1 : un entier N compris entre 2 et 1500 représentant le nombre de cercles.
//    Lignes 2 à N + 1 : trois entiers X, Y, et R séparés par des espaces représentant
//    respectivement l'abscisse(X) et l'ordonnée(Y) du centre et le rayon(R) d'un cercle.
//


//    Sortie
//    La chaîne OK si la carte ne contient pas d'anomalie sinon la chaîne KO.

//    3
//    10 10 20
//    5 5 1
//    40 40 1
//      => OK

    data class Coordinate(val x: Double, val y: Double, val radius: Double)

    fun createCoordinate(data: String): Coordinate {
        val data = data.split(" ")

        return Coordinate(
            x = data[0].toDouble(),
            y = data[1].toDouble(),
            radius = data[2].toDouble()
        )
    }

    fun checkIfConform(coordinates: Array<String>): String {
        var result = "OK"
        firstPoint@ for ((i, coordinate1) in coordinates.withIndex()) {
            val (x1, y1, radius1) = createCoordinate(coordinate1)

            secondPoint@ for ((j, coordinate2) in coordinates.withIndex()) {
                if (i == j) continue@secondPoint

                val (x2, y2, radius2) = createCoordinate(coordinate2)
                val distance = Math.sqrt(Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0))
                if (distance <= radius1 + radius2) result = "KO"; break@firstPoint

            }
        }



        return result
    }

    @Test
    fun `case 1, OK`() {
        val conform = checkIfConform(
            arrayOf(
                "10 10 20",
                "5 5 1",
                "40 40 1"
            )
        )
        Assertions.assertEquals(
            "OK",
            conform
        )
    }

    @Test
    fun `case 2, KO`() {
        val conform = checkIfConform(
            arrayOf(
                "10 10 20",
                "5 5 1",
                "6 6 1"
            )
        )
        Assertions.assertEquals(
            "KO",
            conform
        )
    }

    @Test
    fun `case 3, OK`() {
        val conform = checkIfConform(
            arrayOf(
                "6 6 5",
                "8 3 1",
                "4 6 2",
                "2 1 1",
                "7 8 1",
                "1 10 1",
                "4 6 1",
                "7 5 1",
                "9 7 1",
                "5 9 1"
            )
        )
        Assertions.assertEquals(
            "OK",
            conform
        )
    }

    @Test
    fun `case 4, KO`() {
        val conform = checkIfConform(
            arrayOf(
                "5 4 5",
                "3 5 1",
                "7 1 2",
                "5 1 2",
                "8 10 3",
                "7 6 4",
                "1 4 5",
                "6 10 3",
                "6 8 3",
                "4 7 2",
                "4 2 4",
                "4 4 1",
                "4 1 2",
                "8 5 2",
                "1 2 1",
                "7 9 4",
                "5 10 1",
                "4 5 5",
                "9 1 4",
                "5 4 2",
                "8 8 3",
                "5 2 5",
                "7 6 5",
                "9 3 2",
                "2 4 3",
                "3 1 3",
                "10 5 2",
                "10 8 5",
                "10 6 5",
                "8 10 2",
                "1 8 1",
                "6 2 1",
                "5 9 4",
                "4 7 5",
                "8 8 2",
                "4 1 2",
                "8 1 4",
                "2 1 2",
                "10 1 5",
                "9 8 5",
                "2 8 4",
                "2 4 5",
                "4 8 4",
                "10 1 3",
                "9 8 2",
                "2 2 2",
                "4 9 2",
                "1 10 3",
                "5 10 3",
                "4 9 1",
                "4 1 5",
                "1 2 2",
                "10 6 5",
                "7 5 1",
                "1 4 5",
                "3 6 5",
                "6 10 4",
                "10 10 4",
                "4 5 4",
                "9 8 4",
                "10 2 4",
                "10 2 4",
                "2 2 2",
                "2 8 5",
                "3 9 1",
                "1 2 4",
                "10 8 4",
                "7 8 4",
                "5 1 1",
                "2 10 5",
                "8 9 5",
                "4 9 1",
                "2 1 2",
                "5 2 1",
                "4 4 5",
                "6 4 1",
                "3 4 5",
                "9 10 4",
                "6 5 4",
                "6 6 4",
                "5 4 3",
                "5 8 2",
                "7 10 2",
                "10 5 3",
                "1 8 5",
                "1 3 2",
                "2 5 4",
                "1 4 4",
                "8 10 1",
                "5 5 4",
                "1 10 1",
                "7 4 4",
                "10 10 4",
                "4 10 1",
                "9 10 5",
                "9 10 1",
                "2 2 3",
                "9 3 5",
                "5 10 5",
                "6 4 2"
            )
        )
        Assertions.assertEquals(
            "KO",
            conform
        )
    }
}