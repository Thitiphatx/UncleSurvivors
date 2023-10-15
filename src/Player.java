import javax.swing.*;
import java.awt.*;

public class Player extends Entity {
    private final ImageIcon[] player_walk = new ImageIcon[4];
    private final ImageIcon health_icon = new ImageIcon(this.getClass().getResource(""));
    gamePanel gp;
    KeyHandler kh;
    ImageIcon player_image;
    String direction = "left";
    Rectangle hitBox = new Rectangle();
    private int hitBoxTrim = 10;
    int spriteCounter = 0;

    public Player(gamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;
        reset();
        initializePlayerWalk();
        player_image = player_walk[0];
    }

    private void initializePlayerWalk() {
        for (int i = 0; i < player_walk.length; i++) {
            player_walk[i] = new ImageIcon(this.getClass().getResource("player/uncle_" + i + ".png"));
        }
    }

    public void reset() {
        hp = 20;
        size = 3;
        speed = 4;
        x = gp.screenWidth / 2;
        y = gp.screenHeight / 2;
    }

    Rectangle test2;
    public void walk() {
        hitBox.x = x + hitBoxTrim;
        hitBox.y = y + hitBoxTrim;
        hitBox.width = gp.radius - hitBoxTrim * 2;
        hitBox.height = gp.radius - hitBoxTrim;

        if (kh.up && y > size + gp.radius/2) {
            y -= speed;
        }
        if (kh.down && y < gp.screenHeight - gp.radius/2) {
            y += speed;
        }
        if (kh.left && x > size + gp.radius/2 - gp.pixel/2) {
            x -= speed;
            direction = "left";
        }
        if (kh.right && x < gp.screenWidth - gp.radius/2) {
            x += speed;
            direction = "right";
        }
        spriteCounter++;
        if (kh.left || kh.right || kh.up || kh.down) {
            if (kh.left) {
                if (spriteCounter < 10) {
                    player_image = player_walk[0];
                }
                else {
                    player_image = player_walk[1];
                }
            }
            else if (kh.right) {
                if (spriteCounter < 10) {
                    player_image = player_walk[2];
                }
                else {
                    player_image = player_walk[3];
                }
            }
            else {
                if (direction.equals("left")) {
                    if (spriteCounter < 10) {
                        player_image = player_walk[0];
                    }
                    else {
                        player_image = player_walk[1];
                    }
                }
                else {
                    if (spriteCounter < 10) {
                        player_image = player_walk[2];
                    }
                    else {
                        player_image = player_walk[3];
                    }
                }

            }
            if (spriteCounter > 20) {
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawImage(player_image.getImage(), x, y , gp.radius, gp.radius, null);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height); // player hitbox
        g.drawOval(x - gp.radius, y - gp.radius, gp.radius * size, gp.radius * size);
    }
}


