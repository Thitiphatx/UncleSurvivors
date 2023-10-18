import javax.swing.*;
import java.awt.*;

public class UI {
    ImageIcon healthIcon = new ImageIcon(this.getClass().getResource("ui/health.png"));
    ImageIcon logoIcon = new ImageIcon(this.getClass().getResource("logo.png"));
    ImageIcon levelupIcon = new ImageIcon(this.getClass().getResource("ui/levelup.png"));
    ImageIcon levelupSelectIcon = new ImageIcon(this.getClass().getResource("ui/select.png"));
    ImageIcon finishIcon = new ImageIcon(this.getClass().getResource("ui/finish.png"));
    gamePanel gp;
    Player py;
    UI(gamePanel gp, Player py) {
       this.gp = gp;
       this.py = py;
    }

    public void drawGame(Graphics g) {
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

    int command = 0;
    public void drawMenu(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g.drawImage(logoIcon.getImage(),  gp.screenWidth/2 - 500/2, 100, 500, 208, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 36));
        g.drawString("PLAY", gp.screenWidth/2- 50 ,350);
        g.drawString("EXIT", gp.screenWidth/2- 50 ,400);
        if (command == 0) {
            g.drawString(">", gp.screenWidth/2- 80, 350);
        }
        else if (command == 1) {
            g.drawString(">", gp.screenWidth/2- 80, 400);
        }
    }
    int levelCommand = 0;
    public void drawLevelUp(Graphics g) {
        g.drawImage(levelupIcon.getImage(),  0, 0, 1000, 800, null);
        gp.statusManager.draw(g);
        if (levelCommand == 0) {
            g.drawImage(levelupSelectIcon.getImage(),  287, 350, 150, 161, null);
        }
        else if (levelCommand == 1) {
            g.drawImage(levelupSelectIcon.getImage(),  422, 350, 150, 161, null);
        }
        else {
            g.drawImage(levelupSelectIcon.getImage(),  557, 350, 150, 161, null);
        }
    }

    int finishCommand = 0;
    public void drawOver(Graphics g) {
        g.drawImage(finishIcon.getImage(),  0, 0, 1000, 800, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 36));
        g.drawString("RESTART", gp.screenWidth/2 - 100,400);
        g.drawString("EXIT", gp.screenWidth/2 - 100,450);

        if (finishCommand == 0) {
            g.drawString(">", gp.screenWidth/2 - 80 - 100, 400);
        }
        else if (finishCommand == 1) {
            g.drawString(">", gp.screenWidth/2 - 80 - 100, 450);
        }
    }
}
