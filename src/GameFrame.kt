import java.awt.Rectangle
import java.util.*
import javax.swing.JFrame

class GameFrame() : JFrame() {
    private val width = 700
    private val height = 700
    private val x = 50
    private val y = 50

    init {
        setBounds(x, y, width, height)
        add(GamePanel())
        isVisible = true
    }
}