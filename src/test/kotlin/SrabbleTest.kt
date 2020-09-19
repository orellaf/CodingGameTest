import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SrabbleTest {


    /*
    input :
    =======
    a 1 b 3 c 3 d 2 e 1 f 4 g 2 u 1
    cafe 
    button 
    face
    bad
    zebra
    bug

    output:
    =======
    cafe
    face
    bad
    bug
    zebra
    button

    point:
    ======
    cafe 3+1+4+1=9 
    button 3+1+0+0+0+0=4
    face 4+1+3+1=9
    bad 3+1+2=6 
    zebra 0+1+3+0+1=5
    bug 3+1+2=6
    */

    fun solve(scrabblePoint: Map<String, Int>, wordsList: List<String>): List<String> {
        val wordWithPoint = hashMapOf<String, Int>()
        val wordWithIndex = hashMapOf<String, Int>()
        for ((index, word) in wordsList.withIndex()) {
            var pointWord = 0
            wordWithIndex.put(word, index)
            for (char in word) {
                val pointChar = scrabblePoint.getOrDefault(char.toString(), 0)
                pointWord += pointChar

            }
            wordWithPoint.put(word, pointWord)
        }

        return wordWithPoint.toSortedMap(Comparator<String> { word1, word2 ->
            val pointDiff = wordWithPoint.get(word1)!! - wordWithPoint.get(word2)!!
            if (pointDiff == 0) {
                wordWithIndex.get(word1)!! - wordWithIndex.get(word2)!!
            } else {
                -pointDiff
            }
        })
            .keys.toList()
    }


    @Test
    fun `nominal case`() {

        val pointString = "a 1 b 3 c 3 d 2 e 1 f 4 g 2 u 1"
        val split = pointString.split(" ")
        val pointMap = hashMapOf<String, Int>()
        for (i in 0 until split.size step 2) {
            pointMap.put(split[i], split[i + 1].toInt())
        }

        Assertions.assertEquals(1, pointMap.get("a"))
        Assertions.assertEquals(3, pointMap.get("b"))
        Assertions.assertEquals(3, pointMap.get("c"))
        Assertions.assertEquals(2, pointMap.get("d"))
        Assertions.assertEquals(1, pointMap.get("e"))
        Assertions.assertEquals(4, pointMap.get("f"))
        Assertions.assertEquals(2, pointMap.get("g"))
        Assertions.assertEquals(1, pointMap.get("u"))

        val wordsList = arrayListOf<String>("cafe", "button", "face", "bad", "zebra", "bug")

        val listSort = solve(
            pointMap, wordsList
        )
        listSort.forEach(::println)

        Assertions.assertArrayEquals(
            arrayOf("cafe", "face", "bad", "bug", "zebra", "button"),
            listSort.toTypedArray()
        )

    }
}