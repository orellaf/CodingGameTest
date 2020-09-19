import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class CardTest {

//    Enoncé
//
//    Dans ce challenge, vous devez identifier les cartes manquantes dans un jeu de cartes. Le jeu de cartes initial comprend
//    52 cartes.
//    Chaque carte est identifiée par un premier caractère indiquant la couleur puis un ou deux caractères indiquant la valeur.
//    Les couleurs des cartes peuvent être C pour Coeur, P pour Pique, Q pour Carreau, T pour Trèfle.
//    Les valeurs des cartes peuvent être : 2,3,4,5,6,7,8,9,10,V,D,R,A.
//
//    Votre code doit renvoyer les cartes manquantes dans le jeu en les triant d'abord par couleur : Coeur puis Pique,
//    puis Carreau, puis Trèfle et en les triant ensuite par valeur croissantes (de 2 à A).
//
//    Format des données
//
//    Entrée
//    Entre 1 et 51 cartes séparées par des espaces. Chaque carte est une chaine de 2 ou 3 caractères représentant la
//    couleur puis la valeur de la carte: C7 PV Q10. Les cartes ont été mélangées au hasard.
//
//
//    Sortie
//    Entre 1 et 51 cartes séparées par des espaces triées d'abord par couleur : Coeur puis Pique, puis Carreau,
//    puis Trèfle puis triées par valeur croissantes (de 2 à A) représentant les cartes manquantes dans le jeu de
//    cartes fourni en entrée.

    val cards = arrayOf(
        "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CV", "CD", "CR", "CA",
        "P2", "P3", "P4", "P5", "P6", "P7", "P8", "P9", "P10", "PV", "PD", "PR", "PA",
        "Q2", "Q3", "Q4", "Q5", "Q6", "Q7", "Q8", "Q9", "Q10", "QV", "QD", "QR", "QA",
        "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "TV", "TD", "TR", "TA"

    )

    fun solve(availableCards: String): String {
        val missingCards = cards.toMutableList()

        for (card in availableCards.split(" ")) {
            missingCards.remove(card)
        }

        return missingCards.joinToString(separator = " ")

    }


    @Test
    fun `case 1`() {
        val missingCards =
            solve("Q4 Q10 CD T4 C10 P10 T3 P5 QA C9 T6 T8 QV C5 PR C6 PA T9 Q7 T7 TD C7 P4 C8 TA TR Q6 QD PD CV P9 Q8 Q9 Q5")

        Assertions.assertEquals(
            "C2 C3 C4 CR CA P2 P3 P6 P7 P8 PV Q2 Q3 QR T2 T5 T10 TV",
            missingCards
        )
    }

    @Test
    fun `case 2`() {
        val missingCards = solve("P9")

        Assertions.assertEquals(
            "C2 C3 C4 C5 C6 C7 C8 C9 C10 CV CD CR CA P2 P3 P4 P5 P6 P7 P8 P10 PV PD PR PA Q2 Q3 Q4 Q5 Q6 Q7 Q8 Q9 Q10 QV QD QR QA T2 T3 T4 T5 T6 T7 T8 T9 T10 TV TD TR TA",
            missingCards
        )
    }

    @Test
    fun `case 3`() {
        val missingCards = solve("P9 T2 Q3 Q9 PD C8 QD P8 C5 T7 Q8 Q6 Q5 P10 T6 C6 C4 QA TD")

        Assertions.assertEquals(
            "C2 C3 C7 C9 C10 CV CD CR CA P2 P3 P4 P5 P6 P7 PV PR PA Q2 Q4 Q7 Q10 QV QR T3 T4 T5 T8 T9 T10 TV TR TA",
            missingCards
        )
    }
}