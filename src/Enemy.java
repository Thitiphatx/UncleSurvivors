import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy extends Entity{
    gamePanel gp;
    Player py;
    ImageIcon image;
    Rectangle hitBox = new Rectangle();

    private int width;
    int expDrop;

    Enemy(gamePanel gp) {
        this.gp = gp;
        this.py = gp.player;
        randomSpawnpoint();
    }

    public void randomSpawnpoint() {
        Random rand = new Random();
        this.x = rand.nextInt(1000);
        this.y = rand.nextInt(800);
    }

    public void followPlayer(Enemy[] enemies) {
        hitBox.x = x;
        hitBox.y = y;
        hitBox.width = gp.radius;
        hitBox.height = gp.radius;
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null && enemies[i+1] != null)
                if (enemies[i].hitBox.intersects(enemies[i+1].hitBox)) {
                    enemies[i].x++;
                    enemies[i+1].y++;
                }
        }
        if (py.x > x) {
            x += speed;
            image = image_right;
        }
        if (py.x < x) {
            x -= speed;
            image = image_left;
        }
        if (py.y > y) {
            y += speed;
        }
        if (py.y < y) {
            y -= speed;
        }
    }

    float loopCounter = 0;
    int direction = 1;

    public void loopAnimation() {
        if (loopCounter >= 0 && loopCounter <= 20 && loopCounter%2 == 0) {
            width++;
        }
        else if (loopCounter >= 21 && loopCounter <= 40 && loopCounter%2 == 0){
            width--;
        }
        if (loopCounter == 41) {
            loopCounter = 0;
        }
        loopCounter++;
    }

    public void attack(Player player) {
        if (hitBox.intersects(player.hitBox) && player.isHit()) {
            if (player.hp > 0) {
                player.hp -= atk;
                player.lastHitTime = player.currentTime;
            }
        };
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image.getImage(), x, y , gp.radius+width, gp.radius, null);
//        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
            // hp bar
            g.setColor(new Color(255, 255, 255));
            g.fillRect(hitBox.x, hitBox.y + 50, gp.radius*hp/maxhp , 5);
        }

    }

    public void loop(Enemy[] enemies, int i) {
        followPlayer(enemies);
        loopAnimation();
    }
}
