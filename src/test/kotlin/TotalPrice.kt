import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max

class TotalPrice {

    // sur l'intégralité du panier, on récupère le produit le plus cher
    // et on applique la réduction sur ce produit

    @Test
    fun `teste si le prix est arrondi à l'entier inférieur`() {
        val price = calculateTotalPrice(
            arrayOf(
                15,
                201,
                99
            ), 22
        )

        Assertions.assertEquals(270, price)

        val i: ULongArray
        // val j :UFloat not compiled
        val k: UShort
        // val l: UFloatArray not compiled
        val m: UInt

    }

    fun calculateTotalPrice(prices: Array<Int>, discount: Int): Int {
        if (prices == null || prices.isEmpty()) return 0

        val maxPrice = prices.max()
        val sum = prices.sum()
        val compute: Double = sum.toDouble() - (maxPrice!!.toDouble() * discount.toDouble() / 100).toDouble()

        return Math.floor(compute).toInt()
    }
}