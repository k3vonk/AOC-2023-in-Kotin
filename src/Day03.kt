fun main() {
    val directions =
        arrayOf(
            Pair(-1, -1),
            Pair(-1, 0),
            Pair(-1, 1),
            Pair(0, -1),
            Pair(0, 1),
            Pair(1, -1),
            Pair(1, 0),
            Pair(1, 1),
        )

    val foundStars = mutableMapOf<String, List<Int>>()

    fun adjacentSymbolLocation(
        i: Int,
        j: Int,
        matrix: List<List<Char>>,
    ): Pair<Int, Int>? {
        directions.forEach { direction ->
            val newI = i + direction.first
            val newJ = j + direction.second

            // out of bounds
            if (newI < 0 || newJ < 0 || newI >= matrix.size || newJ >= matrix[i].size) {
                return@forEach
            }

            val searchValue = matrix[newI][newJ]
            if (searchValue != '.' && !searchValue.isDigit()) {
                return Pair(newI, newJ)
            }
        }
        return null
    }

    fun updateFoundStars(
        foundStarLocation: String,
        numberString: Int,
    ) {
        if (foundStarLocation.isNotBlank()) {
            foundStars[foundStarLocation] =
                (foundStars[foundStarLocation] ?: emptyList()) + listOf(numberString)
        }
    }

    fun getNumberIfAdjacentToSymbol(
        isAdjacentToSymbol: Boolean,
        foundStarLocation: String,
        numberString: String,
    ) = if (isAdjacentToSymbol) {
        val number = numberString.toInt()
        updateFoundStars(foundStarLocation, number)
        number
    } else {
        0
    }

    fun part1(matrix: List<List<Char>>): Int {
        var sum = 0
        matrix.forEachIndexed { i, _ ->
            var isAdjacentToSymbol = false
            var numberString = ""
            var foundStarLocation = ""

            matrix[i].forEachIndexed { j, char ->
                if (!char.isDigit()) {
                    sum += getNumberIfAdjacentToSymbol(isAdjacentToSymbol, foundStarLocation, numberString)
                    isAdjacentToSymbol = false
                    numberString = ""
                    foundStarLocation = ""
                    return@forEachIndexed
                }

                numberString += char
                val location = adjacentSymbolLocation(i, j, matrix)
                if (location != null) {
                    isAdjacentToSymbol = true
                    if (matrix[location.first][location.second] == '*') {
                        foundStarLocation = "[${location.first}][${location.second}]"
                    }
                }
            }

            sum += getNumberIfAdjacentToSymbol(isAdjacentToSymbol, foundStarLocation, numberString)
        }
        return sum
    }

    fun List<Int>.getProduct() = reduce { acc, s -> acc * s }

    fun part2() =
        foundStars
            .map { (_, values) -> if (values.size > 1) values.getProduct() else 0 }
            .sumOf { it }

    val input = readInput("DAY03")
    val matrix: List<List<Char>> = input.map { line -> line.map { it } }

    println("Part 1: ${part1(matrix)}")
    println("Part 2: ${part2()}")
}
