import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gamePanel extends JPanel {

    private final ImageIcon map1_image = new ImageIcon(this.getClass().getResource("map1.png"));
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);
    Enemy[] enemies = new Enemy[10];

    UI ui = new UI(this, player);
    public final int screenWidth = 1000;
    public final int screenHeight = 800;
    public final int pixel = 16;
    public final int radius = pixel*player.size;

    gamePanel() {
        this.setBackground(Color.white);
        this.setSize(screenWidth, screenHeight);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        setupEnemy();
        gameThread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(map1_image.getImage(), 0 ,0, screenWidth, screenHeight, this);
        g.setColor(Color.WHITE);
        drawEnemy(g);
        player.draw(g);
        ui.draw(g);
    }

    public void setupEnemy() {
        enemies[0] = new Ghost(this);
        enemies[1] = new Ghost(this);
        enemies[2] = new Ghost(this);
        enemies[3] = new Ghost(this);
        enemies[4] = new Ghost(this);
        enemies[5] = new Ghost(this);
    }

    Thread gameThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(16);
                    player.walk();
                    loopEnemy();
                    repaint();
                } catch (Exception e) {}
            }
        }
    });

    public void loopEnemy() {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                enemies[i].loop(enemies, i);
            }
        }
    }
    public void drawEnemy(Graphics g) {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] == null) {
                continue;
            }
            else {
                enemies[i].draw(g);
            }
        }
    }
}
