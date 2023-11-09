import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player extends Entity {
    private final ImageIcon[] player_walk = new ImageIcon[4];
    gamePanel gp;
    KeyHandler kh;

    ImageIcon player_image;
    String direction = "left";
    int spriteCounter = 0;

    Rectangle hitBox = new Rectangle();

    ArrayList<Ability> skills;
    int exp;
    int level;

    public Player(gamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;
        level = 0;
        reset();
        initializePlayerWalk();
        player_image = player_walk[0];
    }

    private void initializePlayerWalk() {
        for (int i = 0; i < player_walk.length; i++) {
            player_walk[i] = new ImageIcon(this.getClass().getResource("player/uncle_" + i + ".png"));
        }
    }

    public void reset() {
        setDefaultStatus(20, 1, 4);
        atk = 1;
        exp = 0;
        level = 0;
        x = gp.screenWidth / 2;
        y = gp.screenHeight / 2;
        skills = new ArrayList<>(5);
        skills.add(new BounceBall(gp, this));
        skills.add(new DotArea(gp, this));
    }

    public void walk() {
        hitBox.x = x;
        hitBox.y = y;
        hitBox.width = gp.radius;
        hitBox.height = gp.radius;

        if (kh.up && y > 0) {
            y -= speed;
        }
        if (kh.down && y < gp.screenHeight - gp.radius) {
            y += speed;
        }
        if (kh.left && x > 0) {
            x -= speed;
            direction = "left";
        }
        if (kh.right && x < gp.screenWidth - gp.radius) {
            x += speed;
            direction = "right";
        }
        spriteCounter++;
        if (kh.left || kh.right || kh.up || kh.down) {
            if (kh.left) {
                if (spriteCounter < 10) {
                    player_image = player_walk[0];
                } else {
                    player_image = player_walk[1];
                }
            } else if (kh.right) {
                if (spriteCounter < 10) {
                    player_image = player_walk[2];
                } else {
                    player_image = player_walk[3];
                }
            } else {
                if (direction.equals("left")) {
                    if (spriteCounter < 10) {
                        player_image = player_walk[0];
                    } else {
                        player_image = player_walk[1];
                    }
                } else {
                    if (spriteCounter < 10) {
                        player_image = player_walk[2];
                    } else {
                        player_image = player_walk[3];
                    }
                }

            }
            if (spriteCounter > 20) {
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawImage(player_image.getImage(), x, y, gp.radius, gp.radius, null);
//        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
        drawSkills(g);
    }


//     skill methods
    public void addSkill(int id) {
        switch (id) {
            case 1:
                skills.add(new BounceBall(gp, this));
                break;
//            case 2:
//                skills.add(new DotArea(gp, this));
            default:
                System.out.print("No ID");
        }
    }

    public void drawSkills(Graphics g) {
        for (Ability skill : skills) {
            skill.draw(g);
        }

    }

    public void attack(Enemy enemy) {
        for (int i = 0; i < skills.size(); i++) {

            if (skills.get(i).hitBox.intersects(enemy.hitBox) && enemy.isHit()) {
                enemy.hp -= skills.get(i).atk+atk;
            }
        }
    }
}


