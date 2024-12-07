fun main() {

    fun part1(input: List<String>): Int {
        return input.map { it.split("\\s+".toRegex()) }.map { it.map { x -> x.toInt() } }.sumOf { it.max() - it.min() }
    }

    fun processPart2(input: List<Int>): Int {
        val combos = input.combinations(2)
        for (combo in combos) {
            if (combo[0] % combo[1] == 0) return combo[0] / combo[1]
            if (combo[1] % combo[0] == 0) return combo[1] / combo[0]
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split("\\s+".toRegex()) }.map { it.map { x -> x.toInt() } }.sumOf { processPart2(it) }

    }

    // Test input from the `src/Day02_test.txt` file
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 18)

    // Input from the `src/Day02.txt` file
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()

}