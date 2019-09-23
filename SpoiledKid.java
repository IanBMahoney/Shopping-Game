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
public class SpoiledKid extends Enemy {

    BufferedImage spoiledKid = null;
    boolean alive = true;

    public SpoiledKid(Game game, int x, int y) {
        super(game, x, y);
        counter = 0;
        timerCounter = 0;
        rate = 3 * tile;
        w = spoiledKid.getWidth();
        h = spoiledKid.getHeight();
        try {
            spoiledKid = ImageIO.read(getClass().getResource("/sprites/child/childOne28x14.png")); // in constructor
        } catch (IOException ex) {
            Logger.getLogger(DisobedientTeenager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void spoiledMove() {
        if (!dead) {

            if (!randomMovement) {
                timer = new javax.swing.Timer(500, new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        if (direction && timerCounter < 3) {
                            timerCounter++;
                            x += rate;
                            if (timerCounter == 5) {
                                direction = false;
                            }
                        }
                        if (!direction && timerCounter > 3) {
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

                        if (direction && timerCounter < 3) {
                            timerCounter++;
                            x += rate + (random.nextInt(6) + 2);
                            if (timerCounter == random.nextInt()) {
                                direction = false;
                            }
                        }
                        if (!direction && timerCounter > 3) {
                            timerCounter--;
                            x -= rate + (random.nextInt(7) + 3);
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

                    if ((x - game.player.x) <= 2 * tile) {
                        frameCounter++;
                        try {
                            if (frameCounter == 1) {
                                spoiledKid = ImageIO.read(getClass().getResource("/sprites/child/childOne28x28t1.png"));
                                counter++;
                            } else if (frameCounter == 2) {
                                spoiledKid = ImageIO.read(getClass().getResource("/sprites/child/childOne28x28t2.png"));
                                frameCounter++;
                            } else if (frameCounter == 3) {
                                spoiledKid = ImageIO.read(getClass().getResource("/sprites/child/childOne28x28t3.png"));
                                frameCounter++;
                            } else if (frameCounter == 4) {
                                spoiledKid = ImageIO.read(getClass().getResource("/sprites/child/childOne28x28t4.png"));
                                frameCounter++;
                            } else if (frameCounter == 5) {
                                spoiledKid = ImageIO.read(getClass().getResource("/sprites/child/childOne28x28t5.png"));
                                counter++;
                            } else if (frameCounter == 6) {
                                spoiledKid = ImageIO.read(getClass().getResource("/sprites/child/childOne28x28t4.png"));
                                frameCounter++;
                            } else if (frameCounter == 7) {
                                spoiledKid = ImageIO.read(getClass().getResource("/sprites/child/childOne28x28t3.png"));
                                frameCounter++;
                            } else if (frameCounter == 8) {
                                spoiledKid = ImageIO.read(getClass().getResource("/sprites/child/childOne28x28t2.png"));
                                frameCounter++;
                            } else if (frameCounter == 9) {
                                spoiledKid = ImageIO.read(getClass().getResource("/sprites/child/childOne28x28t1.png"));
                                counter++;
                            } else {
                                spoiledKid = ImageIO.read(getClass().getResource("/sprites/child/childOne28x14.png"));
                                frameCounter = 0;
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(DisobedientTeenager.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
        if (!dead) {

            if (alive) {
                
                if ((x - game.player.x) <= 0) {
                    g.drawImage(spoiledKid, x + w, y, -w, h, null);
                    //game.display.drawImage(spoiledKid, x + w, y, -w, h, null);
                } else {
                    g.drawImage(spoiledKid, x, y, null);
                    //game.display.drawImage(spoiledKid, x, y, null);
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
}
