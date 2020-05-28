import java.awt.Graphics
import java.awt.Image
import java.awt.event.ActionListener
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import javax.swing.JPanel
import javax.swing.Timer

class Pole(private val slogn: Int) : JPanel() {
    var x = 400
    private var shapka: Image? = null
    private var fon: Image? = null
    private val gamePodar: Array<Podarok>
    private var end_game: Image? = null
    var timerUpdate: Timer
    var timerDraw: Timer

    public override fun paintComponent(gr: Graphics) {
        super.paintComponent(gr)
        gr.drawImage(fon, 0, 0, null)
        gr.drawImage(shapka, x, 465, null)
        for (i in 0..6) {
            gamePodar[i].draw(gr)
            if (gamePodar[i].act === true)
            {
                if (gamePodar[i].y + 120 >= 470)
                {
                    if (Math.abs(gamePodar[i].x - x) > 75)
                    {
                        gr.drawImage(end_game, 300, 300, null)
                        timerDraw.stop()
                        timerUpdate.stop()
                        break
                    } else gamePodar[i].act = false
                }
            }
        }
    }

    private fun updateStart() {
        var kol = 0
        for (i in 0..6)
        {
            if (gamePodar[i].act === false)
            {
                if (kol < slogn)
                {
                    gamePodar[i].start()
                    break
                }
            } else kol++
        }
    }

    init {
        try {
            shapka = ImageIO.read(File("./shapka.png"))
        } catch (ex: IOException) {
        }

        try {
            fon = ImageIO.read(File("./fon.png"))
        } catch (ex: IOException) {
        }

        try {
            end_game = ImageIO.read(File("./end_game.png"))
        } catch (ex: IOException) {
        }

        gamePodar = arrayOfNulls<Podarok>(7)
        for (i in 0..6) {
            try {
                gamePodar[i] = Podarok(ImageIO.read(File("./p$i.png")))
            } catch (ex: IOException) {
            }
        }

        timerUpdate = Timer(3000, ActionListener {
            updateStart()
        })
        timerUpdate.start()

        timerDraw = Timer(50, ActionListener {
            repaint()
        })
        timerDraw.start()
    }
}