import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TrivialPursuitTest {
    //    Enoncé
//
//    Dans le jeu Trivial Pursuit, le parcours est un cercle composé de 48 cases dont
//    la couleur peut-être orange, jaune, vert, rose, bleu ou violet.
//    Les couleurs des cases du parcours s'enchainent dans l'ordre suivant :
//    orange puis jaune puis vert puis rose puis bleu puis violet. Durant le jeu,
//    chaque joueur lance un dé et avance du nombre de cases indiqué par le dé.
//    Le but de ce challenge est de déterminer la couleur de la case d'arrivée après
//    plusieurs lancés de dé. Les parcours étant un cercle, l'ensemble des lancés de dé
//    peut faire effectuer plus d'un tour complet du parcours au joueur.
//
//
//    On suppose qu'au départ le joueur est sur une case dont la couleur est violet.
//
//    Le résultat affiché par votre code doit être la couleur de la case d'arrivée.
//
//
//    Format des données
//
//    Entrée
//    Une série de lignes contenant chacune un chiffre entre 1 et 6.
//
//    Sortie
//    Une couleur parmi orange, jaune, vert, rose, bleu, violet.
    fun solve(diceRolls: Array<Int>): String {
        val colorCase = arrayOf(
            "orange", "jaune", "vert", "rose", "bleu", "violet"
        )
        // la position du joueur sur le plateau qui contient 48 cases
        // On suppose qu'au départ le joueur est sur une case dont la couleur est violet.
        var currentPosition = 6 // orange = 1, ..., violet = 6
        // la somme de tous les dés
        val diceSum = diceRolls.sum()
        // calcul de la position sur le plateau
        val tempPosition = currentPosition + diceSum
//        currentPosition = if (tempPosition > 48) tempPosition % 48 else tempPosition
        // la position varie entre 1 à 48
        // la case 1 est orange
        // on soustrait -1 pour avoir l'index qui commence à zéro dans un tableau
        val colorIndex = (tempPosition % 6) - 1

        return colorCase[colorIndex]
    }

    @Test
    fun `case 1`() {

        val input = arrayOf(
            4,
            1,
            5,
            3,
            2,
            2,
            6,
            2,
            1
        )

        val color = solve(input)

        Assertions.assertEquals("jaune", color)

    }


    @Test
    fun `case 2`() {

        val input = arrayOf(
            6,
            6,
            6,
            5,
            3,
            3,
            2
        )

        val color = solve(input)

        Assertions.assertEquals("orange", color)

    }


    @Test
    fun `case 3`() {

        val input = arrayOf(
            3,
            4,
            5,
            3,
            6,
            4,
            1,
            1,
            3,
            4
        )

        val color = solve(input)

        Assertions.assertEquals("rose", color)

    }
}