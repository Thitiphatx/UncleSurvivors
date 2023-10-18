import javax.swing.*;
import java.awt.*;

public class UI {
    ImageIcon healthIcon = new ImageIcon(this.getClass().getResource("ui/health.png"));
    gamePanel gp;
    Player py;
    UI(gamePanel gp, Player py) {
       this.gp = gp;
       this.py = py;
    }

    public void draw(Graphics g) {
        g.drawImage(healthIcon.getImage(),  10, 10, gp.radius, gp.radius, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString(py.getHealth()+"", 100, 50);
    }
}
