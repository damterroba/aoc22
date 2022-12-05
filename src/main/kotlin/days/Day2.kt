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

    val pred = { c: String, s: String -> c.startsWith(s) }
    override fun part2(): Int = input.split("\n").sumOf {
        val play = it.split(" ")
        when (play.last()) {
            "X" -> loss.get(loss.keys.find { k -> pred(k, play.first()) })
            "Y" -> draws.get(draws.keys.find { k -> pred(k, play.first()) })
            else -> wins.get(wins.keys.find { k -> pred(k, play.first()) })
        }
    }
}

fun main() {
    val d = Day2()
    println(d.part1())
    println(d.part2())
}