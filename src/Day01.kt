fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach {
            val first = it.find { char -> char.isDigit() }.toString()
            val last = it.findLast { char -> char.isDigit() }
            sum += (first + last).toInt()
        }
        return sum
    }

    val stringToNumber =
        mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9",
        )

    val numbers = stringToNumber.values + stringToNumber.keys

    fun convertStringToNumber(p: Pair<Int, String>?) =
        if (p!!.second in stringToNumber) {
            stringToNumber[p.second]
        } else {
            p.second
        }

    fun part2(input: List<String>): Int {
        var sum = 0
        input.forEach { line ->
            val first = convertStringToNumber(line.findAnyOf(numbers))
            val last = convertStringToNumber(line.findLastAnyOf(numbers))
            sum += (first + last).toInt()
        }
        return sum
    }

    val input = readInput("data/DAY01")
    part1(input).println()
    part2(input).println()
}
