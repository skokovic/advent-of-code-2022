package day03

import readInput

fun calculateScore(char: Char): Int {
    if(char.isUpperCase()) {
        return char.code - 38
    }
    return char.code - 96
}

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { sack ->
            val size = sack.length
            val first = sack.substring(0, size/2)
            val second = sack.substring(size/2, size)
            for (c in first) {
                if (second.contains(c)) {
                    sum += calculateScore(c)
                    break
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        var i = 0
        while(i <= input.size - 2) {
            val first = input[i]
            val second = input[i+1]
            val third = input[i+2]

            for (c in first) {
                if (second.contains(c) && third.contains(c)) {
                    sum += calculateScore(c)
                    break
                }
            }
            i+=3
        }
        return sum
    }


    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
