import javax.swing.*;
import java.util.Random;

public class Entity {
    private int hp;
    private int atk;
    private int speed;

    public int x, y;
    public int size = 3;
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
        if (getHealth() <= 0) {
            return true;
        }
        return false;
    }

    public void setDefaultStatus(int hp, int atk, int speed) {
        this.hp = hp;
        this.atk = atk;
        this.speed = speed;
    }


    // mutator
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setAtk(int atk) {
        this.atk = atk;
    }
    public int getHealth() {
        return hp;
    }
    public int getAtk() {
        return atk;
    }
    public int getSpeed() {
        return speed;
    }
}