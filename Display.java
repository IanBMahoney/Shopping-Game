package shopping;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Lord Byron's Army
 */
public class Display extends JPanel {

    Game game;
    int level;
    Tile[][] tiles;
    ArrayList usedTileIds;
    ArrayList<Image> usedTiles;
    int offset = 0;
    
    
    public Display(int level) {
        this.level = level;
        game = new Game(this);
        calculateBackground();
        
           game.timer.start();
        //////System.out.println("ttt");
    }

    public void calculateBackground() {
        usedTileIds = new ArrayList();

        try {
            BufferedImage image = (BufferedImage) ImageIO.read(new File("levels/" + level + ".png"));
            int width = image.getWidth();
            int height = image.getHeight();
            tiles = new Tile[width][height];

            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    //int rgb = image.getRGB(i, j);
                    tiles[i][j] = new Tile((new Color(image.getRGB(i, j))).getRed(), (new Color(image.getRGB(i, j))).getGreen(), (new Color(image.getRGB(i, j))).getBlue());
                    ////////System.out.println(i + " " + j);
                    if (!usedTileIds.contains(tiles[i][j].rgb[0])) {
                        usedTileIds.add(tiles[i][j].rgb[0]);
                        ////////System.out.println(tiles[i][j].rgb[0]);
                        System.out.println("rgb green:"+tiles[i][j].rgb[1]);
                        
                    }
                }
            }
            //game.enemies.add(new DisobedientTeenager(game, 20, 20));
            game.initializeEnemies();
        } catch (Exception e) {
            //////System.out.println("\\/ " + level);
            e.printStackTrace();
        }

        usedTiles = new ArrayList();

        for (int i = 0; i < usedTileIds.size(); i++) {
            try {
                usedTiles.add(ImageIO.read(new File("tiles/" + usedTileIds.get(i) + ".png")));
            } catch (Exception e) {
                //////System.out.println("\\/ " + usedTileIds.get(i));
                e.printStackTrace();
            }
        }
    }

    public int convert256(int[] array) {
        int value = 0;

        for (int i = 0; i < array.length; i++) {
            value += array[i] * Math.pow(256, i);
        }

        return value;
    }

    public int[] append(int[] array, int value) {
        int[] newArray = new int[array.length + 1];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        newArray[array.length] = value;
        return newArray;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics g) {
        
        paintBackground(g);
        paintPlayer(g);
        paintEnemies(g);
//        g.fillRect(angryMother.hitBox);
        ////////System.out.println("t");
    }

    public void paintPlayer(Graphics g) {
        int x = game.player.x;
        int y = game.player.y;

        try {
//            if (game.player.direction.equals("right")) {
//                g.drawImage(game.player.player, x * game.SCALE, reverseY(y * game.SCALE) - game.player.height * game.SCALE + game.TILE_SIZE, game.TILE_SIZE * 4, game.TILE_SIZE * 8, this);
//            } else {
//                g.drawImage(game.player.player, x * game.SCALE + game.player.width * game.SCALE * 2 / 3 - 4, reverseY(y * game.SCALE) - game.player.height * game.SCALE + game.TILE_SIZE, (game.TILE_SIZE * 4) * -1, game.TILE_SIZE * 8, this);
//            }

if (game.player.direction.equals("right")) {
                g.drawImage(game.player.player, x * game.SCALE - offset, reverseY(y * game.SCALE) - game.player.height * game.SCALE + game.TILE_SIZE, game.player.player.getWidth() * 4, game.TILE_SIZE * 8, this);
            } else {
                g.drawImage(game.player.player, x * game.SCALE + game.player.width * game.SCALE * 2 / 3 - 4 - offset, reverseY(y * game.SCALE) - game.player.height * game.SCALE + game.TILE_SIZE, -game.player.player.getWidth() * 4, game.TILE_SIZE * 8, this);
            }
            
            
            ////////System.out.println("t");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int reverseY(int y) {
        int height = this.getSize().height;
        return height - y;
    }

    public void paintBackground(Graphics g) {

        try {
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    //Image image = ImageIO.read(new File(tiles[i][j].fileName));
                    //g.drawImage(image, i * 16, j * 16, this);
                    //Image t = usedTiles.get(usedTileIds.indexOf(tiles[i][j].convert256(tiles[i][j].rgb)));
                    g.drawImage(usedTiles.get(usedTileIds.indexOf(tiles[i][j].rgb[0])), i * game.SCALE * game.TILE_SIZE - offset, j * game.SCALE * game.TILE_SIZE, game.SCALE * game.TILE_SIZE, game.SCALE * game.TILE_SIZE, this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void paintEnemies(Graphics g){
        System.out.println(game.enemies.length);
        for (int i = 0; i < game.enemies.length; i++){
            if (Math.abs(game.player.x - game.enemies[i].x) < 200) {
            game.enemies[i].paint(g);//   System.out.println("paint"+i);
            }
        }
        
    }

}
