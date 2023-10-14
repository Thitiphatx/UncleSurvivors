import javax.swing.*;
import java.awt.*;

public class Enemy extends Entity{
    gamePanel gp;
    Player py;
    public int width;
    public int height;

    Enemy(gamePanel gp, Player py) {
        this.gp = gp;
        this.py = py;
        reset();
    }

    public void setSpawnpoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void reset() {
        speed = 2;
        size = 3;
        x = -10;
        y = -10;
    }

    public void followPlayer() {
        if (py.x > x) {
            x += speed;
        }
        if (py.x < x) {
            x -= speed;
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
        g.drawImage(image.getImage(), x, y , gp.radius+width, gp.radius, null);
    }
}
