import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.properties.Delegates

sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

typealias Predicate<T> = (T) -> Boolean

interface Base {
    fun printMessage()
    fun printMessageLine()
}

class BaseImpl(private val x: Int) : Base {
    override fun printMessage() {
        print(x)
    }

    override fun printMessageLine() {
        println(x)
    }

}

class Derived(base: Base) : Base by base {
    override fun printMessage() {
        print("abc")
    }
}

class User {
    var name: String by Delegates.observable("<no name>") { _, oldValue, newValue ->
        println("oldValue= $oldValue, newValue= $newValue")
    }
}


class EvalTest {


    fun eval(expr: Expr): Double = when (expr) {
        is Const -> expr.number
        is Sum -> eval(expr.e1) + eval(expr.e2)
        NotANumber -> Double.MAX_VALUE
    }


    @Test
    fun `test eval function`() {
        val number = eval(
            Sum(
                Const(15.0),
                Const(19.6)
            )
        )

        Assertions.assertEquals(
            34.6,
            number
        )

    }

    @Test
    fun `test predicate`() {

        val f: Predicate<Int> = { it > 0 }

        Assertions.assertTrue(f(5))
        Assertions.assertFalse(f(-5))
    }

    @Test
    fun `test class delegate`() {
        val derived = Derived(BaseImpl(15))

        derived.printMessage()
        derived.printMessageLine()
    }

    @Test
    fun `test property delegate`() {
        val user = User()
        user.name = "first"
        user.name = "second"
    }
}