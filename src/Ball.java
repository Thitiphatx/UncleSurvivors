import javax.swing.*;

public class Ball extends Enemy {
    Ball(gamePanel gp) {
        super(gp);
        image_right = new ImageIcon(this.getClass().getResource("enemy/ball_0.png"));
        image_left = new ImageIcon(this.getClass().getResource("enemy/ball_1.png"));
        setDefaultStatus(20, 1, 2);
        expDrop = 2;
    }
}
