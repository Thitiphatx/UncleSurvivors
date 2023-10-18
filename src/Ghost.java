import javax.swing.*;

public class Ghost extends Enemy {
    Ghost(gamePanel gp) {
        super(gp);
        image_right = new ImageIcon(this.getClass().getResource("enemy/ghost_0.png"));
        image_left = new ImageIcon(this.getClass().getResource("enemy/ghost_1.png"));
        setDefaultStatus(20, 1, 1);
        expDrop = 2;
    }
}
