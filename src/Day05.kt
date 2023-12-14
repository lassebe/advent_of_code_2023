import kotlin.math.abs
import kotlin.math.min

fun main() {

    data class Mapping(val target: Long, val source: Long, val range: Long)
    data class Puzzle(
        val seeds: List<Long>,
        val sts: List<Mapping>,
        val stf: List<Mapping>,
        val ftw: List<Mapping>,
        val wtl: List<Mapping>,
        val ltt: List<Mapping>,
        val tth: List<Mapping>,
        val htl: List<Mapping>
    )

    fun lookup(source: Long, mappings: List<Mapping>): Long {
        for (map in mappings) {
            if (source >= map.source && source < map.source + map.range) {
                val rangeIndex = abs(map.source - source)
                return map.target + rangeIndex
            }
        }
        return source
    }

    fun parse(input: List<String>): Puzzle {
        val seeds = input[0].split(":")[1].trim().split(" ").map { it.toLong() }

        val maps = input.subList(3, input.size)
        var tmp = mutableListOf<Mapping>()
        val mappings = mutableListOf<List<Mapping>>()
        for (line in maps) {
            if (line == "") continue
            if (line.contains("map:")) {
                mappings.add(tmp.toList())
                tmp = mutableListOf<Mapping>()
                continue
            }
            val numbers = line.split(" ").map { it.toLong() }
            tmp.add(Mapping(numbers[0], numbers[1], numbers[2]))
        }
        mappings.add(tmp.toList())

        return Puzzle(
            seeds,
            mappings[0],
            mappings[1],
            mappings[2],
            mappings[3],
            mappings[4],
            mappings[5],
            mappings[6],
        )
    }

    fun location(seed: Long, p: Puzzle): Long {
        val soil = lookup(seed, p.sts)
        val fertilizer = lookup(soil, p.stf)
        val water = lookup(fertilizer, p.ftw)
        val light = lookup(water, p.wtl)
        val temperature = lookup(light, p.ltt)
        val humidity = lookup(temperature, p.tth)
        return lookup(humidity, p.htl)
    }

    fun part1(input: List<String>): Long {
        val p = parse(input)
        var solution = Long.MAX_VALUE
        for (seed in p.seeds) {
            solution = min(location(seed, p), solution)
        }
        return solution
    }

    fun part2(input: List<String>): Long {
        val p = parse(input)
        var solution = Long.MAX_VALUE
        var i = 0
        while (i < p.seeds.size - 2) {
            val seedRange = (p.seeds[i] until p.seeds[i] + p.seeds[i + 1])
            for (seed in seedRange) {
                val location = location(seed, p)
                solution = min(location, solution)
            }
            i += 2
        }

        return solution
    }

    val testInput = readInput("05_test")
    println(part1(testInput))
    check(part1(testInput) == 35L)
    val input = readInput("05")
    println(part1(input))

    println(part2(testInput))
    check(part2(testInput) == 46L)

    println(part2(input))
}