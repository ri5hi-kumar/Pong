import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerController {
    public Rect rect;
    public KListener keyListener;

    public PlayerController(Rect rect, KListener keyListener) {
        this.rect = rect;
        this.keyListener = keyListener;
    }

    public void update(double dt) {
        if(this.rect.color == Color.RED) {
            if(keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
                if((rect.y + Constants.PADDLE_SPEED * dt) + rect.height < Constants.SCREEN_HEIGHT) {
                    this.rect.y += Constants.PADDLE_SPEED * dt;
                }
            } else if(keyListener.isKeyPressed(KeyEvent.VK_UP)) {
                if(rect.y - Constants.PADDLE_SPEED * dt > Constants.TOOLBAR_HEIGHT) {
                    this.rect.y -= Constants.PADDLE_SPEED * dt;
                }
            }
        }

        if(this.rect.color == Color.GREEN) {
            if(keyListener.isKeyPressed(KeyEvent.VK_S)) {
                if((rect.y + Constants.PADDLE_SPEED * dt) + rect.height < Constants.SCREEN_HEIGHT) {
                    this.rect.y += Constants.PADDLE_SPEED * dt;
                }
            } else if(keyListener.isKeyPressed(KeyEvent.VK_W)) {
                if(rect.y - Constants.PADDLE_SPEED * dt > Constants.TOOLBAR_HEIGHT) {
                    this.rect.y -= Constants.PADDLE_SPEED * dt;
                }
            }
        }
    }
}
