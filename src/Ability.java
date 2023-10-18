import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

abstract public class Ability {
    gamePanel gp;
    Entity actor;
    String id;
    Ellipse2D hitBox;
    int atk;
    int size;
    int loopCounter = 0;
    int frame = 0;
    Ability(gamePanel gp, Player actor) {
        this.gp = gp;
        this.actor = actor;
    }

    public String getID() {
        return id;
    }
    public int getAtk() {
        return atk;
    }

    abstract public void draw(Graphics g);

}

class DotArea extends Ability {
    ImageIcon[] frameImage = new ImageIcon[10];
    int hitsize;
    DotArea(gamePanel gp, Player actor) {
        super(gp, actor);

        for (int i = 0; i < frameImage.length; i++) {
            frameImage[i] = new ImageIcon(this.getClass().getResource("skill/magic_animation/magic" + i + ".png"));
        }

        size = 200;
        hitsize = size-50;
        atk = 1;

        hitBox = new Ellipse2D.Double(actor.x, actor.y, size, size);
        hitBox.setFrame(actor.x - hitsize/2 + gp.radius/2, actor.y - hitsize/2 + gp.radius/2, hitsize, hitsize);
    }

    @Override
    public void draw(Graphics g) {

        hitBox.setFrame(actor.x - hitsize/2 + gp.radius/2, actor.y - hitsize/2 + gp.radius/2, hitsize, hitsize);
        g.drawImage(frameImage[frame].getImage(), actor.x - size/2 + gp.radius/2, actor.y - size/2 + gp.radius/2, size, size, null);
//        g.drawOval(actor.x - hitsize/2 + gp.radius/2, actor.y - hitsize/2 + gp.radius/2, hitsize, hitsize);

        loopCounter++;
        if (loopCounter % 2 == 0) {
            frame = (frame + 1) % frameImage.length;
        }
    }
}

class BounceBall extends Ability {
    private int x, y;
    private int speedX, speedY;
    ImageIcon[] frameImage = new ImageIcon[10];

    BounceBall(gamePanel gp, Player actor) {
        super(gp, actor);

        for (int i = 0; i < frameImage.length; i++) {
            frameImage[i] = new ImageIcon(this.getClass().getResource("skill/bounce_animation/bounce" + i + ".png"));
        }

        speedX = 2;
        speedY = 2;
        size = 100;
        atk = 1;
        x = randomPosition(1000);
        y = randomPosition(800);
        hitBox = new Ellipse2D.Double(actor.x, actor.y, size, size);
        hitBox.setFrame(actor.x, actor.y, size, size);
    }

    public int randomPosition(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }

    @Override
    public void draw(Graphics g) {
        x += speedX;
        y += speedY;
        if (x < 0 || x > gp.screenWidth - size)
            speedX *= -1;
        if (y < 0 || y > gp.screenHeight - size)
            speedY *= -1;

        hitBox.setFrame(x, y, size, size);
        g.drawImage(frameImage[frame].getImage(),x, y, size, size, null);

        loopCounter++;
        if (loopCounter % 3 == 0) {
            frame = (frame + 1) % frameImage.length;
        }
    }
}