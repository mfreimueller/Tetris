import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

/**
 * 22 x 12
 */
public class BlockPanel extends JPanel {

    public static final int PANEL_WIDTH = 12;
    public static final int PANEL_HEIGHT = 22;

    private Block[][] blocks;

    private Block currentBlock;
    
    public BlockPanel() {
        super();

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
            currentBlock = new Block(Block.Color.random(), 4, 1, Block.Type.Solid);
            currentBlock.setIsMoving(true);
        } else {
            int newY = currentBlock.getY() + 1;
            if (blocks[newY][currentBlock.getX()] != null) {
                blocks[currentBlock.getY()][currentBlock.getX()] = currentBlock;
                currentBlock = null;
            } else {
                currentBlock.setY(newY);
            }
        }
    }

}
