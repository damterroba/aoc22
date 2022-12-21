package days

class Day21 : Day() {
    var monkeys: MutableMap<String, List<Any>>

    init {
        monkeys = input.split("\n").map {
            val (m, r) = it.split(": ")
            val splits = r.split(" ")
            if (splits[0].toLongOrNull() != null) {
                m to listOf(splits[0].toLong())
            } else {
                m to r.split(" ").take(3)
            }
        }.toMap().toMutableMap()
    }

    override fun part1(): Any {
        return getValue("root")
    }

    fun getValue(monkey: String): Long {
        try {
            val v = monkeys[monkey] ?: throw Exception("no monkey here $monkey")
            if (v.first() is String) {
                return when (v[1]) {
                    "*" -> getValue(v[0] as String) * getValue(v[2] as String)
                    "+" -> getValue(v[0] as String) + getValue(v[2] as String)
                    "-" -> getValue(v[0] as String) - getValue(v[2] as String)
                    "/" -> getValue(v[0] as String) / getValue(v[2] as String)
                    else -> throw Exception("undefined op")
                }
            }
            return monkeys.get(monkey)!!.first() as Long
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    fun solve(key: String, target: Long): Any {
        if (!monkeys.containsKey(key)) {
            return target
        }
        if (monkeys[key]!!.first() is Long) {
            return monkeys[key]!!.first() as Long
        }
        val (f, op, s) = monkeys[key]!!
        var deadKey = ""
        var tScore = Long.MAX_VALUE
        try {
            tScore = getValue(f as String)
        } catch (e: Exception) {
            deadKey = f as String
        }
        try {
            tScore = getValue(s.toString())
        } catch (e: Exception) {
            deadKey = s as String
        }
        return when (op) {
            "*" -> solve(deadKey, target / tScore)
            "-" -> if (deadKey == f) solve(deadKey, target + tScore) else solve(deadKey, tScore - target)
            "+" -> solve(deadKey, target - tScore)
            "/" -> if (deadKey == f) solve(deadKey, target * tScore) else solve(deadKey, tScore / target)
            else -> throw IllegalArgumentException("undefined op")
        }
    }

    override fun part2(): Any {
        monkeys.remove("humn")
        monkeys["root"] = listOf(monkeys["root"]!![0], "-", monkeys["root"]!![2])
        return solve("root", 0)
    }
}

fun main() {
    val d = Day21()
    println(d.part1())
    println(d.part2())
}