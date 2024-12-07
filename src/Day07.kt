fun main() {

    fun buildGraph(input: List<String>): NodeMappedGraph<String, Double> {
        val graph = NodeMappedGraph<String, Double>()
        input.forEach {
            val (lhs, _) = it.split(" (")
            graph.addNode(lhs.trim())
        }
        input.filter { it.contains("->") }.forEach {
                val (lhs, rhs) = it.split(" (")
                val (lRhs, rRhs) = rhs.split("->")
                val rRhsLst = rRhs.split(",")
                rRhsLst.forEach { item ->
                    graph.addEdge(lhs.trim(), item.trim(), 1.0)
                }
            }
        return graph
    }

    fun part1(input: List<String>): String {
        val graph = buildGraph(input)
        return graph.nodes.first {
            graph.parentNodes(it).getOrThrow().count() == 0
        }
    }

    fun part2(input: List<String>): Int {
        val graph = buildGraph(input)
        val programValues = input.associate { line ->
            val (lhs, _) = line.split(" (")
            lhs.trim() to Regex("-?\\d+").findAll(line).map { it.value }.toList().first().toInt()
        }
        val towers = graph.childNodes("ykpsek").getOrDefault(listOf())
        val maxLevel1 = towers.map {
            val sumTower =
                graph.descendants(it).getOrDefault(listOf()).sumOf { n -> programValues[n]!! } + programValues[it]!!
            it to sumTower
        }.maxByOrNull { it.second }!!.first
        val maxLevel2 = graph.childNodes(maxLevel1).getOrDefault(listOf()).map {
            val sumTower =
                graph.descendants(it).getOrDefault(listOf()).sumOf { n -> programValues[n]!! } + programValues[it]!!
            it to sumTower
        }.maxByOrNull { it.second }!!.first
        val maxLevel3 = graph.childNodes(maxLevel2).getOrDefault(listOf()).map {
            val sumTower =
                graph.descendants(it).getOrDefault(listOf()).sumOf { n -> programValues[n]!! } + programValues[it]!!
            it to sumTower
        }.maxByOrNull { it.second }!!.first
        return programValues[maxLevel3]!! - 9
    }

    // Test input from the `src/Day07_test.txt` file
    val testInput = readInput("Day07_test")
    check(part1(testInput) == "tknk")

    // Input from the `src/Day07.txt` file
    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()

}