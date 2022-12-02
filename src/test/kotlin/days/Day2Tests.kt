package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Tests {
    private val d = Day2()

    @Test
    fun p1(){
        assertEquals(15, d.part1())
    }

    @Test
    fun p2(){
        assertEquals(12, d.part2())
    }
}