fun main() {
    fun solve(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val digits = mutableListOf<String>()
            for (c in line) {
                val digit = c.digitToIntOrNull()
                if (digit != null) {
                    digits.add(c.toString())
                }
            }
            val number = if (digits.size > 1) {
                Integer.parseInt(digits.first() + digits.last())
            } else {
                Integer.parseInt(digits[0] + digits[0])
            }
            sum += number
        }


        return sum
    }

    fun part1(input: List<String>): Int {
        return solve(input)
    }



    fun part2(input: List<String>): Int {
        val indexes = input.map {
            mapOf(
                    "1" to it.indexOf("1"),
                    "2" to it.indexOf("2"),
                    "3" to it.indexOf("3"),
                    "4" to it.indexOf("4"),
                    "5" to it.indexOf("5"),
                    "6" to it.indexOf("6"),
                    "7" to it.indexOf("7"),
                    "8" to it.indexOf("8"),
                    "9" to it.indexOf("9"),
                    "one" to it.indexOf("one"),
                    "two" to it.indexOf("two"),
                    "three" to it.indexOf("three"),
                    "four" to it.indexOf("four"),
                    "five" to it.indexOf("five"),
                    "six" to it.indexOf("six"),
                    "seven" to it.indexOf("seven"),
                    "eight" to it.indexOf("eight"),
                    "nine" to it.indexOf("nine"),
            ).filter { (_, v) -> v != -1 }

        }
        val lastIndexes = input.map {
            mapOf(
                    "1" to it.lastIndexOf("1"),
                    "2" to it.lastIndexOf("2"),
                    "3" to it.lastIndexOf("3"),
                    "4" to it.lastIndexOf("4"),
                    "5" to it.lastIndexOf("5"),
                    "6" to it.lastIndexOf("6"),
                    "7" to it.lastIndexOf("7"),
                    "8" to it.lastIndexOf("8"),
                    "9" to it.lastIndexOf("9"),
                    "one" to it.lastIndexOf("one"),
                    "two" to it.lastIndexOf("two"),
                    "three" to it.lastIndexOf("three"),
                    "four" to it.lastIndexOf("four"),
                    "five" to it.lastIndexOf("five"),
                    "six" to it.lastIndexOf("six"),
                    "seven" to it.lastIndexOf("seven"),
                    "eight" to it.lastIndexOf("eight"),
                    "nine" to it.lastIndexOf("nine"),
            ).filter { (_, v) -> v != -1 }
        }


        fun stringToInt(string: String): Int = when (string) {
            "one" -> 1
            "two" -> 2
            "three" -> 3
            "four" -> 4
            "five" -> 5
            "six" -> 6
            "seven" -> 7
            "eight" -> 8
            "nine" -> 9
            else -> Integer.parseInt(string)
        }
        return indexes.indices.sumOf { i ->
            val firstDigit = stringToInt(indexes[i].entries.minBy { (_, v) -> v }.key)
            val lastDigit = stringToInt(lastIndexes[i].entries.maxBy { (_, v) -> v }.key)
            Integer.parseInt("$firstDigit$lastDigit")
        }

    }

    val testInput = readInput("01_test")
    println(part1(testInput))
    check(part1(testInput) == 142)
    val input = readInput("01")
    println(part1(input))

    val testInput2 = readInput("01_test_2")
    println(part2(testInput2))
    check(part2(testInput2) == 281)

    println(part2(input))
}