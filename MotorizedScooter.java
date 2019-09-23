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
public class MotorizedScooter extends Enemy {

    BufferedImage motorizedScooter = null;
    boolean alive = true;
    int accelCounter = 0;

    public MotorizedScooter(Game game, int x, int y) {
        super(game, x, y);
        counter = 0;
        timerCounter = 0;
        rate = tile;
        try {
            motorizedScooter = ImageIO.read(getClass().getResource("/levels/mobilityScooter/oldMan32x32.png")); // in constructor
        } catch (IOException ex) {
            Logger.getLogger(DisobedientTeenager.class.getName()).log(Level.SEVERE, null, ex);
        }
        w = motorizedScooter.getWidth();
        h = motorizedScooter.getHeight();
        isClose();

    }

    public void motorizedMove() {
        if (!dead) {
            if (!randomMovement) {
                timer = new javax.swing.Timer(500, new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        if (direction && timerCounter < 5) {
                            timerCounter++;
                            x += rate;
                            if (timerCounter == 5) {
                                direction = false;
                            }
                        }
                        if (!direction && timerCounter > 5) {
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

                        if (direction && timerCounter < 5) {
                            timerCounter++;
                            x += rate + (random.nextInt(5) + 2);
                            if (timerCounter == random.nextInt()) {
                                direction = false;
                            }
                        }
                        if (!direction && timerCounter > 5) {
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

                    if ((x - game.player.x) <= 3 * tile) {
                        accelCounter++;
                        if (accelCounter == 1) {
                            rate += 15;
                            accelCounter++;
                        } else if (accelCounter == 2) {
                            rate += 30;
                            accelCounter++;
                        } else if (accelCounter == 3) {
                            rate += 45;
                            accelCounter++;
                        } else if (accelCounter == 4) {
                            rate += 60;
                            accelCounter++;
                        } else if (accelCounter == 5) {
                            rate += 75;
                            accelCounter++;
                        } else if (accelCounter == 6) {
                            rate -= 15;
                            accelCounter++;

                        } else if (accelCounter == 7) {
                            rate -= 15;
                            accelCounter++;

                        } else if (accelCounter == 8) {
                            rate -= 15;
                            accelCounter++;

                        } else if (accelCounter == 9) {
                            rate -= 15;
                            accelCounter++;

                        } else {
                            rate = tile;
                            accelCounter = 0;
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

            ////System.out.println("print");
            w = motorizedScooter.getWidth();
            h = motorizedScooter.getHeight();
            int wt = (w + 16) / tile;
            int ht = h / tile;
            ////System.out.println("wt:" + wt + "ht" + ht);

            if (alive) {
                if ((x - game.player.x) <= 0) {
                    g.drawImage(motorizedScooter, x * 6 - game.display.offset, y * 5, (-(w) * 4), ((h) * 4), null);
                    try {

                        //g.drawImage(angryMother, x + w * game.SCALE, (y * game.SCALE), game.TILE_SIZE * 4, game.TILE_SIZE * 8, this);
                        //////System.out.println("t");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //game.display.drawImage(angryMother, x + w, y, -w, h, null);
                } else {
                    g.drawImage(motorizedScooter, x * 6 - game.display.offset, y * 5, ((w) * 4), ((h) * 4), null);
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
                if (game.display.tiles[x / game.TILE_SIZE][(y / game.TILE_SIZE)].rgb[2] == 0) {
                    onSolidGround = true;
                    falling = false;
                    return;
                }
            }
        }
    }

}
