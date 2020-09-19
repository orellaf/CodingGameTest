import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TournamentTest {


    fun solve(nbPlayers: Int): Int {
        var tournament = 0
        loop@for (i in 0..nbPlayers) {

            var temp = nbPlayers - 1 - i
            if (temp >= 0) {
                tournament += temp
            } else {
                break@loop
            }

        }

        return tournament
    }

    @Test
    fun `for 4 players, 6 tournaments`() {
        val nbTournament = solve(4)
        Assertions.assertEquals(6, nbTournament)
    }

    @Test
    fun `for 10_000 players, 49_995_000 tournaments`() {
        val nbTournament = solve(10_000)
        Assertions.assertEquals(49_995_000, nbTournament)
    }
}