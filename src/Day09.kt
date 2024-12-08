fun main() {

    fun part1(input: List<String>): Int {
        val adjustedInput = input.first()
        var totalScore = 0
        var groupCount = 0
        var depth = 0
        var inGarbage = false
        var i = 0
        while (i < adjustedInput.length) {
            when {
                adjustedInput[i] == '!' -> i++ // Skip next character
                inGarbage && adjustedInput[i] != '>' -> { /* Ignore garbage content */ }
                adjustedInput[i] == '<' -> inGarbage = true
                adjustedInput[i] == '>' -> inGarbage = false
                adjustedInput[i] == '{' && !inGarbage -> {
                    depth++
                    groupCount++
                }
                adjustedInput[i] == '}' && !inGarbage -> {
                    totalScore += depth
                    depth--
                }
            }
            i++
        }
        return totalScore
    }

    fun part2(input: List<String>): Int {
        val adjustedInput = input.first()
        var totalScore = 0
        var inGarbage = false
        var i = 0
        while (i < adjustedInput.length) {
            when {
                adjustedInput[i] == '!' -> i++ // Skip next character
                inGarbage && adjustedInput[i] != '>' -> { totalScore++ }
                adjustedInput[i] == '<' -> inGarbage = true
                adjustedInput[i] == '>' -> inGarbage = false
                adjustedInput[i] == '{' && !inGarbage -> { }
                adjustedInput[i] == '}' && !inGarbage -> { }
            }
            i++
        }
        return totalScore
    }

    // Input from the `src/Day09.txt` file
    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()

}