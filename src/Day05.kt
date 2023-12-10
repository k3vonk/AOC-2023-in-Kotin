fun main() {
    fun oneToTwoMap(input: List<String>, start: Int, end: Int, seeds: MutableList<Long>) {
        val soilToFertilizerList =
            input.slice(start + 1..<end - 1)
                .map { it.split(" ").map { it.toLong() } }

        seeds.forEachIndexed { index, seed ->
            soilToFertilizerList.forEach {
                val (destination, source, range) = it
                if (seed in source..<source + range) {
                    val movement = (destination - source)
                    seeds[index] = seed + movement
                    return@forEachIndexed
                }
            }
        }
    }

    fun part1(input: List<String>): Long {
        var seeds =
            input[0].substringAfter("seeds: ")
                .split(" ")
                .map { it.toLong() }
                .toMutableList()
        println(seeds)

        val seedToSoilStart = input.indexOf("seed-to-soil map:")
        val soilToFertilizerStart = input.indexOf("soil-to-fertilizer map:")
        val fertilizerToWaterStart = input.indexOf("fertilizer-to-water map:")
        val waterToLightStart = input.indexOf("water-to-light map:")
        val lightToTemporatureStart = input.indexOf("light-to-temperature map:")
        val temperatureToHumidityStart = input.indexOf("temperature-to-humidity map:")
        val humidityToLocationStart = input.indexOf("humidity-to-location map:")

        oneToTwoMap(input, seedToSoilStart, soilToFertilizerStart, seeds)
        println(seeds)
        oneToTwoMap(input, soilToFertilizerStart, fertilizerToWaterStart, seeds)
        println(seeds)
        oneToTwoMap(input, fertilizerToWaterStart, waterToLightStart, seeds) // fertilizer to water
        println(seeds)
        oneToTwoMap(input, waterToLightStart, lightToTemporatureStart, seeds)
        println(seeds)
        oneToTwoMap(input, lightToTemporatureStart, temperatureToHumidityStart, seeds)
        println(seeds)
        oneToTwoMap(input, temperatureToHumidityStart, humidityToLocationStart, seeds)
        println(seeds)
        oneToTwoMap(input, humidityToLocationStart, input.size + 1, seeds)
        println(seeds)
        return seeds.min()
    }

    fun part2(input: List<String>): Long {
        var s =
            input[0].substringAfter("seeds: ")
                .split(" ")
                .map { it.toLong() }
                .toMutableList()
        println(s)
        var seeds: MutableList<Long> = mutableListOf()
        for (i in 1..s.size - 1 step 2) {
            seeds += (s[i - 1]..<s[i - 1] + s[i]).toMutableList()
        }
        println(seeds)

        val seedToSoilStart = input.indexOf("seed-to-soil map:")
        val soilToFertilizerStart = input.indexOf("soil-to-fertilizer map:")
        val fertilizerToWaterStart = input.indexOf("fertilizer-to-water map:")
        val waterToLightStart = input.indexOf("water-to-light map:")
        val lightToTemporatureStart = input.indexOf("light-to-temperature map:")
        val temperatureToHumidityStart = input.indexOf("temperature-to-humidity map:")
        val humidityToLocationStart = input.indexOf("humidity-to-location map:")

        oneToTwoMap(input, seedToSoilStart, soilToFertilizerStart, seeds)
        println(seeds)
        oneToTwoMap(input, soilToFertilizerStart, fertilizerToWaterStart, seeds)
        println(seeds)
        oneToTwoMap(input, fertilizerToWaterStart, waterToLightStart, seeds) // fertilizer to water
        println(seeds)
        oneToTwoMap(input, waterToLightStart, lightToTemporatureStart, seeds)
        println(seeds)
        oneToTwoMap(input, lightToTemporatureStart, temperatureToHumidityStart, seeds)
        println(seeds)
        oneToTwoMap(input, temperatureToHumidityStart, humidityToLocationStart, seeds)
        println(seeds)
        oneToTwoMap(input, humidityToLocationStart, input.size + 1, seeds)
        println(seeds)
        return seeds.min()
    }

    val input = readInput("DAY05")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
