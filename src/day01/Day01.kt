package day01

import readInputText

fun sumCalories(input: String): List<Int> {
    return input.split("\n\n")
        .map { it.lines().sumOf(String::toInt) }
        .sortedDescending()
}

fun main() {

    fun part1(list : List<Int>): Int {
       return list.max()
    }

    fun part2(list: List<Int>): Int {
        return list.take(3).sum();
    }

    val input = readInputText("Day01")
    val list = sumCalories(input)

    println("Elf with the most calories carries: " + part1(list))
    println("Top three elfs carry: " + part2(list))
}
