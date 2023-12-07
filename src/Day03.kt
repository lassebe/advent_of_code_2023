fun main() {

    fun checkSquare(square: Char): Boolean = !square.isDigit() && square != '.'
    fun checkNeighbours(input: List<String>, i: Int, j: Int): Boolean {
        if (i > 0) {
            if (checkSquare(input[i - 1][j])) return true
            if (j > 0 && checkSquare(input[i - 1][j - 1])) return true
            if (j < input.size - 1 && checkSquare(input[i - 1][j + 1])) return true
        }
        if (i < input.size - 1) {
            if (checkSquare(input[i + 1][j])) return true
            if (j > 0 && checkSquare(input[i + 1][j - 1])) return true
            if (j < input.size - 1 && checkSquare(input[i + 1][j + 1])) return true
        }
        if (j > 0) {
            if (checkSquare(input[i][j - 1])) return true
        }
        if (j < input.size - 1) {
            if (checkSquare(input[i][j + 1])) return true
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val partNumbers = mutableListOf<Int>()
        for (i in input.indices) {
            var y = 0
            while (y < input.size) {
                var partNumber = false
                var num = ""
                if (input[i][y].isDigit()) {
                    while (y < input.size && input[i][y].isDigit()) {
                        if (checkNeighbours(input, i, y)) {
                            partNumber = true
                        }
                        num += input[i][y]
                        y += 1
                    }
                    if (partNumber) partNumbers.add(Integer.parseInt(num))
                }
                y += 1
            }
        }
        return partNumbers.sum()
    }


    fun checkSquareIsGear(square: Char): Boolean = square == '*'

    data class AdjacentNumber(val number: Int, val range: Pair<Int, Int>)

    fun findNumbers(line: String): List<AdjacentNumber> {
        val numbers = mutableListOf<AdjacentNumber>()
        var j = 0
        while (j < line.length) {
            var num = ""
            if (line[j].isDigit()) {
                val start = j
                while (j < line.length && line[j].isDigit()) {
                    num += line[j]
                    j += 1
                }
                val end = j - 1
                numbers.add(AdjacentNumber(Integer.parseInt(num), Pair(start, end)))
            }
            j += 1
        }
        return numbers
    }

    fun part2(input: List<String>): Int {
        val gearNumbers = mutableListOf<Int>()
        for (i in input.indices) {
            var y = 0
            while (y < input.size) {
                if (checkSquareIsGear(input[i][y])) {
                    val numbers = findNumbers(input[i]).toMutableList()
                    if (i > 0) {
                        numbers += findNumbers(input[i - 1])
                    }
                    if (i < input.size - 1) {
                        numbers += findNumbers(input[i + 1])
                    }
                    val adjNumbers = numbers.filter {
                        ((y - 1..y + 1).contains(it.range.first) or (y - 1..y + 1).contains(it.range.second))
                    }
                    if (adjNumbers.size == 2) {
                        gearNumbers.add(adjNumbers[0].number * adjNumbers[1].number)
                    }
                }
                y += 1
            }
        }
        return gearNumbers.sum()
    }

    val testInput = readInput("03_test")
    println(part1(testInput))
    check(part1(testInput) == 4361)
    val input = readInput("03")
    println(part1(input))

    println(part2(testInput))
    check(part2(testInput) == 467835)

    println(part2(input))
}