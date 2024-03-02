public class Ball {
    public Rect rect;
    public Rect leftPaddle, rightPaddle;

    private double vx = -100.0;
    private double vy = 150.0;
    private static final int INTERVAL_SECONDS = 1;
    private static final double SPEED_INCREMENT = 1;
    private double lastUpdateTime;

    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    public void reset() {
        lastUpdateTime = Time.getTime();
        this.vx = -100.0;
        this.vy = 150.0;
        this.leftPaddle.x = 40;
        this.leftPaddle.y = (double) (Constants.SCREEN_HEIGHT / 2) - (Constants.PADDLE_HEIGHT / 2);
        this.rightPaddle.x = Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - 40;
        this.rightPaddle.y = (double) (Constants.SCREEN_HEIGHT / 2) - (Constants.PADDLE_HEIGHT / 2);
        this.rect.y = (double) Constants.SCREEN_HEIGHT / 2;
        this.rect.x = (double) Constants.SCREEN_WIDTH / 2;
    }

    public void increaseSpeed() {
        double currentTime = Time.getTime();
        if (currentTime - lastUpdateTime >= INTERVAL_SECONDS) {
            // vx += SPEED_INCREMENT;
            vy += SPEED_INCREMENT;

            vx += vx < 0 ? -SPEED_INCREMENT : SPEED_INCREMENT;
            lastUpdateTime = currentTime;
        }

    }

    public void update(double dt) {
        increaseSpeed();
        if(vx < 0) {
            if(this.rect.x <= this.leftPaddle.x + this.leftPaddle.width && this.rect.x >= this.leftPaddle.x &&
                this.rect.y >= this.leftPaddle.y && this.rect.y <= this.leftPaddle.y + this.leftPaddle.height) {
                this.vx *= -1;
            } else if(this.rect.x + this.rect.width < this.leftPaddle.x) {
                // System.out.println("Player one lost a point");
                reset();
            }
        } else if(vx > 0) {
            if(this.rect.x + this.rect.width >= this.rightPaddle.x && this.rect.x <= this.rightPaddle.x + this.rightPaddle.width &&
                    this.rect.y >= this.rightPaddle.y && this.rect.y <= this.rightPaddle.y + this.rightPaddle.height) {
                this.vx *= -1;
            } else if(this.rect.x + this.rect.width > this.rightPaddle.x + this.rightPaddle.width) {
                // System.out.println("Player two lost a point");
                reset();
            }
        }

        if(vy > 0) {
            if(this.rect.y + this.rect.height > Constants.SCREEN_HEIGHT) {
                this.vy *= -1;
            }
        } else if(vy < 0) {
            if(this.rect.y < Constants.TOOLBAR_HEIGHT) {
                this.vy *= -1;
            }
        }

        this.rect.x += vx * dt;
        this.rect.y += vy * dt;
    }
}
