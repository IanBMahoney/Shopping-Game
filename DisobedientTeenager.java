/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author LBA
 */
public class DisobedientTeenager extends Enemy {

    //Image disobedientTeenager = myToolkit.getImage("disobedientTeenager.png");
    boolean alive = true;
    BufferedImage disobedientTeenager = null; // in class

    public DisobedientTeenager(Game game, int x, int y) {
        super(game, x, y);
        counter = 0;
        timerCounter = 0;
        rate = (tile * 15) / 10;
        try {
            disobedientTeenager = ImageIO.read(getClass().getResource("/levels/teen/teen32x18.png")); // in constructor
        } catch (IOException ex) {
            Logger.getLogger(DisobedientTeenager.class.getName()).log(Level.SEVERE, null, ex);
        }
        w = disobedientTeenager.getWidth();
        h = disobedientTeenager.getHeight();

        isClose();

    }

    public void disobedientMove() {
        if (!dead) {
            if (!randomMovement) {
                timer = new javax.swing.Timer(500, new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        if (direction && timerCounter < 4) {
                            timerCounter++;
                            x += rate;
                            if (timerCounter == 5) {
                                direction = false;
                            }
                        }
                        if (!direction && timerCounter > 4) {
                            timerCounter--;
                            x -= rate;
                            if (timerCounter == 0) {
                                direction = true;
                            }
                        }
                    }
                });
                timer.start();
            } else {
                timer = new javax.swing.Timer(500, new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        if (direction && timerCounter < 4) {
                            timerCounter++;
                            x += rate + (random.nextInt(5) + 2);
                            if (timerCounter == random.nextInt()) {
                                direction = false;
                            }
                        }
                        if (!direction && timerCounter > 4) {
                            timerCounter--;
                            x -= rate + (random.nextInt(6) + 3);
                            if (timerCounter == random.nextInt()) {
                                direction = true;
                            }
                        }
                    }
                });
            }
        }
    }

    public void isClose() {
        if (!dead) {

            attackTimer = new javax.swing.Timer(100, new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    gravitate();

                    if (game.player.x + game.display.offset > x + game.display.offset) {
                        //timerCounter++;
                        x += 1;

                    }
                    if (game.player.x + game.display.offset < x + game.display.offset) {
                        //timerCounter--;
                        x -= 1;

                    }

                    if (((x) - game.player.x) <= 2 * tile) {
                        frameCounter++;
                        try {
                            if (frameCounter == 1) {
                                disobedientTeenager = ImageIO.read(getClass().getResource("/levels/teen/teen32x18w.png"));
                                counter++;
                            } else if (frameCounter == 2) {
                                disobedientTeenager = ImageIO.read(getClass().getResource("/levels/teen/teen32x22a1.png"));
                                frameCounter++;
                            } else if (frameCounter == 3 || frameCounter == 4) {
                                disobedientTeenager = ImageIO.read(getClass().getResource("/levels/teen/teen32x32a2.png"));
                                frameCounter++;
                            } else if (frameCounter == 5) {
                                disobedientTeenager = ImageIO.read(getClass().getResource("/levels/teen/teen32x22a1.png"));
                                frameCounter++;
                            } else if (frameCounter == 6) {
                                disobedientTeenager = ImageIO.read(getClass().getResource("/levels/teen/teen32x18w.png"));
                                frameCounter++;
                            } else {
                                disobedientTeenager = ImageIO.read(getClass().getResource("/levels/teen/teen32x18.png"));
                                frameCounter = 0;//cycle back through spray paint
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(DisobedientTeenager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //check x values if player x less than teen x then turn left, vice versa
                        //check x values if player x less than teen x then turn left, vice versa
                        //check x values if player x less than teen x then turn left, vice versa
                        //check x values if player x less than teen x then turn left, vice versa
                    }
                }
            });
            attackTimer.start();

            /*        if ((x - game.player.x) == 50) {
            try {
                disobedientTeenager = ImageIO.read(getClass().getResource("/sprite/teen32x18w.png"));
                disobedientTeenager = ImageIO.read(getClass().getResource("/sprite/teen32x22a1.png"));
                disobedientTeenager = ImageIO.read(getClass().getResource("/sprite/teen32x32a2.png"));
                disobedientTeenager = ImageIO.read(getClass().getResource("/sprite/teen32x18.png"));
            } catch (IOException ex) {
                Logger.getLogger(DisobedientTeenager.class.getName()).log(Level.SEVERE, null, ex);
            }
            //check x values if player x less than teen x then turn left, vice versa //done i think?
        } */
        }
    }

    

    @Override
    public void paint(Graphics g) {
        System.out.println("print");
        if (!dead) {

            ////System.out.println("print");
            w = disobedientTeenager.getWidth();
            h = disobedientTeenager.getHeight();
            int wt = (w + 16) / tile;
            int ht = h / tile;
            ////System.out.println("wt:" + wt + "ht" + ht);

            if (alive) {
                System.out.println("ah ah ah ah stayin alive");
                if ((x - game.player.x) <= 0) {
                    g.drawImage(disobedientTeenager, x * 6 - game.display.offset, y * 5, ((w) * 4), ((h) * 4), null);
                    try {

                        //g.drawImage(angryMother, x + w * game.SCALE, (y * game.SCALE), game.TILE_SIZE * 4, game.TILE_SIZE * 8, this);
                        //////System.out.println("t");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //game.display.drawImage(angryMother, x + w, y, -w, h, null);
                } else {
                    g.drawImage(disobedientTeenager, x * 6 - game.display.offset, y * 5, (-(w) * 4), ((h) * 4), null);
                    //game.display.drawImage(angryMother, x, y, null);
                }
            }
        }
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

    public void gravitate() {
        //System.out.println(game.display.tiles[(x) / game.TILE_SIZE][(y / game.TILE_SIZE)].rgb[2]);
        if (!(game.display.tiles[(x - 2) / game.TILE_SIZE][(y / game.TILE_SIZE)].rgb[2] == 0)) {
            onSolidGround = false;
            falling = true;
        }
//                if ((game.display.tiles[(((x) / game.TILE_SIZE))][(((y) / game.TILE_SIZE))].rgb[2] == 0) || (y == 0)) {
//                    //////System.out.println("y" + y);
//                    onSolidGround = true;
//                    falling = false;
//                    //jumping = false;
//                    //y = 48;
//                    return;
//                }

        if (!onSolidGround) {
            falling = true;
            downwardMomentum += 1;

            if (downwardMomentum > 3) {
                downwardMomentum = 3;
            }

            moveDown(downwardMomentum);
        }
    }

    public void moveDown(int amount) {
//System.out.println("moving down?");
        for (int i = 0; i < amount; i++) {
            if (++y == 0) {
                break;
            }
            y++;
            //System.out.println("y "+ y);

            if (y % game.TILE_SIZE == 0 && y != 0) {
                //y--;

                //if (game.tiles[x / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2] == 0) {
                ////////System.out.println((x / game.TILE_SIZE) + " " + (y / game.TILE_SIZE) + "\t" + game.display.tiles[x / game.TILE_SIZE][y / game.TILE_SIZE].rgb[2]);
                if (game.display.tiles[(x) / game.TILE_SIZE][(y / game.TILE_SIZE)].rgb[2] == 0) {
                    onSolidGround = true;
                    falling = false;
                    return;
                }
            }
        }
    }
}
