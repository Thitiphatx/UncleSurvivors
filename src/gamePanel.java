import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class gamePanel extends JPanel {

    private final ImageIcon map1_image = new ImageIcon(this.getClass().getResource("map1.png"));

    public KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);
    public ArrayList<Enemy> enemies = new ArrayList<>(10);
    public UI ui = new UI(this, player);

    public final int screenWidth = 1000;
    public final int screenHeight = 800;
    public final int radius = 48;

    gamePanel() {
        this.setBackground(Color.white);
        this.setSize(screenWidth, screenHeight);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        setupEnemy();
        gameThread.start();
    }

    Thread gameThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(16);
                    loopEnemy();
                    loopPlayer();
                    repaint();
                } catch (Exception e) {}
            }
        }
    });

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(map1_image.getImage(), 0 ,0, screenWidth, screenHeight, this);
        g.setColor(Color.WHITE);
        drawEnemy(g);
        drawPlayer(g);
        ui.draw(g);
    }

    public void setupEnemy() {
        enemies.add(new Ghost(this));
        enemies.add(new Ghost(this));
        enemies.add(new Ghost(this));
        enemies.add(new Ghost(this));
        enemies.add(new Ghost(this));

    }

    public void loopPlayer() {
        player.walk();

        for (int i = 0; i < enemies.size(); i++) {
            player.attack(enemies.get(i));
        }

    }
    public void drawPlayer(Graphics g) {
        player.draw(g);
    }

    public void loopEnemy() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) != null) {
                enemies.get(i).loop(enemies, i);
                enemies.get(i).attack(player);
            }
        }
    }
    public void drawEnemy(Graphics g) {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) != null) {
                enemies.get(i).draw(g);
            }
        }
    }

}
