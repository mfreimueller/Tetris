import java.awt.*;

public class Block implements Drawable {

    public enum Type {
        Border,
        Solid,
        Blueprint
    }

    public enum Color {
        Gray(0.467f, 0.467f, 0.467f),
        Cyan(0, 0.8f, 0.8f),
        Orange(0.8f, 0.4f, 0),
        Yellow(0.8f, 0.8f, 0),
        Blue(0, 0, 0.8f),
        Purple(0.6f, 0, 0.8f),
        Red(0.8f, 0, 0),
        Green(0, 0.8f, 0);

        public static Color random() {
            Color[] availableColors = { Cyan, Orange, Yellow, Blue, Purple, Red, Green};
            
            int rndIdx = (int)(Math.random() * availableColors.length);
            return availableColors[rndIdx];
        }

        private java.awt.Color color;
        private java.awt.Color lighterColor;
        private java.awt.Color darkerColor;

        private Color(float r, float g, float b) {
            color = new java.awt.Color(r, g, b);

            float rLighter = Math.max(0, r - 0.2f);
            float gLighter = Math.max(0, g - 0.2f);
            float bLighter = Math.max(0, b - 0.2f);
            this.lighterColor = new java.awt.Color(rLighter, gLighter, bLighter);

            float rDarker = Math.min(1, r + 0.2f);
            float gDarker = Math.min(1, g + 0.2f);
            float bDarker = Math.min(1, b + 0.2f);
            this.darkerColor = new java.awt.Color(rDarker, gDarker, bDarker);
        }

        public java.awt.Color getColor() {
            return color;
        }

        public java.awt.Color getLighterColor() {
            return lighterColor;
        }

        public java.awt.Color getDarkerColor() {
            return darkerColor;
        }
    }

    public static int BLOCK_SIZE = 26;

    private boolean moving;

    protected Color color;
    private int x, y;

    private Type type;

    public Block(Color color, int x, int y, Type type) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
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

    public boolean isMoving() {
        return false;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void draw(Graphics g) {
        int x = this.x * BLOCK_SIZE;
        int y = this.y * BLOCK_SIZE;

        if (type == Type.Blueprint) {
            g.setColor(color.getColor());
            g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
        } else {
            // draw the beautiful corners of the block
            // the left and upper side are color - 20.f
            // the right and lower side are color + 20.f
            g.setColor(color.getLighterColor());
            g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

            // draw the darker, lower rect
            g.setColor(color.getDarkerColor());
            g.fillRect(x + 2, y + 2, BLOCK_SIZE - 2, BLOCK_SIZE - 2);

            // draw the regular rect in the middle
            g.setColor(color.getColor());
            g.fillRect(x + 2, y + 2, BLOCK_SIZE - 4, BLOCK_SIZE - 4);
        }
    }
}
