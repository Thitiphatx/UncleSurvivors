import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        gamePanel gp = new gamePanel();
        frame.setSize(gp.screenWidth+10, gp.screenHeight+39);
        frame.add(gp);
        frame.setTitle("Uncle Survivors");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}