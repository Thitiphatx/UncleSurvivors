import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
abstract public class Ability {
    gamePanel gp;
    Entity actor;
    String id;
    Ellipse2D hitBox;
    int atk;
    int size;
    int loopCounter;
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
    ImageIcon[] skillImage = new ImageIcon[5];
    Ellipse2D.Double hitBox;
    DotArea(gamePanel gp, Player actor) {
        super(gp, actor);
        atk = 1;
        hitBox = new Ellipse2D.Double(0, 0, size, size);
        hitBox.setFrame(actor.x - gp.radius, actor.y - gp.radius, gp.radius, gp.radius);

        for (int i = 0; i < skillImage.length; i++) {
            skillImage[i] = new ImageIcon(this.getClass().getResource("skill/magic_animation/magic" + i + ".png"));
        }
        loopCounter = 0;
    }


    @Override
    public void draw(Graphics g) {
        g.drawImage(skillImage[frame].getImage(), actor.x - gp.radius, actor.y - gp.radius, gp.radius, gp.radius, null);
        loopCounter++;
        if (loopCounter % 3 == 0) {
            frame = (frame + 1) % skillImage.length;
        }
    }
}

class FireBall extends Ability {
    private int x, y;
    private int speedX, speedY;

    FireBall(gamePanel gp, Player actor) {
        super(gp, actor);
        hitBox = new Ellipse2D.Double(actor.x, actor.y, size, size);
        speedX = 2;
        speedY = 2;
        size = 20;
        atk = 1;
        x = 0;
        y = 0;
    }

    @Override
    public void draw(Graphics g) {
        x += speedX;
        y += speedY;
        if (x < 0 || x > gp.screenWidth - size)
            speedX *= -1;
        if (y < 0 || y > gp.screenHeight - size)
            speedY *= -1;

        g.drawOval(x, y, size, size);
    }
}
