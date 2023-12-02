fun main() {
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

    fun Pair<Int, String>.convertStringToNumber() =
        if (second in stringToNumber) {
            stringToNumber[second]
        } else {
            second
        }

    fun part1(input: List<String>) =
        input.sumOf {
            val first = it.find { char -> char.isDigit() }.toString()
            val second = it.findLast { char -> char.isDigit() }.toString()
            (first + second).toInt()
        }

    fun part2(input: List<String>) =
        input.sumOf {
            val first = it.findAnyOf(numbers)!!.convertStringToNumber()
            val second = it.findLastAnyOf(numbers)!!.convertStringToNumber()
            (first + second).toInt()
        }

    val input = readInput("DAY01")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
