import java.awt.Graphics
import java.awt.Image
import java.awt.event.ActionListener
import javax.swing.Timer


internal class Podarok(img: Image) {
    var img: Image
    var x = 0
    var y = 0
    var act: Boolean
    var timerUpdate: Timer

    fun start() {
        timerUpdate.delay = 300
        timerUpdate.start()
        y = 0
        x = (Math.random() * 700).toInt()
        act = true
    }

    fun vniz() {
        if (act == true)
        {
            y += 6
        }
        if (y + 120 >= 470)
        {
            timerUpdate.stop()
        }
    }

    fun draw(gr: Graphics) {
        if (act == true) {
            gr.drawImage(img, x, y, 120, 120, null)
        }
    }

    init {
        timerUpdate = Timer(500, ActionListener {
            vniz()
        })
        this.img = img
        act = false
    }
}