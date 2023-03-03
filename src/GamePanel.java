import java.awt.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    private BlockPanel blockPanel;
    private boolean running;

    private Image renderSurface;

    private Thread gameThread;
    
    public GamePanel() {
        super(false);

        blockPanel = new BlockPanel();
        add(blockPanel, "Center");
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
        blockPanel.update();
    }
}
