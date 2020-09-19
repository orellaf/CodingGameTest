import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ArrayTest {

    @Test
    fun `array test`() {
//        val arr = arrayOf("string", "96", "8663", "dddd")
//        for ( (index,value) in arr.withIndex()) {
//            println("index= $index, value= $value")
//        }
//        println(" for (i in 1..3) ")
//        for (i in 1..3 step 3) {
//            print(i)
//        }
//        println("")
//        println("for (i in 1 until 3) ")
//        for (i in 1 until 3 step 2) {
//            print(i)
//        }
//        println("")
//        println(" for (i in arr.size-1 downTo 0 step 2)")
//        for (i in arr.size-1 downTo 0 step 2) {
//            print(arr[i])
//        }



        val appendStrategy = AppendArray.ListAppendArray
        val arr01 = appendStrategy.append(arrayOf(5, 6), 5666) // add 5 to nums[]

        println("array = ${arr01.contentToString()}")
        Assertions.assertEquals("[5, 6, 5666]", arr01.contentToString())

    }

    sealed class AppendArray {

        abstract fun append(arr: Array<Int?>, element: Int): Array<Int?>

        object ListAppendArray : AppendArray() {
            override fun append(arr: Array<Int?>, element: Int): Array<Int?> {
                val list: MutableList<Int?> = arr.toMutableList()
                list.add(element)
                return list.toTypedArray()
            }

        }

    }

}