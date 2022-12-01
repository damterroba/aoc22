package days

import java.io.FileNotFoundException

abstract class AbstractDay {
    val input: String = javaClass.getResource("/inputs/${this.javaClass.simpleName.lowercase()}/input")?.readText() ?: throw FileNotFoundException()

    abstract fun part1(): Any
    abstract fun part2(): Any

}