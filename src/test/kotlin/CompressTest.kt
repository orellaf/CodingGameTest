import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CompressTest {

//    Enoncé
//
//    Dans ce challenge, vous devez rédiger un programme qui met en place une sorte de compression par "Run Length Encoding".
//    Il s'agit d'obtenir une représentation plus compacte d'une suite de caractères en remplaçant les séquences de caractères
//    identiques par une indication du nombre de caractères identiques consécutifs. La séquence aaaaaaa sera ainsi représentée
//    par 7a (le nombre 7 et le caractère a).
//
//    Afin d'éviter d'augmenter la taille de la suite de caractères, on ne traitera ainsi que les séquences de (strictement)
//    plus de 2 caractères identiques. En effet, 2i comprend 2 caractères tout comme la séquence ainsi représentée ii,
//    la compression n'aurait alors pas d'intérêt. Donc aaaaaaabbbbii sera remplacé par 7a4bii.
//
//
//    Format des données
//
//    Entrée
//    Une unique suite de lettres en minuscules comprises entre a et z.
//
//    Sortie
//    La série compressée.
    fun solve(rawText: String): String {

        // le caractèere en cours
        var previousChar: Char? = rawText.firstOrNull()
        var occurence = 1
        var outputString = ""

        for (index in 1 until rawText.length) {
            var char = rawText[index]
            println("char= $char, index= $index")

            if (previousChar == char) {
                // si le caractere actuel est égale au caratere precedent
                // alors on incrémente le compteur
                occurence++
                println("on incrémente de 1 le compteur pour le caractere ")
                println("previousChar= $previousChar, occurence= $occurence")
            } else {
                // si il est différent on regarde la valeur du compteur si elle supérieur à deux alors
                // on simplifie l'écriture
                outputString += if (occurence > 2) "$occurence$previousChar" else previousChar.toString()
                    .repeat(occurence)
                occurence = 1

            }
            previousChar = char

            // si c'est le dernier tour de la boucle  alors on concatène le dernier caractere
            if (index == rawText.length-1 ) {
                outputString += if (occurence > 2) "$occurence$previousChar" else previousChar.toString()
                    .repeat(occurence)
            }


        }
        return outputString
    }

    @Test
    fun `case 1`() {
        val output = solve("bbddb")

        Assertions.assertEquals(
            "bbddb",
            output
        )
    }

    @Test
    fun `case 2`() {
        val output = solve("cccccfffdd")

        Assertions.assertEquals(
            "5c3fdd",
            output
        )
    }

    @Test
    fun `case 3`() {
        val output = solve("eeeeeaaaaa")

        Assertions.assertEquals(
            "5e5a",
            output
        )
    }
}