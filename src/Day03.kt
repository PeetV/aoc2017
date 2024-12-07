import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val inputValue = input.first().toInt()
        var direction = Direction.East
        // position format: sequence position, x position, y position
        var position = Triple(1, 0, 0)
        // spaceX, spaceY format: x min and max, y min and max
        var spaceX = -1 to 1
        var spaceY = -1 to 1
        while (position.first < inputValue) {
            // Update limits and direction
            if (direction == Direction.East && position.second == spaceX.second) {
                direction = Direction.North
                spaceX = spaceX.first to (spaceX.second + 1)
            }
            if (direction == Direction.West && position.second == spaceX.first) {
                direction = Direction.South
                spaceX = (spaceX.first - 1) to spaceX.second
            }
            if (direction == Direction.North && position.third == spaceY.second) {
                direction = Direction.West
                spaceY = spaceY.first to (spaceY.second + 1)
            }
            if (direction == Direction.South && position.third == spaceY.first) {
                direction = Direction.East
                spaceY = (spaceY.first - 1) to spaceY.second
            }
            // Update position
            when (direction) {
                Direction.North -> position = Triple(position.first + 1, position.second, position.third + 1)
                Direction.South -> position = Triple(position.first + 1, position.second, position.third - 1)
                Direction.West -> position = Triple(position.first + 1, position.second - 1, position.third)
                Direction.East -> position = Triple(position.first + 1, position.second + 1, position.third)
                else -> position = Triple(position.first + 1, position.second, position.third + 1)
            }
        }
        return abs(position.second) + abs(position.third)
    }

    fun part2(input: List<String>): Int {
        val inputValue = input.first().toInt()
        var cache: MutableMap<String, Int> = listOf("0:0" to 1).toMap().toMutableMap()
        var direction = Direction.East
        var position = 0 to 0
        var spaceX = -1 to 1
        var spaceY = -1 to 1
        var positionValue = 0
        while (positionValue <= inputValue) {
            // Update limits and direction
            if (direction == Direction.East && position.first == spaceX.second) {
                direction = Direction.North
                spaceX = spaceX.first to (spaceX.second + 1)
            }
            if (direction == Direction.West && position.first == spaceX.first) {
                direction = Direction.South
                spaceX = (spaceX.first - 1) to spaceX.second
            }
            if (direction == Direction.North && position.second == spaceY.second) {
                direction = Direction.West
                spaceY = spaceY.first to (spaceY.second + 1)
            }
            if (direction == Direction.South && position.second == spaceY.first) {
                direction = Direction.East
                spaceY = (spaceY.first - 1) to spaceY.second
            }
            // Update position
            position = when (direction) {
                Direction.North -> position.first to (position.second + 1)
                Direction.South -> position.first to (position.second - 1)
                Direction.West -> (position.first - 1) to position.second
                Direction.East -> (position.first + 1) to position.second
                else -> position.first to (position.second + 1)
            }
            positionValue = cache.getOrDefault(
                "${position.first - 1}:${position.second}",
                0
            ) + cache.getOrDefault(
                "${position.first + 1}:${position.second}",
                0
            ) + cache.getOrDefault(
                "${position.first}:${position.second - 1}",
                0
            ) + cache.getOrDefault(
                "${position.first}:${position.second + 1}",
                0
            ) + cache.getOrDefault(
                "${position.first - 1}:${position.second + 1}",
                0
            ) + cache.getOrDefault(
                "${position.first + 1}:${position.second + 1}",
                0
            ) + cache.getOrDefault(
                "${position.first - 1}:${position.second - 1}",
                0
            ) + cache.getOrDefault("${position.first + 1}:${position.second - 1}", 0)
            cache["${position.first}:${position.second}"] = positionValue
            // println("$position - $positionValue - $spaceX - $spaceY - $cache")
        }
        return positionValue
    }

    // Test input from the `src/Day03_test.txt` file
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 31)

    // Input from the `src/Day03.txt` file
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()

}