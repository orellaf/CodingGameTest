import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MonopolyTest {
//    Enoncé
//
//    Dans ce challenge, l'objectif est de déterminer le moment où un joueur donné est ruiné au jeu du Monopoly.
//    On va supposer que le joueur ne fait que perdre de l'argent et qu'il ne reçoit rien lorsqu'il passe par la case départ. Le plateau est composé de 40 cases numérotées de 1 à 40. La somme dont le candidat dispose à l'origine vous sera fournie dans les données en entrée. Le montant positif ou nul que le joueur paye lorsqu'il arrive sur une case donnée vous sera aussi indiqué dans les données en entrée.
//    Pour se déplacer le candidat jette une paire de dés et se déplace de la somme des chiffres indiqués par les dés.
//    Par ailleurs, la case numéro 20 est la case Allez en prison. Si le joueur tombe sur la case 20,
//    il retourne à la case 10. Pour simplifier, on va supposer que le joueur peut sortir de prison immédiatement
//    (dans un vrai Monopoly, il y a des conditions pour sortir de prison).
//
//    Vous devez déterminer à quel moment le candidat n'a plus d'argent, c'est à dire le moment où le candidat
//    n'a pas assez d'argent pour payer le montant correspondant à la case sur laquelle il arrive.
//    Votre code renverra le numéro de la case sur laquelle il se trouve lorsque ce moment est atteint.
//
//    Le joueur débute sur la case 1. Les fichiers de données seront tels que ce moment sera toujours atteint.
//
//
//    Format des données
//
//    Entrée
//    Ligne 1 : un entier représentant la somme dont dispose le candidat entre 10 000 et 200 000.
//    Lignes 2  : 40 nombres séparés par des espaces indiquant le montant que doit payer le candidat s'il arrive sur les cases 1 à 40. Le montant est compris entre 0 et 4000
//    Ligne 3 : une série contenant un nombre pair de chiffres entre 1 et 6 séparés par des espaces. Ces chiffres représentent les lancés de dé du joueur. Le joueur lance 2 dés à chaque tour.
//
//
//    Sortie
//    Un nombre entre 1 et 40 indiquant le numéro de la case où le candidat n'a plus d'argent.
//

//    20000
//    0 1700 3600 1800 2100 1100 2200 2500 2500 0 2900 2100 2100 2800 3100 1500 2900 2100 3200 0 3200 1100 1100 3200 2500 3700 3500 1300 3900 2700 3300 1100 3400 2900 1900 1400 3000 3200 3000 1400
//    3 2 4 6 6 2 1 3 4 5 2 3 5 3 1 2 2 6 3 2 3 2 2 2 5 4 3 3 2 1 4 5 2 2 4 1 3 5 4 1 3 6 3 2 2 3 4 4 3 1


    fun solve(playerAmount: Int, priceByCaseString: String, gestureString: String): Int {
        val priceByCase = hashMapOf<Int, Int>()
        for ((index, char) in priceByCaseString.split(" ").withIndex()) {
            priceByCase.put(index + 1, char.toInt())
        }
        // contient les lancés des deux dés
        val gestureArray = gestureString.split(" ").map { it.toInt() }
        // l'argent qui reste au joueur
        var currentAmount = playerAmount
        // sur quelle case de monopoly est le joueur, au départ sur la case 1
        var currentPosition = 1

        // on parcourt les lancés des dés
        for (index in 0 until gestureArray.size step 2) {
            // somme des deux dés
            val count = gestureArray[index] + gestureArray[index + 1]
            println("-----------")
            println("old position= $currentPosition")
            println("old money= $currentAmount")
            println("dice= +$count")
            // la case ou se trouve le joueur + le résultat des deux dés
            var tempPosition = currentPosition + count
            // il y a 40 cases sur le plateau de jeu monopoly,
            // donc si le joueur est sur la case 38 et la somme des dés vaut 8 alors le joueur ira sur la case 6
            if (tempPosition > 40) tempPosition %= 40
            // on prélève au joueur le montant de la case d'arrivée
            currentAmount -= priceByCase.get(tempPosition)!!

            // on met à jour la position du joueur,
            // si le joueur tombe sur la case 20 alors direction case prison qui est la case 10
            currentPosition = if (tempPosition == 20) 10 else tempPosition

            println("player position= $currentPosition, player money= $currentAmount")

            // si le joueur n'a plus d'argent alors il ne peut plus se déplacer
            if (currentAmount <= 0) {
                break
            }
        }
        return currentPosition
    }

    @Test
    fun ` case 1`() {

        var currentPosition = solve(
            playerAmount = 20_000,
            gestureString = "3 2 4 6 6 2 1 3 4 5 2 3 5 3 1 2 2 6 3 2 3 2 2 2 5 4 3 3 2 1 4 5 2 2 4 1 3 5 4 1 3 6 3 2 2 3 4 4 3 1",
            priceByCaseString = "0 1700 3600 1800 2100 1100 2200 2500 2500 0 2900 2100 2100 2800 3100 1500 2900 2100 3200 0 3200 1100 1100 3200 2500 3700 3500 1300 3900 2700 3300 1100 3400 2900 1900 1400 3000 3200 3000 1400"
        )

        Assertions.assertEquals(26, currentPosition)

    }

    @Test
    fun ` case 3`() {

        var currentPosition = solve(
            playerAmount = 100_000,
            gestureString = "1 4 6 5 3 2 1 2 6 6 6 5 6 4 1 2 2 3 5 5 5 2 5 3 3 4 4 4 4 2 6 5 5 6 4 2 2 4 3 1 3 2 6 3 5 6 4 1 3 3 6 1 4 4 4 1 2 1 4 6 2 4 4 1 3 1 2 4 5 4 5 1 6 4 4 4 3 2 5 5 5 4 5 2 2 2 3 3 3 1 2 5 4 6 6 1 6 1 5 5",
            priceByCaseString = "0 3000 3500 3100 1800 3400 1900 2700 2300 0 3800 2300 2900 3500 2100 1900 3300 2900 3500 0 3600 1200 3200 1800 1900 3300 2400 2600 2300 1500 2300 1100 3500 1700 3300 1200 1100 1200 2900 2400"
        )

        Assertions.assertEquals(27, currentPosition)

    }


    @Test
    fun ` case 2`() {

        var currentPosition = solve(
            playerAmount = 90_000,
            priceByCaseString = "0 1700 3800 4000 1200 2400 2100 2800 3200 0 1700 2500 3100 2300 1300 3600 3600 3400 2600 0 1700 2500 1900 2600 3500 3300 1400 2100 1200 1600 1200 3600 2400 1000 3600 2600 2400 1600 1300 1600",
             gestureString= "4 3 5 2 5 5 2 4 4 5 4 6 2 6 3 1 4 4 3 5 5 3 4 2 3 3 5 6 4 6 1 2 2 6 4 1 5 5 5 2 3 2 1 4 1 4 5 5 1 1 3 6 4 6 1 1 2 6 6 6 5 1 2 6 6 5 1 4 4 5 6 6 6 1 4 6 4 2 4 5 3 1 4 6 6 5 6 2 4 6 2 2 1 3 2 1 2 2 5 5"
        )

        Assertions.assertEquals(17, currentPosition)

    }



}