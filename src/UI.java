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
        // icon
        g.drawImage(healthIcon.getImage(),  30, 50, gp.radius, gp.radius, null);
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("TimesRoman", Font.BOLD, 24));
        g.drawString(py.level+"", 47, 85);

        //hp bar
        g.setColor(new Color(34, 40, 49));
        g.drawRect(100, 50, 200, 30);
        g.setColor(new Color(255, 255, 255));
        g.fillRect(100, 50, py.hp*10, 30);
        g.setColor(new Color(34, 40, 49));
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("hp : "+py.hp+"  /  20", 110, 75);

        // xp bar
        g.setColor(new Color(34, 40, 49));
        g.drawRect(100, 80, 200, 30);
        g.setColor(new Color(255, 255, 255));
        g.fillRect(100, 80, (200/10)*py.exp, 30);
        g.setColor(new Color(34, 40, 49));
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("xp : "+py.exp+"  /  10", 110, 105);

        // skills

    }

}
