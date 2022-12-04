package day04

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        val pairRegex = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()

        input.forEach {
            val (sx, ex, sy, ey) = pairRegex.matchEntire(it)!!.destructured
            val (startX, endX) = sx.toInt() to ex.toInt()
            val (startY, endY) = sy.toInt() to ey.toInt()
            if (startX <= startY && endX >= endY) sum++
            else if (startY <= startX && endY >= endX) sum ++
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        val pairRegex = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()

        input.forEach {
            val (sx, ex, sy, ey) = pairRegex.matchEntire(it)!!.destructured
            val (startX, endX) = sx.toInt() to ex.toInt()
            val (startY, endY) = sy.toInt() to ey.toInt()
            if (startX <= startY && endX >= endY) sum++
            else if (startY <= startX && endY >= endX) sum ++
            else if (startY > startX && startY <= endX) sum++
            else if (startX > startY && startX <= endY) sum++
        }

        return sum
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
