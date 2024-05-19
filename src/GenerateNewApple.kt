object GenerateNewApple {
    fun generate(snake: Snake): Pair<Double, Double> {
        var newApple: Pair<Double, Double>
        do {
            newApple = GenerateApple.generate()
        } while (snake.body.contains(newApple))
        return newApple
    }
}
