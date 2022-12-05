package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Tests {
    val d = Day5()

    @Test
    fun test(){
        assertEquals("CMZ",d.part1())
        assertEquals("MCD", d.part2())
    }
}