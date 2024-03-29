import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable {

    public Graphics2D g2;
    public KListener keyListener = new KListener();
    public Rect playerOne, playerTwo, ballRect;
    public PlayerController playerOneController, playerTwoController;
    public Ball ball;

    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);

        Constants.TOOLBAR_HEIGHT = this.getInsets().top;

        g2 = (Graphics2D) this.getGraphics();
        playerOne = new Rect(40, (Constants.SCREEN_HEIGHT / 2) - (Constants.PADDLE_HEIGHT / 2),
                            Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PLAYERONE_COLOR);
        playerTwo = new Rect(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - 40, (Constants.SCREEN_HEIGHT / 2) - (Constants.PADDLE_HEIGHT / 2),
                            Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PLAYERTWO_COLOR);
        ballRect = new Rect(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2,
                            Constants.BALL_WIDTH, Constants.BALL_WIDTH, Constants.BALL_COLOR);
        ball = new Ball(ballRect, playerOne, playerTwo);

        playerOneController = new PlayerController(playerOne, keyListener);
        playerTwoController = new PlayerController(playerTwo, keyListener);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        playerOne.draw(g2);
        playerTwo.draw(g2);
        ballRect.draw(g2);
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage, 0, 0, this);

        playerOneController.update(dt);
        playerTwoController.update(dt);
        ball.update(dt);
    }

    public void run() {
        double lastFrameTime = 0.0;

        while(true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);
        }
    }
}
