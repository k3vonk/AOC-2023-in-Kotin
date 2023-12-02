fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        input.forEach {
            val game =
                it.substringBefore(":")
                    .substringAfter("Game ")
                    .toInt()
            val colors =
                it.substringAfter(": ")
                    .replace(";", ",")
                    .split(",")
            val c = mutableMapOf("blue" to 14, "green" to 13, "red" to 12)

            var b = true
            colors.forEach { p ->
                val pair =
                    p.trim()
                        .partition { s -> s.isDigit() }

                if (pair.first.toInt() > c[pair.second.trim()]!!) {
                    b = false
                    return@forEach
                }
            }

            if (b) {
                sum += game
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        input.forEach {
            val colors =
                it.substringAfter(": ")
                    .replace(";", ",")
                    .split(",")
            val c = mutableMapOf("blue" to 0, "green" to 0, "red" to 0)

            colors.forEach { p ->
                val pair =
                    p.trim()
                        .partition { s -> s.isDigit() }

                if (pair.first.toInt() > c[pair.second.trim()]!!) {
                    c[pair.second.trim()] = pair.first.toInt()
                }
            }

            val p = c["green"]!! * c["blue"]!! * c["red"]!!
            sum += p
        }
        return sum
    }

    val input = readInput("data/DAY02")
    part1(input).println()
    println("Part 2")
    part2(input).println()
}
