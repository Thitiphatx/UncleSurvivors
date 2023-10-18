import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Entity{
    gamePanel gp;
    Player py;
    ImageIcon image;
    Rectangle hitBox = new Rectangle();

    private int width;

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

    public void followPlayer(ArrayList<Enemy> enemies) {
        hitBox.x = x;
        hitBox.y = y;
        hitBox.width = gp.radius;
        hitBox.height = gp.radius;
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) != null && enemies.get(i+1) != null)
                if (enemies.get(i).hitBox.intersects(enemies.get(i+1).hitBox)) {
                    enemies.get(i).x++;
                    enemies.get(i+1).y++;
                }
        }
        if (py.x > x) {
            x += getSpeed();
            image = image_right;
        }
        if (py.x < x) {
            x -= getSpeed();
            image = image_left;
        }
        if (py.y > y) {
            y += getSpeed();
        }
        if (py.y < y) {
            y -= getSpeed();
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
            if (player.getHealth() > 0) {
                player.setHp(player.getHealth() - getAtk());
                player.lastHitTime = player.currentTime;
                System.out.println(player.getHealth());
            }
        };
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawImage(image.getImage(), x, y , gp.radius+width, gp.radius, null);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    public void loop(ArrayList<Enemy> enemies, int i) {
        followPlayer(enemies);
        loopAnimation();
        if (enemies.get(i).isDied()) {
            enemies.remove(i);
        }
    }
}
