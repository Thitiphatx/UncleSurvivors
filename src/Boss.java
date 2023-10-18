import javax.swing.*;

public class Boss extends Enemy {
    Boss(gamePanel gp) {
        super(gp);
        image_right = new ImageIcon(this.getClass().getResource("enemy/boss_0.png"));
        image_left = new ImageIcon(this.getClass().getResource("enemy/boss_1.png"));
        setDefaultStatus(100, 2, 1);
        expDrop = 0;
        size = 3;
    }
}
