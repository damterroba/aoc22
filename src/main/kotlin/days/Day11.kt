package days

data class Monkey(
    val id: Int,
    var inspection: ULong = 0UL,
    val items: ArrayDeque<ULong> = ArrayDeque(),
    val operation: Pair<Char, String>,
    val test: ULong,
    val sendsTo: Pair<Int, Int>
) {
    fun runInspection(item: ULong, mod: (ULong) -> ULong): ULong {
        val op: (ULong, ULong) -> ULong = when (operation.first) {
            '*' -> { a: ULong, b: ULong -> a * b }
            '+' -> { a: ULong, b: ULong -> a + b }
            else -> throw Exception("")
        }
        val b: ULong = when (operation.second) {
            "old" -> item
            else -> operation.second.toULong()
        }
        inspection++
        return mod(op(item, b))
    }
}

class Day11 : Day() {

    companion object{
        fun gcd(a: ULong, b: ULong): ULong {
            if (b == 0UL) return a
            return gcd(b, a % b)
        }
        fun lcm(a: ULong, b: ULong): ULong {
            return a / gcd(a, b) * b
        }
        fun lcm(num: List<ULong>): ULong {
            return num.reduce {r, n -> lcm(r, n) }
        }
        var lcm = 1UL
    }
    
    override fun part1():ULong{
        return solve(20,true)
    }
    fun solve(turns: Int, div: Boolean =false): ULong {
        val monkeys = input.split("\n").chunked(7).map {
            val id = it[0].replace(":", "").split(" ").last().toInt()
            val items = it[1].split(":").last().split(",").map { it.trim().toULong() }
            val operation = it[2].split("old ").last().split(" ")
            val test = it[3].split(" ").last().toULong()
            val sendsTo = Pair(
                it[4].split(" ").last().toInt(),
                it[5].split(" ").last().toInt(),
            )
            Monkey(id, operation = Pair(operation.first()[0], operation.last()), test = test, sendsTo = sendsTo).also {
                items.forEach { i ->
                    it.items.add(i)
                }
            }
        }
        lcm = lcm(monkeys.map{it.test}.toList())
//        println("LCM : ${lcm}")
        repeat(turns) {
            monkeys.forEach {
                while (it.items.size > 0) {
                    val item = it.items.removeFirst()
                    var m:( ULong) -> ULong 
                    if(div){
                        m = { a: ULong -> a/3UL}
                    }else {
                        m = { a: ULong -> a%lcm}
                    }
                    val wl = it.runInspection(item, m)
                    if(wl%it.test == 0UL) {
                        monkeys.get(it.sendsTo.first).items.addLast(wl)
                    }else{
                        monkeys.get(it.sendsTo.second).items.addLast(wl)
                    }
                }
            }
        }

//        println()
        return monkeys.map { it.inspection }.sortedByDescending { it }.take(2).reduce { a, e -> a * e}
    }

    override fun part2(): Any {
       return solve(10000)
    }
}

fun main() {
    val d = Day11()
    println(d.part1())
    println(d.part2())
}