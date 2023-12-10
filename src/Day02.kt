fun main() {
    fun String.toColorAndCountPairs() = trim()
        .replace(";", ",") // sets do not matter, we only care about the Num - Color.
        .split(",")
        .map {
            val (count, color) = it.trim().split(" ")
            color to count.toInt()
        }

    fun String.possibleGame(): Int {
        val (gameId, rawSets) = split(":")
        val id = gameId.substringAfter("Game ").toInt()

        val colorCountPairs = rawSets.toColorAndCountPairs()
        val isWithinPossibilities =
            colorCountPairs.all { (color, count) ->
                when (color) {
                    "red" -> count <= 12
                    "green" -> count <= 13
                    "blue" -> count <= 14
                    else -> false
                }
            }

        return if (isWithinPossibilities) id else 0
    }

    fun String.powerSet(): Int {
        val rawSets = substringAfter(": ")
        val colorCountPairs = rawSets.toColorAndCountPairs()

        val highestCubes = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        colorCountPairs.forEach { (color, count) ->
            highestCubes[color] = maxOf(highestCubes[color]!!.toInt(), count)
        }

        return highestCubes.values.reduce { acc, i -> acc * i }
    }

    fun part1(input: List<String>) = input.sumOf { it.possibleGame() }

    fun part2(input: List<String>): Int = input.sumOf { it.powerSet() }

    val input = readInput("DAY02")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
