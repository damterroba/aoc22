package days

class Day10 : Day() {
    override fun part1(): Any {
        val states = mutableListOf<Int>()
        var c = 0
        var value = 1
        val targets = (20..220 step 40)
        input.split("\n").forEach {
            when (it != "noop") {
                true -> {
                    val (com, amount) = it.split(" ")
                    repeat(2) {
                        c++
                        if (c in targets) {
                            println("adding $value -> $c")
                            states.add(value)
                        }
                    }
                    if (com == "addx") {
                        value += amount.toInt()
                    }

                }

                else -> {
                    c++
                    if (c in targets) {
                        println("adding $value at $c")
                        states.add(value)
                    }
                }
            }
        }
        println(c)
        println(value)
        println(states)
        return states.zip(targets) { a, b -> a * b }.sum()
    }

    override fun part2() {
        val states = mutableListOf<Int>()
        var c = 0
        var value = 1
        val targets = (40..240 step 40)
        val outputs = mutableListOf<String>()
        var currentLine = ""
        var spriteP = (0..2)
        input.split("\n").forEach{it ->
            when (it != "noop") {
                true -> {
                    val (_, amount) = it.split(" ")
                    repeat(2) {
                        c++ 
                        if(c%40 in spriteP){
                            currentLine += '#'
                        }else{
                            currentLine += '.'
                        }
                        if (c in targets) {
                            println("adding $value -> $c")
                            states.add(value)
                            outputs.add(currentLine)
                            currentLine = ""
                        }
                    }
                    
                        value += amount.toInt()
                        spriteP = (value..value+2)
                }
                else -> {
                    c++
                    if(c%40 in spriteP){
                        currentLine += '#'
                    }else{
                        currentLine += '.'
                    }
                    if (c in targets) {
                        states.add(value)
                        outputs.add(currentLine)
                        currentLine = ""
                    }
                    spriteP = (value..value+2)
                }
            }
        }
        outputs.forEach { 
            println(it)
        }
    }
}

fun main() {
    val d = Day10()
    println(d.part1())
    d.part2()
}