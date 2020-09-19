import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class InverseWordTest {

    fun isSpecialCharacter(char: Char): Boolean =
        arrayListOf<Char>(',', '!', ' ', '.').contains(char)

    fun transform(sentence: String): String {
        var transformString = ""
        var tempString = ""
        for ((index, char) in sentence.withIndex()) {
            // On parcours la chaine de caractères.
            // Si le caractère est special alors on regarde si on a stocké
            // des caractères texte. Si il y en a alors on les inverse
            // on les ajoute à la nouvelle chaine de caractères.
            // Dans le cas ou la chaine de caractère ne finit par un caractère de ponctuation.
            // Alors il faut regarder si il y a des caracteres texte stockés.
            if (isSpecialCharacter(char) ||
                (index == sentence.length - 1 && tempString.isNotBlank())
            ) {
                if (!isSpecialCharacter(char)) {
                    tempString += char.toString()
                }
                if (tempString.isNotBlank()) {
                    transformString += tempString.reversed()
                    tempString = ""
                }
                if (isSpecialCharacter(char)) {
                    transformString += char.toString()
                }
            } else {
                tempString += char.toString()
            }
        }


        return transformString
    }

    @Test
    fun `case without a dot at the end of the sentence`() {
        val result = transform("Hello")
        Assertions.assertEquals(
            "olleH",
            result
        )
    }

    @Test
    fun `case ok`() {
        val result = transform("Hello Orel, you are a lazy man.")
        Assertions.assertEquals(
            "olleH lerO, uoy era a yzal nam.",
            result
        )
    }

    @Test
    fun `case ok 01`() {
        val result = transform("Hello Cam, you are a bitch !!")
        Assertions.assertEquals(
            "olleH maC, uoy era a hctib !!",
            result
        )
    }
}