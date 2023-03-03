import java.awt.*;

public class Block implements Drawable {

    public static int BLOCK_WIDTH = 50;
    public static int BLOCK_HEIGHT = 50;

    private Color color;
    private int x, y;
    private int width, height;

    public Block(Color color, int x, int y, int width, int height) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x + 2, y - 2, width, height);

        g.setColor(color);
        g.fillRect(x, y, width, height);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
    
    public void update() {
        y += BLOCK_HEIGHT;
    }
}
