fun main() {

    fun part1(input: List<String>): Int {
        val adjusted =  input.map { it.trim().toInt() }
        var workingList = adjusted.toMutableList()
        var counter = 0
        var index = 0
        while (index <= workingList.lastIndex) {
            val currentVal = workingList[index]
            workingList[index] = currentVal + 1
            index = index + currentVal
            counter += 1
        }
        return counter
    }

    fun part2(input: List<String>): Int {
        val adjusted =  input.map { it.trim().toInt() }
        var workingList = adjusted.toMutableList()
        var counter = 0
        var index = 0
        while (index <= workingList.lastIndex) {
            val currentVal = workingList[index]
            workingList[index] = if (currentVal < 3) currentVal + 1 else currentVal - 1
            index = index + currentVal
            counter += 1
        }
        return counter
    }

    // Test input from the `src/Day05_test.txt` file
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 10)

    // Input from the `src/Day05.txt` file
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()

}