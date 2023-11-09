import javax.swing.*;

public class Bat extends Enemy {
    Bat(gamePanel gp) {
        super(gp);
        image_right = new ImageIcon(this.getClass().getResource("enemy/bat_0.png"));
        image_left = new ImageIcon(this.getClass().getResource("enemy/bat_1.png"));
        setDefaultStatus(40, 1, 1);
        expDrop = 2;
    }
}
