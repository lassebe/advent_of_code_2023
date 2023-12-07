import kotlin.math.pow

fun main() {
    data class Card(val winningNumbers: List<Int>, val lottoNumbers: List<Int>)

    fun parse(input: List<String>): List<Card> = input.map { line ->
        val s = line.substring(line.indexOf(":") + 2).split(" | ")
        val winningNumbers = s[0].trim().split("\\s+".toRegex()).map { Integer.parseInt(it) }
        val lottoNumbers = s[1].trim().split("\\s+".toRegex()).map { Integer.parseInt(it) }
        Card(winningNumbers, lottoNumbers)
    }

    fun part1(input: List<String>): Int = parse(input).sumOf { card ->
        val overlap = card.winningNumbers.toSet().filter { card.lottoNumbers.contains(it) }
        if (overlap.isNotEmpty()) 2.toDouble().pow((overlap.size - 1)).toInt()
        else 0
    }


    fun part2(input: List<String>): Int {
        val cards = parse(input)
        val wins = mutableMapOf<Int, Int>()
        for (i in cards.indices) {
            wins[i] = 1
        }
        for (i in cards.indices) {
            val overlap = cards[i].winningNumbers.toSet().filter { cards[i].lottoNumbers.contains(it) }
            for (j in overlap.indices) {
                wins[i + j + 1] = wins.getValue(i + j + 1) + wins.getValue(i)
            }
        }
        return wins.values.sum()
    }

    val testInput = readInput("04_test")
    println(part1(testInput))
    check(part1(testInput) == 13)
    val input = readInput("04")
    println(part1(input))

    println(part2(testInput))
    check(part2(testInput) == 30)

    println(part2(input))
}