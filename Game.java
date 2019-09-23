package shopping;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.Timer;

/**
 *
 * @author Lord Byron's Army
 */
class Game {

    Player player;
    AngryMother angryMother;
    
    //Tile[][] tiles;
//    ArrayList<Enemy> enemies = new ArrayList();
    Enemy[] enemies = new Enemy[0];
    Display display;
    
    Timer timer;
    final int SCALE = 6; // 8 tiles for height
    final int TILE_SIZE = 16;
    //final int FPS = 60;
    //final int FPS_DELAY = (int) (1000 / (double) FPS);
    int framesPassed = 0;

    boolean clipboardAttack = false;

    public Game(Display display) {
        
        this.display = display;
        theRightWay();
        ////////System.out.println(FPS_DELAY);
        timer = new Timer(33, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                framesPassed++;

                
                display.repaint();
                display.offset++;
            }
        });
        
        
        
        player = new Player(this);
//        angryMother = new AngryMother(this, 130, 0);
//        for (int i = 0; i < 7; i++){
//        enemies.add(new AngryMother(this, 130, 0));
//        enemies.add(new DisobedientTeenager(this, 13, 0));
//        enemies.add(new MotorizedScooter(this, 23, 0));
//        }
    }

        Clip clip;
        
    public Enemy[] remove(Enemy[] array, int position) {
        Enemy[] newArray = new Enemy[array.length - 1];
        int index = 0;
        
        for (int i = 0; i < array.length; i++) {
            if (position != i) {
                //index++;
                newArray[index] = array[i];
                index++;
            }
        }
        
        return newArray;
    }
        
        public Enemy[] append(Enemy[] array, Enemy value) {
            Enemy[] newArray = new Enemy[array.length + 1];
            
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            
            newArray[array.length] = value;
            return newArray;
        }

    public void theRightWay() {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/levels/music_main.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void initializeEnemies(){
        
        for (int i = 0; i < display.tiles.length; i++) {
                for (int j = 0; j < display.tiles[i].length; j++) {
        switch (display.tiles[i][j].rgb[1]){
                            
                            case 255:
                                //System.out.println("55555555");
                                enemies = append(enemies,(new AngryMother(this, i*TILE_SIZE, j*TILE_SIZE)));
                                break;
                            case 254:
                                //System.out.println("44444444");
                                enemies = append(enemies, (new DisobedientTeenager(this, i* TILE_SIZE, j* TILE_SIZE)));
                                break;
                            case 253:
                                //System.out.println("33333333");
                              enemies = append( enemies, (new MotorizedScooter(this, i*TILE_SIZE, j*TILE_SIZE)));
                                break;
                             default:
                                break;
                        }
                }
        }
    }
    
}
