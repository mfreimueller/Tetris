import java.awt.*;

public interface Drawable {

    void setX(int x);
    void setY(int y);
    int getX();
    int getY();

    void draw(Graphics g);

}
