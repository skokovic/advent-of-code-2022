package day08

import readInput

fun createForest(input: List<String>): List<List<Int>> {
    return input
        .map { it.split("").filter { s -> s.isNotEmpty() }.map(String::toInt).toList() }
        .toList()
}

fun isVisible(forest: List<List<Int>>, i: Int, j: Int): Boolean {
    val elem = forest[i][j]

    return forest.take(i).map { it[j] }.none { it >= elem }
            || forest.takeLast(forest.size - i - 1).map { it[j] }.none { it >= elem }
            || forest[i].take(j).none { it >= elem }
            || forest[i].takeLast(forest.size - j - 1).none { it >= elem }
}

fun countTrees(trees: List<Int>, tree: Int): Int {
    if (trees.none { it >= tree }) {
        return trees.count()
    }
    return trees.takeWhile { it < tree }.count() + 1
}

fun calculateScenicScore(forest: List<List<Int>>, i: Int, j: Int): Int {
    val tree = forest[i][j]

    val up = forest.take(i).map { it[j] }.reversed()
    val down = forest.drop(i + 1).map { it[j] }
    val left = forest[i].take(j).reversed()
    val right = forest[i].drop(j + 1)

    return countTrees(up, tree) * countTrees(down, tree) * countTrees(left, tree) * countTrees(right, tree)
}


fun main() {

    fun part1(forest: List<List<Int>>): Int {
        var count = 0

        for (i in forest.indices) {
            for (j in forest.indices) {
                if (isVisible(forest, i, j)) {
                    count++
                }
            }
        }

        return count
    }

    fun part2(forest: List<List<Int>>): Int {
        var scenicScore = 0

        for (i in forest.indices) {
            for (j in forest.indices) {
                scenicScore = maxOf(scenicScore, calculateScenicScore(forest, i, j))
            }
        }

        return scenicScore
    }

    val input = readInput("Day08")
    val forest = createForest(input)

    println(part1(forest))
    println(part2(forest))
}
