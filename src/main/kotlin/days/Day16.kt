package days

class Day16 : Day() {
    val directed:MutableMap<String, MutableMap<String, Int>>
    val valves:   MutableMap<String, Pair<Int, List<String>>>
    val flows: Map<String, Int>
    init{
        valves = parse()
        directed = getDirected(valves)
        flows =  valves.mapNotNull { if (it.value.first > 0) it.key to it.value.first else null }.toMap()
    }
    override fun part1(): Any {
        return search(30, "AA", flows, directed)
    }

    fun getDirected(valves: MutableMap<String, Pair<Int, List<String>>>): MutableMap<String, MutableMap<String, Int>> {
        val l = buildMap<String, MutableMap<String, Int>> {
            valves.forEach { f ->
                putIfAbsent(f.key, mutableMapOf())
                valves.forEach { s ->
                    if (s.key in f.value.second) {
                        get(f.key)!!.put(s.key, 1)
                    } else if (s.key == f.key) {
                        get(f.key)!!.put(s.key, 0)
                    } else {
                        get(f.key)!!.put(s.key, 100000)
                    }
                }
            }
        }.toMutableMap()
        valves.keys.forEach { k ->
            valves.keys.forEach { j ->
                valves.keys.forEach { i ->
                    l[i]!!.replace(j, minOf(l[i]!![j]!!, l[i]!![k]!! + l[k]!![j]!!))
                }
            }
        }
        return l
    }

    fun search(
        timer: Int,
        start: String = "AA",
        flows: Map<String, Int>,
        dists: Map<String, MutableMap<String, Int>>,
        elephant: Boolean = false
    ): Int {
        var targets = flows.filter { dists[start]!!.contains(it.key) && (dists[start]!![it.key]!! < timer) }
        if (targets.isEmpty()) {
            return 0
        } else {
            val res = targets.map {
                flows.getOrDefault(
                    it.key,
                    0
                ) * (timer - dists[start]!![it.key]!! - 1) + search(
                    timer - dists[start]!![it.key]!! - 1,
                    it.key,
                    flows.filterKeys { k -> k != it.key },
                    dists,
                    elephant
                )
            }.toMutableList()
            if (elephant) {
                res.add(search(26, flows = flows, dists = dists))
            }
            return res.max()
        }
    }


    override fun part2(): Any {
        return search(26, "AA", flows, directed, true)
    }

    fun parse(): MutableMap<String, Pair<Int, List<String>>> {
        val valves = mutableMapOf<String, Pair<Int, List<String>>>()
        input.split("\n").forEach {
            val regex =
                "Valve ([A-Z]{2}) has flow rate=([0-9]+); tunnels? leads? to valves? ((?:([A-Z]{2}),\\s){0,})([A-Z]{2})".toRegex()
            val matchGroup = regex.find(it)
            if (matchGroup != null) {
                val dests = mutableListOf<String>()
                if (matchGroup.groupValues[3].isNotEmpty()) {
                    dests.addAll(matchGroup.groupValues[3].split(",").map { it.trim() }.filterNot(String::isEmpty))

                }
                dests.add(matchGroup.groupValues.last())

                valves.put(matchGroup.groupValues[1], Pair(matchGroup.groupValues[2].toInt(), dests))
            }
        }
        return valves
    }
}

fun main() {
    val d = Day16()
    println(d.part1())
    println(d.part2())
}