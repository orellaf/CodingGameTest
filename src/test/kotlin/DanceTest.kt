import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertTimeout
import java.time.Duration


class DanceTest {

    fun getPositionAt(n: Int): Int {
//        var stepList = mutableListOf<Int>(1, -2)
        var firstStep = 1
        var secondStep = -2

        var index = 3
        var dancerPosition = when(n) {
            0 -> 0
            1 -> 1
            else -> -1
        }
        while (n > 2 && index <= n) {

//            stepList.add(stepList[1] - stepList[0])
//            dancerPosition += stepList[1] - stepList[0]
//            stepList.removeAt(0)
            val step = secondStep - firstStep
            dancerPosition += step
            firstStep = secondStep
            secondStep = step
            index++
        }

        return dancerPosition
    }

    @Test
    fun `check position for n = 3`() {
        val index = getPositionAt(3)
        Assertions.assertEquals(-4, index)
    }

    @Test
    fun `check position for n = 4`() {
        val index = getPositionAt(4)
        Assertions.assertEquals(-5, index)
    }

    @Test
    fun `check position for n = 1_000_000`() {
        val index = getPositionAt(1_000_000)
        Assertions.assertEquals(-5, index)
    }


    @Disabled
    @Test
    fun `check position for n = 2_147_483_647`() {

//        assertTimeout(timeout = Duration.ofSeconds(1), executable = {
            val index = getPositionAt(2_147_483_647)
            Assertions.assertEquals(1, index)
//        })

    }

}