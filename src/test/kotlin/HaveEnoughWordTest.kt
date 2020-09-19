import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HaveEnoughWordTest {

    fun isSpecialCharacter(char: Char): Boolean =
        arrayListOf<Char>(',', '!', ' ', '.', '\'').contains(char)

    private fun createWordMap(sentence: String): Map<String, Int> {
        val wordsMap = hashMapOf<String, Int>()
        var tempWord = ""
        for (char in sentence) {
            if (isSpecialCharacter(char)) {
                if (tempWord.isNotBlank()) {
                    val count = wordsMap.getOrDefault(tempWord, 0)
                    when (count) {
                        0 -> wordsMap.put(tempWord, 1)
                        else -> wordsMap.put(tempWord, count + 1)
                    }
                    tempWord = ""
                }
            } else {
                tempWord += char.toString()
            }
        }
        if (tempWord.isNotBlank()) {
            val count = wordsMap.getOrDefault(tempWord, 0)
            when (count) {
                0 -> wordsMap.put(tempWord, 1)
                else -> wordsMap.put(tempWord, count + 1)
            }

        }
        return wordsMap
    }


    private fun haveEnoughtWord(inputSentence: String, sentenceToBuild: String): Boolean {
        val inputWordMap = createWordMap(inputSentence)
        val expectedWordMap = createWordMap(sentenceToBuild)

        var haveEnough = true
        for (key in expectedWordMap.keys) {
            if (inputWordMap.get(key) == null ||
                inputWordMap.get(key)!! < expectedWordMap.get(key)!!
            ) {
                haveEnough = false
                break
            }
        }

        return haveEnough
    }

    @Test
    fun `case OK`() {
        val result = haveEnoughtWord(
            inputSentence = "Bonjour, je suis le gentil de l'histoire.",
            sentenceToBuild = "Bonjour, l'histoire."
        )

        Assertions.assertTrue(result)
    }

    @Test
    fun `case OK without a dot`() {
        val result = haveEnoughtWord(
            inputSentence = "Bonjour, je suis le gentil de l'histoire",
            sentenceToBuild = "Bonjour, l'histoire."
        )

        Assertions.assertTrue(result)
    }

    @Test
    fun `case KO , not enough le`() {
        val result = haveEnoughtWord(
            inputSentence = "Bonjour, je suis le dindon de la farce.",
            sentenceToBuild = "Bonjour le dindon le."
        )

        Assertions.assertFalse(result)
    }

    @Test
    fun `case KO`() {
        val result = haveEnoughtWord(
            inputSentence = "Bonjour, je suis le gentil de l'histoire.",
            sentenceToBuild = "Bonjour, l'histoire de ma vie."
        )

        Assertions.assertFalse(result)
    }

}