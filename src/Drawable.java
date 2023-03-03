import java.awt.*;

public interface Drawable {

    void setColor(Color c);
    Color getColor();

    void setPosition(int x, int y);
    int getX();
    int getY();

    void setSize(int width, int height);
    int getWidth();
    int getHeight();

    void draw(Graphics g);

}
