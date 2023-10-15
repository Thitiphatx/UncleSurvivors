import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
abstract public class Ability {
    int atk;
    int size;
    int loopCounter;
    int frame = 0;
    Ability() {
        atk = 5;
        size = 1;
    }

    abstract public void attack(Enemy target);
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
    public void attack(Enemy target) {
        if (AOE.intersects(target.hitBox) && target.isHit()) {
            if (target.hp > 0) {
                target.hp -= atk;
                target.lastHitTime = target.currentTime;
                System.out.println(target.hp);
            }
        };
    }

    @Override
    public void setPosition(int x, int y) {
        AOE.setFrame(actor.x - gp.radius, actor.y - gp.radius, gp.radius*size, gp.radius*size);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(skillImage[frame].getImage(), actor.x - gp.radius, actor.y - gp.radius, gp.radius * size, gp.radius * size, null);
        loopCounter++;
        if (loopCounter % 3 == 0) {
            frame = (frame + 1) % skillImage.length;
        }
    }
}
