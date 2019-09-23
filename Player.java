/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author Lord Byron's Army
 */
public class Player {

    Game game;
    int x;
    int y;
    int damage = 3;
    //int health = 5;
    //int tile;
    int counter = 0;
    int jumpHeight;
    int currentJumpHeight = 0;
    int lives;
    int width = 16;
    int height = 24;
    int health = 3;
    int cooldown = 0;
    boolean inAir;
    boolean jumped;
    boolean dead = false;
    boolean onSolidGround = false;
    boolean left = false;
    boolean right = false;
    boolean jumping = false;
    boolean falling = true;
    String direction = "right";

    BufferedImage player = null; // in class  
    //private Timer attackTimer;
    private Timer timer;
    int downwardMomentum = 0;
    int momentum = 0;
    int delay = 0;

    public Player(Game game) {
        this.game = game;

        x = 1;
        y = 48;
        jumpHeight = 12 / 5 * game.TILE_SIZE;

        try {
            if (!game.clipboardAttack) {
                player = ImageIO.read(new File("sprites/manager/manager32x16.png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                                intersection();

                if (momentum == 0) {
                    try {
                        player = player = ImageIO.read(new File("sprites/manager/manager32x16.png"));
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                // stops the player from falling if it reaches the top of a solid tile
                if (y % game.TILE_SIZE == 0 && y != 0) {
                    //if (game.tiles[x / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2] == 0) {
                    ////////System.out.println((x / game.TILE_SIZE) + " " + (y / game.TILE_SIZE) + "\t" + game.display.tiles[x / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2]);
                    if (game.display.tiles[(x) / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2] == 0) {
                        onSolidGround = true;
                        falling = false;
                    }
                }
                //////System.out.println("rgb:" + game.display.tiles[(((x) / game.TILE_SIZE))][(7 - ((y) / game.TILE_SIZE))].rgb[2]);
                // falling momentum
                if (!(game.display.tiles[((x) - 2) / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2] == 0)) {
                    onSolidGround = false;
                    falling = true;
                }
                if ((game.display.tiles[((((x)) / game.TILE_SIZE))][(7 - ((y) / game.TILE_SIZE))].rgb[2] == 0) || (y == 0)) {
                    //////System.out.println("y" + y);
                    onSolidGround = true;
                    falling = false;
                    //jumping = false;
                    //y = 48;
                }

                if (!onSolidGround && !jumping) {
                    falling = true;
                    downwardMomentum += 1;

                    if (downwardMomentum > 1) {
                        downwardMomentum = 1;
                    }

                    moveDown(downwardMomentum);
                }

                if (onSolidGround) {
                    currentJumpHeight = 0;
                    falling = false;
                }

                // left/right momentum
                if (left || right) {
                    delay++;

                    if (momentum == 0) {
                        momentum++;
                    }

                    if (momentum < 3 && delay == 20) {
                        momentum++;
                        delay = 0;
                    }
                }

                // left and right movement / momentum stopper
                if (left) {
                    moveLeft();
                } else if (right) {
                    moveRight();
                } else {
                    momentum = 0;
                    delay = 0;
                }

                if (jumping) {
                    jump();
                }

                if (game.clipboardAttack) {
                    try {
                        switch (counter) {
                            case 0:
                                //counter++;

                                //////System.out.println("Counter == 0");
                                player = ImageIO.read(new File("sprites/manager/manager32x24r2.png"));

                                //////System.out.println("Counter just past == 0");
                                break;
                            case 1:
                                //counter++;

                                //////System.out.println("Counter == 1");
                                player = ImageIO.read(new File("sprites/manager/manager32x26m1.png"));
                                //player = ImageIO.read(new File("sprites/manager/manager32x16m1.png"));
                                break;
                            case 2:
                                //counter++;

                                //////System.out.println("Counter == 2");
                                player = ImageIO.read(new File("sprites/manager/manager32x26m2.png"));
                                break;
                            case 3:
                                //counter++;

                                //////System.out.println("Counter == 3");
                                player = ImageIO.read(new File("sprites/manager/manager32x26m3.png"));
                                try {
                                    for (int i = 0; i < game.enemies.length; i++) {
                                        //if (x + tile) == game.enemies.get(i).x) {
                                        // may need to increase attack distance
                                        //   if (within(game.TILE_SIZE, x, game.enemies.get(i).x)) {
                                        //           if ((x - game.enemies.get(i).x <= game.TILE_SIZE) && ((y - game.enemies.get(i).y <= 5) || (y - game.enemies.get(i).y >= -5)) && (game.clipboardAttack)) {
                                        //       game.enemies.get(i).death();
                                        //          game.enemies.remove(i);
                                        //                 }
                                        if (within(game.TILE_SIZE, x, game.enemies[i].x)) {
                                            game.enemies[i].dead = true;
                                            //System.out.println("death occurred");
                                            //game.enemies.get(i).
                                        }
                                        //System.out.println("ArrayList Contents: " + game.enemies.length);

                                    }
                                } catch (Exception er) {
                                    System.err.println(er);
                                }
                                break;
                            case 4:
                                //counter++;

                                //////System.out.println("Counter == 4");
                                player = ImageIO.read(new File("sprites/manager/manager32x26m2.png"));
                                break;
                            case 5:
                                //counter++;

//                            ////System.out.println("Counter: "+counter);
                                player = ImageIO.read(new File("sprites/manager/manager32x26m1.png"));
                                break;
                            case 6:
                                //counter++;
                                counter = 0;
                                //////System.out.println("Counter == 6");
                                player = ImageIO.read(new File("sprites/manager/manager32x24r2.png"));
                                game.clipboardAttack = false;
                                player = ImageIO.read(new File("sprites/manager/manager32x16.png"));
                                break;
                            default:
                                break;
                        }
                        counter++;
                        ////System.out.println(counter);
                    } catch (Exception ex) {
                        System.err.println(ex);
                        ////System.out.println(counter);
                    }
                }

            }
        });

        timer.start();
    }

    public void moveDown(int amount) {

        for (int i = 0; i < amount; i++) {
            if (--y == 0) {
                break;
            }
            y--;

            if (y % game.TILE_SIZE == 0 && y != 0) {
                //y--;

                //if (game.tiles[x / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2] == 0) {
                ////////System.out.println((x / game.TILE_SIZE) + " " + (y / game.TILE_SIZE) + "\t" + game.display.tiles[x / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2]);
                if (game.display.tiles[(x) / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2] == 0) {
                    onSolidGround = true;
                    return;
                }
            }
        }
    }

//    public void penAttack() {
//
//    }
    public boolean within(int distance, int playerCoordinate, int enemyCoordinate) {
        int[] boundaries = {playerCoordinate - distance, playerCoordinate + distance};

        //if (boundaries[0] >= enemyCoordinate && boundaries[1] <= enemyCoordinate) {
        if (Math.abs(playerCoordinate - enemyCoordinate) <= distance) {
            return true;
        }

        return false;
    }

    public void death() {
        player = rotateCw(player);
        dead = true;
    }

    public void jump() {

        if (!jumping) {

            return;
        }

        if (currentJumpHeight < jumpHeight) {
            onSolidGround = false;
            currentJumpHeight += 4;

            for (int i = 0; i < 4; i++) {
                y += 1;

                if (y % game.TILE_SIZE == 0 && y != 0) {
                    //////System.out.println("rgb:" + game.display.tiles[((x / game.TILE_SIZE) + 1)][((y / game.TILE_SIZE) - 1)].rgb[2]);
                    //////System.out.println("x:" + (x / game.TILE_SIZE));
                    //////System.out.println("y:" + ((y / game.TILE_SIZE) - 2));
                    //////System.out.println((game.display.tiles[x / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2] == 0));
                    //////System.out.println((game.display.tiles[x / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2]));
                    //if (game.tiles[x / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2] == 0) {
                    ////////System.out.println((x / game.TILE_SIZE) + " " + (y / game.TILE_SIZE) + "\t" + game.display.tiles[x / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2]);
                    if ((game.display.tiles[((((x)) / game.TILE_SIZE))][(((y) / game.TILE_SIZE))].rgb[2] == 0) || (y == 0)) {

                        onSolidGround = true;
                        falling = false;
                        //jumping = false;
                        //y = 48;
                    }

                    //////System.out.println((x / game.TILE_SIZE / game.SCALE) + "" + (y / game.TILE_SIZE) + " " + game.display.tiles[x / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2]);
                }
            }
        } else {
            jumping = false;
        }
    }

    public void moveRight() {
        try {
            if ((game.framesPassed % 5 == 0) && (momentum > 0)) {
                player = ImageIO.read(new File("sprites/manager/manager32x24r1.png"));
            } else {
                player = ImageIO.read(new File("sprites/manager/manager32x24r2.png"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < momentum; i++) {
            x += 1;
        }

        direction = "right";
    }

    public void moveLeft() {
        try {
            if ((game.framesPassed % 5 == 0) && (momentum > 0)) {
                player = ImageIO.read(new File("sprites/manager/manager32x24r1.png"));
            } else {
                player = ImageIO.read(new File("sprites/manager/manager32x24r2.png"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < momentum && x > 0; i++) {
            x -= 1;
        }

        direction = "left";
    }

    public static BufferedImage rotateCw(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImage = new BufferedImage(height, width, img.getType());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newImage.setRGB(height - 1 - j, i, img.getRGB(i, j));
            }
        }

        return newImage;
    }

    public void intersection() {
        //System.out.println("intersection run");
        
        for (int i = 0; i < game.enemies.length; i++) {
             //game.enemies[i].death();
             //ame.enemies = game.remove(game.enemies, i);
             //System.out.println(i + " c8svdisfovhsiov iu g");
            if (within(game.TILE_SIZE, this.x, game.enemies[i].x) && game.clipboardAttack) {//Player hit enemy
                //System.out.println("Im being attacked");
                //game.enemies[i].death();
                //System.out.println("jso ihdf ish iushgvsljg vjlhgjlhg");
                //game.enemies[i].dead = true;
                game.enemies = game.remove(game.enemies, i);
                i--;
//                System.out.println("ow i died");
            } else if ((x - game.enemies[i].x <= 16) && (!game.clipboardAttack) && (cooldown == 0)) {//Player hit enemy
                health--;
                System.out.println("health: " + health);
//                x -= 64;

                if (health == 0) {
                    death();
                }
                cooldown = 1500;
            } else {
//                game.enemies.get(i).dead=false;
                cooldown--;
            }

        }

    }

}
