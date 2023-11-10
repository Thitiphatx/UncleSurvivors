import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    gamePanel gp;
    KeyHandler(gamePanel gp) {
        this.gp = gp;
    }

    public boolean up, down, left, right;

    public void resetDirection() {
        up = false;
        down = false;
        left = false;
        right = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (gp.gameState == gp.menuState) {
            if (key == KeyEvent.VK_W && gp.ui.command > 0) {
                gp.ui.command--;
            }
            if (key == KeyEvent.VK_S && gp.ui.command < 1) {
                gp.ui.command++;
            }
            if (key == KeyEvent.VK_ENTER) {
                if (gp.ui.command == 0) {
                    gp.resetGame();
                    gp.gameState = gp.playState;
                }
                else {
                    System.exit(0);
                }
            }
        }
        else if (gp.gameState == gp.playState) {
            if (key == KeyEvent.VK_W) {
                up = true;
            }
            if (key == KeyEvent.VK_A) {
                left = true;
            }
            if (key == KeyEvent.VK_S) {
                down = true;
            }
            if (key == KeyEvent.VK_D) {
                right = true;
            }
        }
        else if (gp.gameState == gp.levelUpState) {

            if (key == KeyEvent.VK_A && gp.ui.levelCommand > 0) {
                gp.ui.levelCommand--;
            }
            else if (key == KeyEvent.VK_D && gp.ui.levelCommand < 2) {
                gp.ui.levelCommand++;
            }

            if (key == KeyEvent.VK_ENTER) {
                int select = gp.statusManager.statusArr[gp.ui.levelCommand];
                gp.statusManager.SelectBuff(select);
                gp.gameState = gp.playState;
            }
        }
        else if (gp.gameState == gp.finishState || gp.gameState == gp.overState) {
            if (key == KeyEvent.VK_W && gp.ui.finishCommand == 1) {
                gp.ui.finishCommand--;
            }
            if (key == KeyEvent.VK_S && gp.ui.finishCommand == 0) {
                gp.ui.finishCommand++;
            }
            if (key == KeyEvent.VK_ENTER) {
                if (gp.ui.finishCommand == 0) {
                    gp.resetGame();
                    gp.gameState = gp.playState;
                }
                else {
                    gp.gameState = gp.menuState;
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (gp.gameState == 1) {
            if (key == KeyEvent.VK_W) {
                up = false;
            }
            if (key == KeyEvent.VK_A) {
                left = false;
            }
            if (key == KeyEvent.VK_S) {
                down = false;
            }
            if (key == KeyEvent.VK_D) {
                right = false;
            }
        }

    }
}
