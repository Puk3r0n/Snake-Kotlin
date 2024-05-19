class Snake {
    val body = mutableListOf(Pair(10.0, 10.0))

    fun move(newHead: Pair<Double, Double>) {
        body.add(0, newHead)
        body.removeAt(body.size - 1)
    }

    fun grow(newHead: Pair<Double, Double>) {
        body.add(0, newHead)
    }

    fun head(): Pair<Double, Double> {
        return body.first()
    }
}
