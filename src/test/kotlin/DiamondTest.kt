import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DiamondTest {
//    Objectif
//
//    En attendant la neige, on peut toujours dessiner des flocons.... Le but de ce challenge est de dessiner des flocons.
//
//    Pour dessiner un flocon de taille N (où N est un entier impair), vous devez créer un losange de dimension N dans
//    une feuille comprenant N lignes et N colonnes. Chaque point non compris dans le losange est représenté par un .
//    et chaque point du losange est représenté par un *.
//
//    Exemples
//    Si N = 3, le losange sera ainsi :
//    .*.
//    ***
//    .*.
//
//    Si N =7, le losange sera ainsi :
//    ...*...
//    ..***..
//    .*****.
//    *******
//    .*****.
//    ..***..
//    ...*…
//
//
//    Données
//
//    Entrée
//    Ligne 1 : un entier impair N compris entre 3 et 51 représentant la taille du flocon.
//
//    Sortie
//    N lignes de N caractères représentant le flocon. Chaque caractère pouvant être un . ou un *.
//    Si vous rencontrez des problèmes avec les sauts de ligne dans votre sortie, vous pouvez aussi renvoyer
//    une ligne unique comprenant toutes les lignes du dessin, en séparant chaque ligne du dessin par un espace.
//    Par exemple si N=3 votre sortie serait :
//    .*. *** .*.

    @ExperimentalStdlibApi
    fun drawDiamond(n: Int): String {
        var diamond = ""
        val dot = "."
        val star = "*"
        val nbStarsByRow = mutableListOf<Int>()

        for (row in 0 until n) {
            val tempNbStars = 1 + 2 * row
            var nbStars = if (tempNbStars < n) {
                nbStarsByRow.add(tempNbStars)
                tempNbStars
            } else if (tempNbStars == n) {
                n
            } else {
                nbStarsByRow.removeLast()
            }

            val nbDot: Int = (n - nbStars) / 2

            diamond += dot.repeat(nbDot) + star.repeat(nbStars) + dot.repeat(nbDot)


            if (row != n - 1) diamond += " "

        }

        return diamond
    }

    @Test
    @ExperimentalStdlibApi
    fun `case with N=3`() {
        val diamond = drawDiamond(n = 3)
        Assertions.assertEquals(
            ".*. *** .*.",
            diamond
        )
    }

    @Test
    @ExperimentalStdlibApi
    fun `case with N=7`() {
        val diamond = drawDiamond(n = 7)
        Assertions.assertEquals(
            "...*... ..***.. .*****. ******* .*****. ..***.. ...*...",
            diamond
        )
    }
}