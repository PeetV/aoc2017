fun main() {

    fun part1(input: List<String>): Int {
        val adjustedInput = input.first().split(",")
        val graph = NodeMappedGraph<String, Double>()
        var currentLocation = XYLocation(0, 0, null)
        var nextLocation: XYLocation
        var endLocation = ""
        graph.addNode(currentLocation.toString())
        for ((index, move) in adjustedInput.withIndex()) {
            nextLocation = when (move) {
                "n" -> currentLocation.nextLocation(Direction.North)
                "s" -> currentLocation.nextLocation(Direction.South)
                "nw" -> currentLocation.nextLocation(Direction.NorthWest)
                "ne" -> currentLocation.nextLocation(Direction.NorthEast)
                "sw" -> currentLocation.nextLocation(Direction.SouthWest)
                "se" -> currentLocation.nextLocation(Direction.SouthEast)
                else -> currentLocation.nextLocation(Direction.North)
            }
            if (!graph.hasNode(nextLocation.toString())) graph.addNode(nextLocation.toString())
            graph.addEdge(currentLocation.toString(), nextLocation.toString(), 1.0)
            currentLocation = nextLocation
            if (index == adjustedInput.lastIndex) endLocation = currentLocation.toString()
        }
        val path = graph.shortestPathDijkstra(fromNode = "(0, 0)", toNode = endLocation, { it } )
        return path.getOrThrow().count()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Input from the `src/Day11.txt` file
    val input = readInput("Day11")
    part1(input).println()
//    part2(input).println()

}