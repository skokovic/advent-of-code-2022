package day02

import readInput

fun calculateTuple(elf: String, me: String): Int {
    var sum = 0

    when (me) {
        "X" -> sum += 1
        "Y" -> sum += 2
        "Z" -> sum += 3
    }

    when (elf) {
        "A" -> when (me) {
            "X" -> sum += 3
            "Y" -> sum += 6
            "Z" -> sum += 0
        }
        "B" -> when (me) {
            "X" -> sum += 0
            "Y" -> sum += 3
            "Z" -> sum += 6
        }
        "C" -> when (me) {
            "X" -> sum += 6
            "Y" -> sum += 0
            "Z" -> sum += 3
        }
    }

    return sum
}

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach {
            val (elf, me) = it.split(' ')
            sum += calculateTuple(elf, me)
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        input.forEach {
            val (elf, me) = it.split(' ')
            when (me) {
                "X" -> when(elf) {
                    "A" -> sum+= calculateTuple("A", "Z")
                    "B" -> sum+= calculateTuple("B", "X")
                    "C" -> sum+= calculateTuple("C", "Y")
                }
                "Y" -> when(elf) {
                    "A" -> sum+= calculateTuple("A","X")
                    "B" -> sum+= calculateTuple("B","Y")
                    "C" -> sum+= calculateTuple("C","Z")
                }
                "Z" -> when(elf) {
                    "A" -> sum+= calculateTuple("A","Y")
                    "B" -> sum+= calculateTuple("B","Z")
                    "C" -> sum+= calculateTuple("C","X")
                }
            }
        }
        return sum
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
