import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

object SoundUtils {
    fun playSound(filePath: String) {
        try {
            val soundFile = File(filePath)
            val audioInputStream = AudioSystem.getAudioInputStream(soundFile)
            val clip: Clip = AudioSystem.getClip()
            clip.open(audioInputStream)
            clip.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
