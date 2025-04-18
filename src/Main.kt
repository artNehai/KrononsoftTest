data class Workplace(
    val x: Int,
    val y: Int,
)

enum class Direction(val symbol: Char) {
    UP('U'),
    RIGHT('R'),
    DOWN('D'),
    LEFT('L'),
}

class Round(
    val startingPosition: Workplace,
) {
    private val workplaceToVisitsMap = mutableMapOf(startingPosition to 1)
    private var currentPositionX = startingPosition.x
    private var currentPositionY = startingPosition.y

    fun start() {
        print("Please enter the path: ")
        val path = readlnOrNull()
            ?.uppercase()
            ?.filter { direction -> direction in validDirections }
            ?.take(MAX_PATH_LENGTH)
            ?: return

        for (direction in path) {
            when (direction) {
                Direction.UP.symbol -> currentPositionY++
                Direction.DOWN.symbol -> currentPositionY--
                Direction.LEFT.symbol -> currentPositionX--
                Direction.RIGHT.symbol -> currentPositionX++
            }

            val newDestination = Workplace(currentPositionX, currentPositionY)
            workplaceToVisitsMap[newDestination] = (workplaceToVisitsMap[newDestination] ?: 0) + 1
        }

        val result = workplaceToVisitsMap.count { it.value > 1 }
        println("Number of workplaces visited more than once: $result")
    }

    companion object {
        private const val MAX_PATH_LENGTH = 1000
        private val validDirections = Direction.entries.map { it.symbol }
    }
}

fun main() {

    println("Choose the starting position:")
    val x = readInt("x = ")
    val y = readInt("y = ")

    val round = Round(startingPosition = Workplace(x, y))
    round.start()
}

fun readInt(accompanyingText: String = ""): Int {
    print(accompanyingText)
    while (true) {
        try {
            return readln().toInt()
        } catch (e: NumberFormatException) {
            print("Invalid value. Please enter an integer value:")
        }
    }
}