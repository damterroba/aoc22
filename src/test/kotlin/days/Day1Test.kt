package days

import kotlin.test.Test
import kotlin.test.assertEquals


class Day1Tests {
    private val d = Day1()
    @Test
    fun part1(){
        assertEquals(24000, d.part1())
    }

    @Test
    fun part2(){
        assertEquals(45000, d.part2())
    }
}