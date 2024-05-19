object GenerateApple {
    fun generate(): Pair<Double, Double> {
        val x = (Math.random() * (700 / BLOCK_SIZE)).toInt().toDouble()
        val y = (Math.random() * (700 / BLOCK_SIZE)).toInt().toDouble()
        return Pair(x, y)
    }
}
