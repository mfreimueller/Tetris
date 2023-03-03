import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class BlockPanel extends JPanel {

    private Block[] blocks;
    
    public BlockPanel() {
        super();

        blocks = new Block[16];
        blocks[0] = new Block(Color.GREEN, 50, 50, 100, 50);
    }

    public void render(Graphics g) {
        for (int x = 0; x < blocks.length; x++) {
            if (blocks[x] != null) {
                blocks[x].draw(g);
            }
        }
    }

    public void update() {
        for (int x = 0; x < blocks.length; x++) {
            if (blocks[x] != null) {
                blocks[x].update();
            }
        }
    }

}
