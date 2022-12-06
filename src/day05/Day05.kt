package day05

import readInputText

val moveRegex = """move\s+(\d+)\s+from\s+(\d+)\s+to\s+(\d+)""".toRegex()

data class Move(val count: Int, val from: Int, val to: Int)

fun createStacks(str: String): Map<Int, ArrayDeque<Char>> {
    val lines = str.lines()
    val stackNums = lines[lines.size - 1].trim().split("\\s+".toRegex())
    val stacks = stackNums.associate { it.toInt() to ArrayDeque<Char>() }

    lines.dropLast(1).reversed().forEach {
        for (i in 1 until it.length step 4) {
            if (it[i] != ' ') {
                stacks[i/4+1]?.addLast(it[i])
            }
        }
    }
    return stacks
}

fun createMoves(str: String): List<Move> {
    val lines = str.lines()
    val moves = lines.map {
        val (count, from, to) = moveRegex.matchEntire(it)!!.destructured
        Move(count.toInt(), from.toInt(), to.toInt())
    }.toList()
    return moves
}

fun moveStacks(stacks: Map<Int, ArrayDeque<Char>>, moves: List<Move>, reverse: Boolean = false): Map<Int, ArrayDeque<Char>> {
    moves.forEach {
        val tempQueue = ArrayDeque<Char>()
        for(i in 0 until it.count){
            tempQueue.addLast(stacks[it.from]!!.removeLast())
        }
        if(reverse && !tempQueue.isEmpty()) tempQueue.reverse()
        for(c in tempQueue){
            stacks[it.to]!!.addLast(c)
        }
    }
    return stacks
}

fun main() {

    fun part1(stacks: Map<Int, ArrayDeque<Char>>, moves: List<Move>): String {
        val movedStacks = moveStacks(stacks, moves)
        return movedStacks.values.map { it.removeLast() }.joinToString("")
    }

    fun part2(stacks: Map<Int, ArrayDeque<Char>>, moves: List<Move>): String  {
        val movedStacks = moveStacks(stacks, moves, true)
        return movedStacks.values.map { it.removeLast() }.joinToString("")
    }

    val input = readInputText("Day05")
    val (stackString, moveString) = input.split("\n\n")

    val stacks = createStacks(stackString)
    val moves = createMoves(moveString)

    val copy = hashMapOf<Int, ArrayDeque<Char>>()
    stacks.forEach {
        val tempArr = ArrayDeque<Char>()
        tempArr.addAll(it.value)
        copy[it.key] = tempArr
    }

    println(part1(stacks, moves))
    println(part2(copy, moves))
}
