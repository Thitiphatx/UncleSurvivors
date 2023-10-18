import javax.swing.*;

public class Slime extends Enemy {
    Slime(gamePanel gp) {
        super(gp);
        image_right = new ImageIcon(this.getClass().getResource("enemy/slime_0.png"));
        image_left = new ImageIcon(this.getClass().getResource("enemy/slime_1.png"));
        setDefaultStatus(10, 1, 1);
        expDrop = 2;
    }
}
