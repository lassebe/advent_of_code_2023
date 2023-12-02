import java.lang.Integer.max

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
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
            if (maxRed <= 12 && maxGreen <= 13 && maxBlue <= 14) {
                sum += gameId
            }
        }
        return sum
    }



    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            var maxRed = 0
            var maxGreen = 0
            var maxBlue = 0
            val l = line.split(": ")

            val rounds = l[1].split(";")
            for (round in rounds){
                val pulls = round.split(",")
                for (pull in pulls) {
                    val x = pull.trim().split(" ")
                    val count = Integer.parseInt(x[0])
                    when (x[1]){
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
            val power = maxRed * maxBlue * maxGreen
            sum += power
        }


        return sum
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