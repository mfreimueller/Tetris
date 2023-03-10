import java.awt.Graphics;

/**
 * 22 x 12
 */
public class BlockPanel {

    public static final int PANEL_WIDTH = 12;
    public static final int PANEL_HEIGHT = 22;

    private Block[][] blocks;

    private CompoundBlock currentBlock;
    
    public BlockPanel() {
        initialize();
    }

    private void initialize() {
        // create two-dimensional array first
        blocks = new Block[PANEL_HEIGHT][];
        for (int y = 0; y < PANEL_HEIGHT; y++) {
            blocks[y] = new Block[PANEL_WIDTH];

            // and populate with border blocks
            boolean isTopOrBottomRow = (y == 0 || y == PANEL_HEIGHT - 1);
            for (int x = 0; x < PANEL_WIDTH; x++) {
                boolean isLeftOrRight = (x == 0 || x == PANEL_WIDTH - 1);
                
                if (isLeftOrRight || isTopOrBottomRow) {
                    addNewBlock(x, y, Block.Color.Gray, Block.Type.Border);
                }
            }
        }
    }

    public void addNewBlock(int x, int y, Block.Color color, Block.Type type) {
        if ((x < 0 || x >= PANEL_WIDTH) || (y < 0 || y >= PANEL_HEIGHT)) {
            throw new IllegalArgumentException("Coordinates for new block are outside the bounds of the panel.");
        }

        // TODO: what happens if block already exists?
        blocks[y][x] = new Block(color, x, y, type);
    }

    public void turnBlockLeft() {

    }

    public void turnBlockRight() {

    }

    public void moveBlock(int deltaX, int deltaY) {
        if (currentBlock == null) {
            return;
        }

        int newX = currentBlock.getX() + deltaX;
        int newY = currentBlock.getY() + deltaY;

        if ((newX < 0 || newX >= PANEL_WIDTH) || (newY < 0 || newY >= PANEL_HEIGHT)) {
            return; // silently ignore move if out of bounds
        } else if (blocks[newY][newX] != null) {
            return; // silently ignore move if it would collide with another block
        }

        currentBlock.setX(newX);
        currentBlock.setY(newY);
    }

    public void render(Graphics g) {
        for (int y = 0; y < PANEL_HEIGHT; y++) {
            for (int x = 0; x < PANEL_WIDTH; x++) {
                if (blocks[y][x] != null) {
                    blocks[y][x].draw(g);
                }
            }
        }

        if (currentBlock != null) {
            currentBlock.draw(g);
        }
    }

    public void update() {
        if (currentBlock == null) {
            currentBlock = new CompoundBlock(Block.Color.random(), 4, 1, Block.Type.Solid, CompoundBlock.Form.random());
        } else {
            int newY = currentBlock.getY() + 1;

            // make current block immovable if it vertically collides with another block
            if (blocks[newY][currentBlock.getX()] != null) {
                blocks[currentBlock.getY()][currentBlock.getX()] = currentBlock;
                currentBlock = null;

                // test for complete row
                boolean rowIsComplete = true;
                for (int x = 1; x < PANEL_WIDTH - 1; x++) {
                    if (blocks[newY - 1][x] == null) {
                        rowIsComplete = false;
                        break;
                    }
                }

                // if we have a complete row, delete row and increase score
                if (rowIsComplete) {
                    for (int x = 1; x < PANEL_WIDTH - 2; x++) {
                        blocks[newY - 1][x] = null;
                    }
                }
            } else {
                currentBlock.setY(newY);
            }
        }
    }

}
