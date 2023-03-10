import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private BlockPanel blockPanel;
    private boolean running;

    private Image renderSurface;

    private Thread gameThread;

    // key states
    private boolean moveLeft, moveRight, moveDown, turnLeft, turnRight;
    
    public GamePanel() {
        super(false);

        blockPanel = new BlockPanel();
    }

    public void startGame() {
        running = true;

        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        while (running) {
            render();

            update();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveLeft = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveRight = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            moveDown = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            turnLeft = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            turnRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    private void render() {
        if (renderSurface == null) {
            renderSurface = createImage(800, 600);
        }

        Graphics graphics = renderSurface.getGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        blockPanel.render(graphics);

        getGraphics().drawImage(renderSurface, 0, 0, null);
        Toolkit.getDefaultToolkit().sync();
    }

    private void update() {
        if (moveLeft) {
            blockPanel.moveBlock(-1, 0);
            moveLeft = false;
        } else if (moveRight) {
            blockPanel.moveBlock(1, 0);
            moveRight = false;
        }
        
        if (moveDown) {
            blockPanel.moveBlock(0, 1);
            moveDown = false;
        }
        
        if (turnLeft) {
            blockPanel.turnBlockLeft();
            turnLeft = false;
        } else if (turnRight) {
            blockPanel.turnBlockRight();
            turnRight = false;
        }

        blockPanel.update();
    }
}
