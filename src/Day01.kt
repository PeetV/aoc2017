fun main() {

    fun part1(input: List<String>): Int {
        val adjustedInput = "${input.first()}${input.first().first()}"
        return adjustedInput.toCharArray().map { it.toString().toInt() }.zipWithNext()
            .sumOf { if (it.first == it.second) it.first else 0 }
    }

    fun part2(input: List<String>): Int {
        var total = 0
        val adjusted = input.first()
        val length = adjusted.count()
        val mid = length / 2
        for (index in adjusted.indices) {
            val char = adjusted[index]
            val nextIndex = index + mid
            val nextChar = if (nextIndex > adjusted.lastIndex) adjusted[nextIndex - length]
            else adjusted[nextIndex]
            if (char == nextChar) total += char.toString().toInt()
        }
        return total
        return input.size
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 9)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()

}
