import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class status {
    Player player;
    int[] statusArr;
    int countSelected = 1;
    status(Player player) {
        this.player = player;
        statusArr = new int[3];
        RandomBuff();
    }


    // speed, atk, regen, add bounce
    public void SelectBuff(int i) {
        if (i == 1) {
            player.speed += 1;
        }
        else if (i == 2) {
            player.atk += 1;
        }
        else if (i == 3) {
            if (player.hp+5 < player.maxhp) {
                player.hp += 5;
            }
            else {
                player.hp = 20;
            }
        }
        else {
            player.addSkill(1);
        }
    }

    public void RandomBuff() {
        Random rand = new Random();
        statusArr[0] = rand.nextInt(4);
        statusArr[1] = rand.nextInt(4);
        statusArr[2] = rand.nextInt(4);
    }

    ImageIcon bootIcon = new ImageIcon(this.getClass().getResource("ui/boot.png"));
    ImageIcon swordIcon = new ImageIcon(this.getClass().getResource("ui/Sword.png"));
    ImageIcon healthIcon = new ImageIcon(this.getClass().getResource("ui/health.png"));
    ImageIcon bounceIcon = new ImageIcon(this.getClass().getResource("ui/bounce.png"));

    public void draw(Graphics g) {
        int xSpace = 0;
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                xSpace = 20;
            }
            else if (i == 1) {
                xSpace = 160;
            }
            else if (i == 2) {
                xSpace = 300;
            }

            if (statusArr[i] == 1) {
                g.drawImage(bootIcon.getImage(), 287+xSpace, 380, 100, 100, null);
            }
            else if (statusArr[i] == 2) {
                g.drawImage(swordIcon.getImage(), 287+xSpace, 380, 100, 100, null);
            }
            else if (statusArr[i] == 3) {
                g.drawImage(healthIcon.getImage(), 287+xSpace, 380, 100, 100, null);
            }
            else {
                g.drawImage(bounceIcon.getImage(), 287+xSpace, 380, 100, 100, null);
            }
        }
    }
}
