import javax.swing.*;
import java.util.Random;

public class Entity {
    int maxhp;
    int hp;
    int atk;
    int speed;

    public int x, y;
    public int size = 1;
    public String direction;
    public ImageIcon image_left, image_right;

    // hit Iframe
    final long hitCooldown = 1000;
    long lastHitTime;
    long currentTime;

    Entity() {
        lastHitTime = System.currentTimeMillis();
        currentTime = System.currentTimeMillis();
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

    public void setDefaultStatus(int maxHp, int atk, int speed) {
        this.maxhp = maxHp;
        this.hp = maxHp;
        this.atk = atk;
        this.speed = speed;
    }
}