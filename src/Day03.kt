fun main() {
    val directions = arrayOf(
        Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
        Pair(0, -1), Pair(0, 1),
        Pair(1, -1), Pair(1, 0), Pair(1, 1)
    )

    val foundMatrix = mutableMapOf<String, List<Int>>()
    fun part1(matrix: List<List<Char>>): Int {
        var sum = 0
        var numberString = ""

        println("size: [${matrix.size}][${matrix[0].size}]")
        matrix.forEachIndexed { i, _ ->
            var searched = false
            var isStar = ""
            matrix[i].forEachIndexed { j, jChar ->
                if(jChar == '.' || !jChar.isDigit()) {
                    if (searched) {
                        sum += numberString.toInt()
                        println(numberString)
                        searched = false

                        if(isStar.isNotBlank()) {
                            foundMatrix[isStar] = (foundMatrix[isStar] ?: emptyList()) + listOf(numberString.toInt())
                        }
                        isStar = ""
                    }
                    numberString = ""
                    return@forEachIndexed
                }
                numberString += jChar
                directions.forEach { direction ->
                    val newI = i + direction.first
                    val newJ = j + direction.second

                    if(newI < 0 || newJ < 0) {
                        return@forEach
                    }
                    if (newI < matrix.size && newJ < matrix[i].size) {
                        val searchValue = matrix[newI][newJ]
                        if(searchValue != '.' && !searchValue.isDigit()) {
                            searched = true
                            if(searchValue == '*') {
                                isStar = "[$newI][$newJ]"
                            }
                        }
                    }
                }
            }

            if(searched) {
                sum += numberString.toInt()
                searched = false
                if(isStar.isNotBlank()) {
                    foundMatrix[isStar] = (foundMatrix[isStar] ?: emptyList()) + listOf(numberString.toInt())
                }
                isStar = ""
                numberString = ""
            }
        }

        return sum
    }

    fun part2(): Int {
        var sum = 0
        foundMatrix.forEach { key, value ->
            if(value.size > 1) {
                sum += value.reduce { acc, s -> acc * s }
            }
        }
        return sum
    }

    val input = readInput("DAY03")
    val matrix: List<List<Char>> = input.map { line -> line.map { it } }

    println("Part 1: ${part1(matrix)}")
    println("Part 2: ${part2()}")
}
