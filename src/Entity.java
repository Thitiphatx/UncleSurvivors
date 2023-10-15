import javax.swing.*;
import java.util.Random;

public class Entity {
    public int hp;
    public int atk;
    public int speed;
    public int x, y;
    public int size;
    public String direction;
    public ImageIcon image_left, image_right;
    final long hitCooldown = 1000;
    long lastHitTime;
    long currentTime;

    Entity() {
        lastHitTime = System.currentTimeMillis();
        currentTime = System.currentTimeMillis();
    }

    public void randomSpawnpoint() {
        Random rand = new Random();
        this.x = rand.nextInt(1000);
        this.y = rand.nextInt(800);
    }

    public boolean isHit() {
        currentTime = System.currentTimeMillis();
        if (currentTime - lastHitTime >= hitCooldown) {
            lastHitTime = currentTime;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isDied() {
        if (hp <= 0) {
            return true;
        }
        return false;
    }
}