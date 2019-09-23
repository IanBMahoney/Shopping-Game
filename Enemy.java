package shopping;

import java.awt.*;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author LBA
 */
public class Enemy {

    Random random = new Random();
    boolean randomMovement = true;
    int counter = 0;
    int timerCounter = 0;
    int x;
    int y;
    int rate;
    Game game;
    Timer timer;
    Timer attackTimer;
    boolean direction = true;//true is right
    boolean start = false;
    Game manager;
    int frameCounter = 0;
    int w;
    int h;
    int tile = 16; //REMEMBER TO CHANGE TILE THINGIES MAX
    boolean onSolidGround = false;
    //int offset = 0;
    boolean falling = true;
    int downwardMomentum = 0;
    boolean dead=false;

    Toolkit myToolkit = Toolkit.getDefaultToolkit();

    //Toolkit myToolkit = Toolkit.getDefaultToolkit();
    //Image crosshairImage = myToolkit.getImage(".png");
    public Enemy(Game game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        
    }

    public void death() {
        //System.out.println(game.enemies.length-1);
for (int i = 0; i < game.enemies.length; i++){
    if (this == game.enemies[i] ){
        game.remove(game.enemies, i);
    }
}
    }
    
   
}
