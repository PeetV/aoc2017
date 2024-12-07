fun main() {

    fun part1(input: List<String>): Int {
        return input.map { it.split("\\s+".toRegex()) }.map { if (it.count() == it.distinct().count()) 1 else 0 }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split("\\s+".toRegex()) }
            .map { lst -> lst.map { it.toCharArray().sorted().joinToString("") } }
            .map { if (it.count() == it.distinct().count()) 1 else 0 }.sum()
    }

    // Test input from the `src/Day04_test.txt` file
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)

    // Input from the `src/Day04.txt` file
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()

}