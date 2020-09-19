import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FunctionTest {

    @Test
    fun `test function higherOrder`() {
       // higherOrder(println) not compiled
        higherOrder({ println(it) })
        higherOrder{ println(it) }
        higherOrder(::println)

        assert(true)
        Assertions.assertEquals(5, 2)
    }

    fun higherOrder(block: (String) -> Unit) {
        block("Welcome to CodinGame!")
    }
}