

import javax.swing.JFrame;

import tetris.ui.GamePanel;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Tetris");

        GamePanel panel = new GamePanel();

        frame.add(panel);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.startGame();
    }
}