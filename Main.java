package shopping;

import javax.swing.JDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Ian Mahoney, Alex Truman, Max Clausius, Billy Fong 
 */
public class Main {

    public static JDialog win;

    public static void main(String[] args) {
        win = new JDialog();
        win.setResizable(false);
        win.setSize(1280, 768);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        win.setUndecorated(false);

        TitleDisplay titleDisplay = new TitleDisplay(win);
        //Game game = new Game(display);
        win.add(titleDisplay);
        //win.add(display);
        win.setVisible(true);

        TitleInput displayInput = new TitleInput(win, titleDisplay);

        win.addKeyListener(displayInput);
        win.addMouseListener(displayInput);
        
        while (win.isValid()) {
            
        }
        
        win = new JDialog();
        win.setResizable(false);
        win.setSize(1280, 768);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        win.setUndecorated(true);

        Display display = new Display(1);
        //Game game = new Game(display);
        win.add(display);
        //win.add(display);
        win.setVisible(true);

        Input input = new Input(display.game);

        win.addKeyListener(input);
        win.addMouseListener(input);
        
    }

}
