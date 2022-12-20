package days


class Day20 : Day() {
    override fun part1(): Any {
        val init = input.split("\n").mapIndexed { index, s -> s.toLong() to index }
        val cInit = mix(init)
        val oIndex = cInit.indexOfFirst { it.first == 0L }
        return cInit[(oIndex + 1000) % (init.size)].first + cInit[(oIndex + 2000) % (init.size)].first + cInit[(oIndex + 3000) % (init.size)].first
    }


    override fun part2(): Any {
        val init = input.split("\n").mapIndexed { index, s -> s.toLong() * 811589153 to index }
        val cInit = mix(init, 10)
        val oIndex = cInit.indexOfFirst { it.first == 0L }
        return cInit[(oIndex + 1000) % (init.size)].first + cInit[(oIndex + 2000) % (init.size)].first + cInit[(oIndex + 3000) % (init.size)].first
    }

    private fun mix(
        init: List<Pair<Long, Int>>,
        times: Int = 1
    ): MutableList<Pair<Long, Int>> {
        val cInit = init.toMutableList()
        repeat(times) {
            init.forEach {
                val itIndex = cInit.indexOf(it)
                cInit.removeAt(itIndex)
                var mod = (itIndex + it.first) % (init.size - 1)
                if (mod <= 0) {
                    mod += (init.size) - 1
                }

                cInit.add(mod.toInt(), it)
                //            }
            }
        }
        return cInit
    }
}

fun main() {
    val d = Day20()
    println(d.part1())
    println(d.part2())
}