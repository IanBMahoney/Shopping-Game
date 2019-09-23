package shopping;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import static shopping.Main.win;

/**
 *
 * @author Max
 */
public class TitleInput extends KeyAdapter implements MouseListener {
    
    Game game;
    boolean debug = false;
    int num = 0;
    JDialog win;
    TitleDisplay display;
    
    public TitleInput(JDialog win, TitleDisplay display) {
        this.win = win;
        this.display = display;
    }
    
    public void debug(String message) {
        if (debug) {
            System.out.println(message + " " + num);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            debug("A\t Held down");
            
            //if (!game.player.right) {
            //    game.player.left = true;
            //    //game.player.moveLeft();
            //}
        }
        
        if (e.getKeyCode() == KeyEvent.VK_D) {
            debug("D\t Held down");
            
            //if (!game.player.left) {
            //    game.player.right = true;
            //    //game.player.moveRight();
            //    //System.out.println(game.player.x);
            //}
        }
        
        if (e.getKeyCode() == KeyEvent.VK_W) {
            debug("W\t Held down");
            
            //if (!game.player.falling) {
            //    game.player.jumping = true;
            //    System.out.println("t");
            //}
        }
        
        if (e.getKeyCode() == KeyEvent.VK_S) {
            debug("S\t Held down");
        }
        
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            debug("Exiting the program");
            System.exit(0);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            display.clip.stop();
            display.gooooo = true;
            //win.setVisible(false);
            win.dispose();
            //game.display.win.dispose();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            
            //System.out.println("left");
        }
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            
            //System.out.println("right");
        }
        
        if (e.getKeyCode() == KeyEvent.VK_COMMA) {
            
        }
        
        if (e.getKeyCode() == KeyEvent.VK_PERIOD) {
            
        }
        
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            //game.player.left = false;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_D) {
            //game.player.right = false;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_W) {
            //game.player.jumping = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("exited");
    }
    
}
