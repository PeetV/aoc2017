fun main() {

    fun processRegisters(input: List<String>): Pair<Map<String, Int>, Int> {
        val result: MutableMap<String, Int> = mutableMapOf()
        var maxRegister = 0
        for (line in input) {
            if (line.startsWith("inc") || line.startsWith("dec")) throw Exception("Invalid register")
            val (lhs, rhs) = line.split("if")
            val operation = if (lhs.contains("inc")) "inc" else "dec"
            val (lLhs, lRhs) = lhs.split(operation)
            val register = lLhs.trim()
            val byValue = lRhs.trim().toInt()
            val condition = when {
                rhs.contains("> ") -> "> "
                rhs.contains("< ") -> "< "
                rhs.contains(">=") -> ">="
                rhs.contains("<=") -> "<="
                rhs.contains("==") -> "=="
                rhs.contains("!=") -> "!="
                else -> throw Exception("Invalid condition")
            }
            val (rLhs, rRhs) = rhs.split(condition)
            val checkRegister = rLhs.trim()
            val checkValue = rRhs.trim().toInt()
            val conditionTrue = when (condition) {
                "> " -> result.getOrElse(checkRegister) { 0 } > checkValue
                "< " -> result.getOrElse(checkRegister) { 0 } < checkValue
                ">=" -> result.getOrElse(checkRegister) { 0 } >= checkValue
                "<=" -> result.getOrElse(checkRegister) { 0 } <= checkValue
                "==" -> result.getOrElse(checkRegister) { 0 } == checkValue
                "!=" -> result.getOrElse(checkRegister) { 0 } != checkValue
                else -> false
            }
            if (conditionTrue) {
                result[register] = when(operation) {
                    "inc" -> result.getOrElse(register) { 0 } + byValue
                    "dec" -> result.getOrElse(register) { 0 } - byValue
                    else -> result.getOrElse(register) { 0 }
                }
            }
            val iterationNax = result.maxByOrNull { it.value }?.value ?: 0
            if (iterationNax > maxRegister) maxRegister = iterationNax
        }
        return result.toMap() to maxRegister
    }

    fun part1(input: List<String>): Int {
        val registers = processRegisters(input)
        return registers.first.maxBy { it.value }.value
    }

    fun part2(input: List<String>): Int {
        val registers = processRegisters(input)
        return registers.second
    }

    // Test input from the `src/Day08_test.txt` file
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 1)

    // Input from the `src/Day08.txt` file
    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()

}