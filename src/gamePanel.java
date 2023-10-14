import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gamePanel extends JPanel {

    private final ImageIcon map1_image = new ImageIcon(this.getClass().getResource("map1.png"));
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);
    Enemy enemies = new Ghost(this, player);

    public final int screenWidth = 1000;
    public final int screenHeight = 800;
    public final int pixel = 16;
    public final int radius = pixel*player.size;

    gamePanel() {
        this.setBackground(Color.white);
        this.setSize(screenWidth, screenHeight);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        gameThread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(map1_image.getImage(), 0 ,0, screenWidth, screenHeight, this);
        g.setColor(Color.WHITE);

        player.draw(g);
        enemies.draw(g);
    }

    Thread gameThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(16);
                    player.walk();
                    enemies.followPlayer();
                    enemies.loopAnimation();
                    repaint();
                } catch (Exception e) {}
            }
        }
    });
}
