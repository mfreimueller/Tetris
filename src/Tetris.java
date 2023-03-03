import javax.swing.JFrame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;

public class Tetris extends JFrame implements WindowListener {

    public static void main(String[] args) {
        new Tetris();
    }

    private GamePanel gamePanel;

    public Tetris() {
        super("Tetris");

        addWindowListener(this);
        
        gamePanel = new GamePanel();
        Container cp = getContentPane();
        cp.add(gamePanel, "Center");

        setPreferredSize(new Dimension(800, 600));
        
        pack();
        
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        gamePanel.startGame();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }

}