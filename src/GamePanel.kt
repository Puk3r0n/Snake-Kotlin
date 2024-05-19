import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.imageio.ImageIO
import javax.swing.JPanel
import javax.swing.Timer
import kotlin.io.path.Path
import kotlin.io.path.inputStream

const val BLOCK_SIZE = 27

class GamePanel : JPanel(), ActionListener, KeyListener {
    private var direction = Direction.RIGHT
    private val snake = Snake()
    private var food = GenerateApple.generate()
    private var gameOver = false
    private val timer: Timer
    private val headImage: Image
    private val appleImage: Image
    private val bodyImage: Image

    init {
        preferredSize = Dimension(700, 700)
        isFocusable = true
        addKeyListener(this)
        timer = Timer(100, this)
        timer.start()

        headImage = ImageIO.read(Path("src/head.png").inputStream())
        appleImage = ImageIO.read(Path("src/apple.png").inputStream())
        bodyImage = ImageIO.read(Path("src/body.png").inputStream())
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        draw(g)
    }

    private fun draw(g: Graphics) {
        if (gameOver) {
            g.color = Color.WHITE
            g.drawString("Game Over", width / 2 - 30, height / 2)
            return
        }

        for (i in 1 until snake.body.size) {
            val segment = snake.body[i]
            g.drawImage(bodyImage, segment.first.toInt() * BLOCK_SIZE, segment.second.toInt() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, this)
        }

        val head = snake.head()
        g.drawImage(headImage, head.first.toInt() * BLOCK_SIZE, head.second.toInt() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, this)

        g.drawImage(appleImage, food.first.toInt() * BLOCK_SIZE, food.second.toInt() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, this)
    }

    override fun actionPerformed(e: ActionEvent) {
        if (!gameOver) {
            gameOver = !Update.update(snake, direction, food)
            if (snake.head() == food) {
                food = GenerateNewApple.generate(snake)
                SoundUtils.playSound("src/sound.wav")
            }
            repaint()
        }
    }

    override fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_W -> if (direction != Direction.DOWN) direction = Direction.UP
            KeyEvent.VK_S -> if (direction != Direction.UP) direction = Direction.DOWN
            KeyEvent.VK_A -> if (direction != Direction.RIGHT) direction = Direction.LEFT
            KeyEvent.VK_D -> if (direction != Direction.LEFT) direction = Direction.RIGHT
        }
    }

    override fun keyReleased(e: KeyEvent) {}
    override fun keyTyped(e: KeyEvent) {}
}
