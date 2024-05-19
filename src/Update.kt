object Update {
    fun update(snake: Snake, direction: Direction, food: Pair<Double, Double>): Boolean {
        val head = snake.head()
        val newHead = when (direction) {
            Direction.UP -> Pair(head.first, head.second - 1)
            Direction.DOWN -> Pair(head.first, head.second + 1)
            Direction.LEFT -> Pair(head.first - 1, head.second)
            Direction.RIGHT -> Pair(head.first + 1, head.second)
        }

        if (newHead.first < 0 || newHead.first >= 700 / BLOCK_SIZE ||
            newHead.second < 0 || newHead.second >= 700 / BLOCK_SIZE ||
            snake.body.contains(newHead)
        ) {
            return false
        }

        if (newHead == food) {
            snake.grow(newHead)
        } else {
            snake.move(newHead)
        }

        return true
    }
}
