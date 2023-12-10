fun main() {
    fun List<String>.extractTime() = this[0].substringAfter("Time:")
        .trim()
        .split(" ")
        .filter { it.isNotBlank() }
        .map { it.toInt() }

    fun List<String>.extractDistance() = this[1].substringAfter(
        "Distance:",
    ).trim().split(" ").filter {
        it.isNotBlank()
    }.map { it.toInt() }

    fun part1(input: List<String>): Int {
        val time = input.extractTime()
        val distance = input.extractDistance()

        var product = 1
        time.forEachIndexed { j,  t ->
            println("Time: $t")
            var ways = 0
            for (i in 1..t) {
                val res = t - i
                val d = res * i
                if (d > distance[j]) {
                    println("speed $i and distance $d")
                    ways += 1
                }
            }
            product *= ways
        }
        return product
    }

    fun part2(input: List<String>): Int {
        val time = input[0].substringAfter("Time:")
            .trim()
            .filter {
                it != ' '
            }.toLong()
        val distance = input[1].substringAfter("Distance:")
            .trim()
            .filter {
                it != ' '
            }.toLong()

        println("Time: $time")
        var ways = 0
        for (i in 1..time) {
            val res = time - i
            val d = res * i
            if (d > distance) {
//                println("speed $i and distance $d")
                ways += 1
            }
        }
        return  ways
    }

    /** ===============                                    ===============                                    ===============                                    */
    val input = readInput("DAY06")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
