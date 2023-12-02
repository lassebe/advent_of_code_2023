import java.lang.Integer.max

fun main() {

    data class Game(val gameId: Int, val maxRed: Int, val maxGreen: Int, val maxBlue: Int)

    fun gameResult(line: String): Game {
        var maxRed = 0
        var maxGreen = 0
        var maxBlue = 0
        val l = line.split(": ")
        val gameId = Integer.parseInt(l[0].substring(5))

        val rounds = l[1].split(";")
        for (round in rounds) {
            val pulls = round.split(",")
            for (pull in pulls) {
                val x = pull.trim().split(" ")
                val count = Integer.parseInt(x[0])
                when (x[1]) {
                    "blue" -> {
                        maxBlue = max(maxBlue, count)
                    }

                    "green" -> {
                        maxGreen = max(maxGreen, count)
                    }

                    "red" -> {
                        maxRed = max(maxRed, count)
                    }

                    else -> throw Error("invalid colour")
                }
            }
        }
        return Game(gameId, maxRed, maxGreen, maxBlue)
    }

    fun part1(input: List<String>): Int = input.sumOf { line ->
        val g = gameResult(line)
        if (g.maxRed <= 12 && g.maxGreen <= 13 && g.maxBlue <= 14) {
           g.gameId
        } else {
            0
        }
    }

    fun part2(input: List<String>): Int = input.sumOf { line ->
        val g = gameResult(line)
        g.maxRed * g.maxBlue * g.maxGreen
    }


    val testInput = readInput("02_test")
    println(part1(testInput))
    check(part1(testInput) == 8)
    val input = readInput("02")
    println(part1(input))

    println(part2(testInput))
    check(part2(testInput) == 2286)

    println(part2(input))
}