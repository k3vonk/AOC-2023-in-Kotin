fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach {
            val (win, prediction) =
                it.substringAfter(": ")
                    .split(" | ")

            val winList =
                win
                    .replace("  ", " ")
                    .split(" ")
                    .filter { w -> w.isNotBlank() }
                    .map { w ->
                        w.toInt()
                    }

            val predictionList =
                prediction
                    .replace("  ", " ")
                    .split(" ")
                    .filter { w -> w.isNotBlank() }
                    .map { w ->
                        w.toInt()
                    }

            var points = 0
            winList.forEach {
                if (it in predictionList) {
                    if (points == 0) {
                        points += 1
                    } else {
                        points *= 2
                    }
                }
            }

            sum += points
        }

        return sum
    }

    // 1=4
    // 2=2
    // 3=2
    // 4=1
    fun part2(input: List<String>): Int {
        var sum = 0
        val scratchCard = mutableMapOf<Int, Int>()
        input.forEachIndexed { index, s ->
            scratchCard[index + 1] = (scratchCard[index + 1] ?: 0) + 1
            println("start: $scratchCard")

            val (win, prediction) =
                s.substringAfter(": ")
                    .split(" | ")

            val winList =
                win
                    .replace("  ", " ")
                    .split(" ")
                    .filter { w -> w.isNotBlank() }
                    .map { w ->
                        w.toInt()
                    }

            val predictionList =
                prediction
                    .replace("  ", " ")
                    .split(" ")
                    .filter { w -> w.isNotBlank() }
                    .map { w ->
                        w.toInt()
                    }

            var count = 1
            winList.forEach { value ->
                if (value in predictionList) {
                    if (index + 1 + count < input.size + 1) {
                        scratchCard[index + 1 + count] = ((scratchCard[index + 1 + count] ?: 0) + (1 * scratchCard[index + 1]!!))
                        count++
                    }
                }
            }
            println("end: $scratchCard")
            println()
        }

        return scratchCard.values.sumOf { it }
    }

    val input = readInput("DAY04")

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
