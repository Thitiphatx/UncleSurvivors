import javax.swing.*;

public class Ghost extends Enemy {
    Ghost(gamePanel gp, Player player) {
        super(gp, player);
        image = new ImageIcon(this.getClass().getResource("enemy/ghost.png"));
    }
}
