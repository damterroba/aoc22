package days

// a x rock
// b y paper
// c z scissors
class Day2 : Day() {
    val wins = mapOf<String, Int>(
        "A Y" to 8,
        "B Z" to 9,
        "C X" to 7
    )
    val draws = mapOf<String, Int>(
        "A X" to 4,
        "B Y" to 5,
        "C Z" to 6
    )
    val loss = mapOf<String, Int>(
        "A Z" to 3,
        "B X" to 1,
        "C Y" to 2
    )

    override fun part1(): Int = input.split("\n").sumOf {
        when {
            it in wins -> wins[it]
            it in draws -> draws[it]
            else -> loss[it]
        }
    }

    override fun part2(): Any = input.split("\n").sumOf { it ->
        val play = it.split(" ")
        val key = { c: String -> c.startsWith(play.first()) }
        when (play.last()) {
            "X" -> loss.get(loss.keys.find { k -> key(k) })
            "Y" -> draws.get(draws.keys.find { k -> key(k) })
            else -> wins.get(wins.keys.find { k -> key(k) })
        }
    }
}

fun main() {
    val d = Day2()
    println(d.part1())
    println(d.part2())
}