# Условие задачи
Офис компании представляет собой бесконечную двумерную таблицу-опенспейс, разделенную на единичные квадраты. В каждом таком квадрате за столом сидит разработчик и усердно создает продукт. Господин директор любит выбрать какой-нибудь квадрат и начиная с него ходить по офису и хвалить разработчиков, в том числе разработчика в том квадрате, с которого он начал обход. Проходя через какой-то квадрат он обязательно хвалит разработчика, который в нём работает. 

Директор может двигаться в четырех направлениях U (вверх), R (вправо), D (вниз) и L (влево), перемещаясь в один из соседних квадратов.

По маршруту директора определите количество разработчиков, которых директор похвалит более одного раза.

# Формат ввода
В Единственной строке задается маршрут директора — строка, состоящая не более чем из 1000 символов U, R, D или L.

# Примеры входных данных
RRRDDDLLLUUU  Ожидаемый вывод: 1

RRULLD  Ожидаемый вывод: 1

RrUUlLDDR Ожидаемый вывод: 2


Решение
==========
```kotlin
class Workplace(
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
```
