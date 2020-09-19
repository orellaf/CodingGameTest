import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ParcelTest {

    enum class ParcelType {
        STANDARD,
        SPECIAL,
        REJECTED
    }

    fun computeVolume(width: Int, height: Int, length: Int) = width * height * length

    fun checkDimension(dimension: Int) = dimension >= 150

    fun solve(width: Int, height: Int, length: Int, mass: Int): ParcelType {
        val heavy = mass >= 20
        val big = computeVolume(width, height, length) >= 1_000_000
                || checkDimension(width) || checkDimension(height) || checkDimension(length)

        return if (heavy && big) ParcelType.REJECTED
        else if (heavy || big) ParcelType.SPECIAL
        else ParcelType.STANDARD
    }

    @Test
    fun `should be standard`() {
        val parcelType = solve(
            width = 90,
            height = 90,
            length = 118,
            mass = 10
        )
        Assertions.assertEquals(ParcelType.STANDARD, parcelType)
    }

    @Test
    fun `should be rejected`() {
        val parcelType = solve(
            width = 120,
            height = 100,
            length = 110,
            mass = 20
        )
        Assertions.assertEquals(ParcelType.REJECTED, parcelType)
    }

    @Test
    fun `should be special with volume`() {
        val parcelType = solve(
            width = 120,
            height = 100,
            length = 110,
            mass = 8
        )
        Assertions.assertEquals(ParcelType.SPECIAL, parcelType)
    }

    @Test
    fun `should be special with mass`() {
        val parcelType = solve(
            width = 120,
            height = 5,
            length = 2,
            mass = 20
        )
        Assertions.assertEquals(ParcelType.SPECIAL, parcelType)
    }

}