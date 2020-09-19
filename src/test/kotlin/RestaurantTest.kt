import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RestaurantTest {


    fun computeDayGains(nbSeats: Int, payingGuest: Array<Int>, guestMovements: Array<Int>): Int {

        val guestInPlace = mutableListOf<Int>()
        val guestAlreadyPaid = mutableListOf<Int>()
        val guestInQueue = mutableListOf<Int>()
        var seatAvailable = nbSeats

        var price = 0

        for (guestId in guestMovements) {
            if (guestAlreadyPaid.contains(guestId)) {
                // le client est revenu au buffet à volonté, donc il ne doit pas repayer
            } else if (guestInPlace.contains(guestId)) {
                // si le client est déjà en place dans le restaurant, cela veut dire qu'il va partir
                // donc une place est vacante
                guestInPlace.remove(guestId)
                seatAvailable++
                price += payingGuest[guestId]
                guestAlreadyPaid.add(guestId)
            } else {
                if (seatAvailable > 0) {
                    // si il y a des places, le client peut manger
                    guestInPlace.add(guestId)
                    seatAvailable--
                } else {
                    // le client est mis dans la file d'attente quand il n'y a plus de place
                    // le client en a marre d'attendre et s'en va
                    if (guestInQueue.contains(guestId)) guestInQueue.remove(guestId)
                    else guestInQueue.add(guestId)

                }

            }

            // si il y a une place qui se libere et qu'il y a des clients dans la file d'attente
            // alors on place le premier client de la file d'attente
            if (seatAvailable > 0 && guestInQueue.isNotEmpty()) {
                val firstGuest = guestInQueue.removeAt(0)
                guestInPlace.add(firstGuest)
                seatAvailable--

            }
        }

        return price
    }

    @Test
    fun `fonctionnement nominal`() {
        val price = computeDayGains(
            nbSeats = 50_000,
            payingGuest = arrayOf(
                34, 29, 10, 15, 1
            ),
            guestMovements = arrayOf(
                0, 0, 1, 2, 3, 3, 2, 1
            )
        )
        Assertions.assertEquals(88, price)
    }

    @Test
    fun `le client revient`() {
        val price = computeDayGains(
            nbSeats = 10,
            payingGuest = arrayOf(
                34, 29
            ),
            guestMovements = arrayOf(
                0, 0, 1, 1, 0, 0
            )
        )
        Assertions.assertEquals(63, price)
    }

    @Test
    fun `file d'attente`() {
        val price = computeDayGains(
            nbSeats = 2,
            payingGuest = arrayOf(
                34, 29, 10, 15, 1, 100, 23, 56, 87, 2
            ),
            guestMovements = arrayOf(
                9, 8, 7, 6, 5, 4, 3, 9
            )
        )
        Assertions.assertEquals(2, price)

    }

    @Test
    fun `file d'attente trop longue`() {
        val price = computeDayGains(
            nbSeats = 5,
            payingGuest = arrayOf(
                34, 29, 10, 15, 1, 100, 23, 56, 87, 2
            ),
            guestMovements = arrayOf(
                9, 8, 7, 6, 5, 4, 3, 3, 4, 5, 9, 8, 7, 6
            )
        )
        Assertions.assertEquals(268, price)

    }
}