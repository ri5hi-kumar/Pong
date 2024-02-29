import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rect {
    public double x, y, height, width;
    Color color;

    public Rect(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fill(new Rectangle2D.Double(x, y, width, height));
    }
}
