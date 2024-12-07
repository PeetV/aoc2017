fun main() {

    fun part1(input: List<String>): Int {
        val adjusted = input.first().split("\\s+".toRegex()).map { it.trim().toInt() }
        var count = 0
        var banks = adjusted.toMutableList()
        var seen: MutableSet<String> = mutableSetOf(banks.joinToString(""))
        var notSeenBefore = true
        while (notSeenBefore) {
            val maxIndex = banks.indexOf(banks.max())
            var maxValue = banks[maxIndex]
            banks[maxIndex] = 0
            var index = if (maxIndex == banks.lastIndex) 0 else maxIndex + 1
            while (maxValue > 0) {
                banks[index] = banks[index] + 1
                maxValue -= 1
                index = if (index == banks.lastIndex) 0 else index + 1
            }
            count += 1
            val nextKey = banks.joinToString("")
            if (seen.contains(nextKey)) notSeenBefore = false
            else seen.add(nextKey)
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val adjusted = input.first().split("\\s+".toRegex()).map { it.trim().toInt() }
        var count = 0
        var banks = adjusted.toMutableList()
        var seen: MutableMap<String, Int> = listOf(banks.joinToString("") to 0).toMap().toMutableMap()
        var notSeenBefore = true
        while (notSeenBefore) {
            val maxIndex = banks.indexOf(banks.max())
            var maxValue = banks[maxIndex]
            banks[maxIndex] = 0
            var index = if (maxIndex == banks.lastIndex) 0 else maxIndex + 1
            while (maxValue > 0) {
                banks[index] = banks[index] + 1
                maxValue -= 1
                index = if (index == banks.lastIndex) 0 else index + 1
            }
            count += 1
            val nextKey = banks.joinToString("")
            if (seen.keys.contains(nextKey)) {
                notSeenBefore = false
                return count - seen[nextKey]!!
            } else seen[nextKey] = count
        }
        return count
    }

    // Input from the `src/Day06.txt` file
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()

}