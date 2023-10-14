import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
abstract public class Ability {
    int damage;
    int size;
    int loopCounter;
    Ability() {
        damage = 1;
        size = 1;
    }

    abstract public boolean intersects(Rectangle rect);
    abstract public void setPosition(int x, int y);

    abstract public void draw(Graphics g);
}

class DotArea extends Ability {
    ImageIcon[] skillImage = new ImageIcon[5];
    gamePanel gp;
    Entity actor;
    Ellipse2D.Double AOE;
    DotArea(gamePanel gp, Entity actor) {
        this.gp = gp;
        this.actor = actor;
        AOE = new Ellipse2D.Double(0, 0, size, size);
        size = 3;

        for (int i = 0; i < skillImage.length; i++) {
            skillImage[i] = new ImageIcon(this.getClass().getResource("skill/magic_animation/magic" + i + ".png"));
        }
        loopCounter = 0;
    }

    @Override
    public boolean intersects(Rectangle rect) {
        return AOE.intersects(rect);
    }

    @Override
    public void setPosition(int x, int y) {
        AOE.setFrame(actor.x - gp.radius, actor.y - gp.radius, gp.radius*size, gp.radius*size);
    }

    @Override
    public void draw(Graphics g) {
        loopCounter++;
        System.out.println(loopCounter);
    }
}
