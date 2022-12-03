package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Tests {
    val d = Day3()
    @Test
    fun d1(){
        assertEquals(157, d.part1())
        d.part2()
    }
}