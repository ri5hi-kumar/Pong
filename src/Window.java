import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {

    Graphics2D g2;
    KListener keyListener = new KListener();

    public Window() {
        this.setSize(Constents.SCREEN_WIDTH, Constents.SCREEN_HEIGHT);
        this.setTitle(Constents.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        g2 = (Graphics2D) this.getGraphics();
    }

    public void update(double dt) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constents.SCREEN_WIDTH, Constents.SCREEN_HEIGHT);

        if(keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            System.out.println("UP key is pressed");
        } else if(keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            System.out.println("DOWN key is down");
        }
    }

    public void run() {
        double lastFrameTime = 0.0;
        while(true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

            // caps the frames to 30fps
            try {
                Thread.sleep(30);
            } catch(Exception e) {
                System.out.println("Thread sleep exception");
            }
        }
    }
}
