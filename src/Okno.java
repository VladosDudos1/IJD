import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Okno extends JFrame
{
    private final Pole gameP;
    private final int slogn;

    private class myKey implements KeyListener
    {
        public void keyPressed(KeyEvent e)
        {
            int key_ = e.getKeyCode();

            if (key_==27) System.exit(0);
            else if (key_==37)
            {
                if (gameP.getX()-30>-48) gameP.getX()-=30;
                else gameP.getX()=752;
            }
            else if (key_==39)
            {
                if (gameP.getX()+30<752) {
                    gameP.getX()+=30;
                } else {
                    gameP.getX()=-48;
                }
            }

        }
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}
    }

    public Okno(int slogn)
    {
        this.slogn = slogn;
        addKeyListener(new myKey());
        setFocusable(true);

        setBounds(0,0,800,600);
        setTitle("Игра: Новогодний дождь");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameP = new Pole(slogn);
        Container con = getContentPane();
        con.add(gameP);
        setVisible(true);
    }
}
