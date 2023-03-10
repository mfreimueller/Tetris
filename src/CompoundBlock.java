import java.awt.*;

public class CompoundBlock extends Block {

    public enum Form {
        I,
        L,
        Block;

        public static Form random() {
            Form[] forms = Form.values();
            
            int rndIdx = (int)(Math.random() * forms.length);
            return forms[rndIdx];
        }
    }

    private Form form;
    private Block[] blocks;

    public CompoundBlock(Block.Color color, int x, int y, Block.Type type, Form form) {
        super(color, x, y, type);

        this.form = form;
        initialize();
    }

    private void initialize() {
        switch (form) {
            case I: {
                blocks = new Block[] {
                    new Block(color, getX(), getY(), Block.Type.Solid),
                    new Block(color, getX(), getY() + 1, Block.Type.Solid),
                    new Block(color, getX(), getY() + 2, Block.Type.Solid)
                };

                break;
            }
            case L: {
                blocks = new Block[] {
                    new Block(color, getX(), getY(), Block.Type.Solid),
                    new Block(color, getX(), getY() + 1, Block.Type.Solid),
                    new Block(color, getX(), getY() + 2, Block.Type.Solid),
                    new Block(color, getX() + 1, getY() + 2, Block.Type.Solid)
                };

                break;
            }
            case Block: {
                blocks = new Block[] {
                    new Block(color, getX(), getY(), Block.Type.Solid),
                    new Block(color, getX(), getY() + 1, Block.Type.Solid),
                    new Block(color, getX() + 1, getY(), Block.Type.Solid),
                    new Block(color, getX() + 1, getY() + 1, Block.Type.Solid)
                };

                break;
            }
        }
    }

    public Form getForm() {
        return form;
    }

    

    @Override
    public void setX(int x) {
        int dx = x - this.getX();

        super.setX(x);
        for (Block block : blocks) {
            block.setX(block.getX() + dx);
        }
    }

    @Override
    public void setY(int y) {
        int dy = y - this.getY();

        super.setY(y);
        for (Block block : blocks) {
            block.setY(block.getY() + dy);
        }
    }

    @Override
    public boolean isMoving() {
        return true;
    }

    @Override
    public void draw(Graphics g) {
        for (Block block : blocks) {
            block.draw(g);
        }
    }    
}
