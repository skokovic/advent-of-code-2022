package day07

import readInput

data class FileNode(val name: String, val parent: FileNode? = null, val children: MutableList<FileNode> = mutableListOf(), var size: Int = 0)

fun createFileTree(lines: List<String>): List<FileNode> {
    val root = FileNode("/")
    val nodes = mutableListOf(root)
    var current = root

    lines.drop(1).forEach {
        when {
            it.startsWith("$ cd") -> {
                val name = it.drop(5) // "$ cd name"
                current = if (name == "..") current.parent!! else current.children.find{ it.name == name}!!
            }

            it.startsWith("dir") -> {
                val name = it.drop(4) // "dir name"
                val child = FileNode(name, current)
                nodes.add(child)
                current.children.add(child)
            }

            it.first().isDigit() -> {
                val size = it.split(" ").first().toInt() // "100 file.txt"
                current.size += size
                var parent = current.parent
                while (parent != null) {
                    parent.size += size
                    parent = parent.parent
                }
            }
        }
    }
    return nodes
}

fun calcSize(node: FileNode): Int {
    if (node.children.isEmpty()) {
        return node.size
    }
    node.size = node.size + node.children.map { calcSize(it) }.sum()
    return node.size
}


fun main() {

    fun part1(nodes: List<FileNode>): Int {
        return nodes.filter { it.size <= 100000 }.sumOf { it.size }
    }

    fun part2(nodes: List<FileNode>): Int  {
        val free = 70000000 - nodes.find { it.name == "/" }!!.size // root
        return nodes.filter { it.size >= 30000000 - free }.minOf { it.size }
    }

    val input = readInput("Day07")
    val nodes = createFileTree(input)

    println(part1(nodes))
    println(part2(nodes))
}
