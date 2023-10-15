import javax.swing.*;
import java.awt.*;

public class Enemy extends Entity{
    gamePanel gp;
    Player py;
    public int width;
    ImageIcon image;
    Rectangle hitBox = new Rectangle();

    Enemy(gamePanel gp) {
        this.gp = gp;
        this.py = gp.player;
        super.randomSpawnpoint();
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

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawImage(image.getImage(), x, y , gp.radius+width, gp.radius, null);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    public void attack(Player target) {
        if (this.hitBox.intersects(target.hitBox) && target.isHit()) {
            if (target.hp > 0) {
                target.hp -= atk;
                target.lastHitTime = target.currentTime;
                System.out.println(target.hp);
            }
        };
    }

    public void loop(Enemy[] enemies, int index) {
        attack(py);
        followPlayer(enemies);
        loopAnimation();
        if (enemies[index].isDied()) {
            enemies[index] = null;
        }
    }
}
