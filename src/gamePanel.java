import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel {

    private final ImageIcon map1_image = new ImageIcon(this.getClass().getResource("map1.png"));

    public KeyHandler keyH = new KeyHandler(this);
    public Player player = new Player(this, keyH);
    public Enemy[] enemies = new Enemy[50];
    public UI ui = new UI(this, player);

    public final int screenWidth = 1000;
    public final int screenHeight = 800;
    public final int radius = 48;
    private int currentEnemy = 0;
    private int gameLevel = 1;

    public int gameState = 0;
    public final int menuState = 0;
    public final int playState = 1;
    public final int levelUpState = 2;

    gamePanel() {
        this.setBackground(Color.white);
        this.setSize(screenWidth, screenHeight);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        setupEnemy(gameLevel);
        gameState = levelUpState;
        gameThread.start();
    }

    Thread gameThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(16);
                    repaint();
                } catch (Exception e) {}
            }
        }
    });

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        playState(g);
    }

    public void setupEnemy(int level) {
        if (level == 1) {
            for (int i = 0; i < 6; i++) {
                enemies[i] = new Slime(this);
                currentEnemy++;
            }

        }
        else if (level == 2) {
            for (int i = 0; i < 16; i++) {
                enemies[i] = new Slime(this);
                if (i > 10) {
                    enemies[i] = new Ghost(this);
                }
                currentEnemy++;
            }
        }


    }

    public void loopPlayer() {
        player.walk();
        if (player.exp >= 10) {
            player.level++;
            player.exp -= 10;
        }
    }
    public void drawPlayer(Graphics g) {
        player.draw(g);
    }

    public void loopEnemy() {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                enemies[i].loop(enemies, i);
                enemies[i].attack(player);
                player.attack(enemies[i]);
                if (enemies[i].isDied()) {
                    player.exp += enemies[i].expDrop;
                    enemies[i] = null;
                    currentEnemy--;
                }
            }
        }
        if (currentEnemy == 0) {
            gameLevel++;
            setupEnemy(gameLevel);
        }
    }
    public void drawEnemy(Graphics g) {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                enemies[i].draw(g);
            }
        }
    }


    public void playState(Graphics g) {
        if (gameState == menuState) {
            ui.drawMenu(g);
        }
        else if (gameState == playState) {
            g.drawImage(map1_image.getImage(), 0 ,0, screenWidth, screenHeight, this);
            g.setColor(Color.WHITE);
            drawEnemy(g);
            drawPlayer(g);
            ui.drawGame(g);
            loopEnemy();
            loopPlayer();
        }
        else if (gameState == levelUpState) {
            ui.drawLevelUp(g);
        }
    }
}
