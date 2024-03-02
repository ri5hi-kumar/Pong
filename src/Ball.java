public class Ball {
    public Rect rect;
    public Rect leftPaddle, rightPaddle;

    private double vx = -170.0;
    private double vy = 310.0;

    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    public void reset() {
        this.leftPaddle.x = 40;
        this.leftPaddle.y = (double) (Constants.SCREEN_HEIGHT / 2) - (Constants.PADDLE_HEIGHT / 2);
        this.rightPaddle.x = Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - 40;
        this.rightPaddle.y = (double) (Constants.SCREEN_HEIGHT / 2) - (Constants.PADDLE_HEIGHT / 2);
        this.rect.y = (double) Constants.SCREEN_HEIGHT / 2;
        this.rect.x = (double) Constants.SCREEN_WIDTH / 2;
    }

    public void update(double dt) {
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
