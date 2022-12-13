package days

import com.beust.klaxon.JsonArray
import com.beust.klaxon.Parser
import extension.groups
import extension.produce
import java.lang.StringBuilder


class Day13: Day() {
    override fun part1(): Any {
        val groups = input.groups().map {
            val (left, right) = it.split("\n").map { parse(it) }
            compare(left, right)
        }
        return groups.mapIndexedNotNull { i, v-> if(v < 1) i +1 else null }.sum()
    }

    override fun part2(): Any {
        val groups = input.groups().flatMap { it.split("\n").map { parse(it) } }.toMutableList()
        val i1 = JsonArray(JsonArray(2))
        val i2 = JsonArray(JsonArray(6))
        groups.addAll(listOf(i1,i2))
        return groups.sortedWith(::compare).mapIndexedNotNull{i, it -> if(it == i1 || it == i2) i+1 else null}.produce()
    }

    fun parse(s: String): JsonArray<*> {
        val json = Parser.default()
        return json.parse(StringBuilder(s)) as JsonArray<*>
    }

    fun compare(l: Any, r: Any): Int {
        return when {
            (l is Int && r is Int) -> l.compareTo(r)
            (l is Int && r is JsonArray<*>) -> compare(JsonArray(l), r)
            (l is JsonArray<*> && r is Int) -> compare(l, JsonArray(r)) 
            l is JsonArray<*> && r is JsonArray<*> -> {
                for(m in (0..minOf(l.size, r.size)-1)){
                    val t = compare(l[m]!!,r[m]!!)
                    if(t != 0){
                        return t
                    }
                    // all equals
                }
                return l.size.compareTo(r.size)
            }else-> 0
        }
    }
}
fun main(){
    val d = Day13()
    println(d.part1())
    println(d.part2())
}