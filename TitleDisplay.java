package shopping;

import java.awt.Graphics;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Max
 */
class TitleDisplay extends JPanel {

    JDialog win;
    Clip clip;
    boolean gooooo = false;
    
    public TitleDisplay(JDialog win) {
        this.win = win;
        repaint();
        theRightWay();
    }
    
    @Override
    public void paint(Graphics g) {
        System.out.println(WIDTH + " " + HEIGHT);
        try {
            g.drawImage(ImageIO.read(new File("title.png")), 0, 0, 1280, 768, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void theRightWay() {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/levels/music_shopping.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
}
