package day06

import readInputText


fun getIndexOfUniqueWindow(input: String, windowSize: Int): Int {
    input.withIndex().windowed(windowSize).forEach {
        val uniqueChars = it.map { idval -> idval.value }.toSet()
        if (uniqueChars.size == windowSize) {
            return it[windowSize - 1].index + 1
        }
    }
    return 0
}


fun main() {

    fun part1(input: String): Int {
        return getIndexOfUniqueWindow(input, 4)
    }

    fun part2(input: String): Int  {
        return getIndexOfUniqueWindow(input, 14)
    }

    val input = readInputText("Day06")

    println(part1(input))
    println(part2(input))
}
