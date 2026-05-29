package tetris.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;

public class MenuFrame extends JFrame {

    public MenuFrame() {

        setTitle("Tetris");

        setSize(300, 200);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton rankingButton = new JButton("Ranking");

        rankingButton.addActionListener(e -> {

            new RankingFrame();
        });

        JButton newGameButton = new JButton("New Game");

        JButton loadGameButton = new JButton("Load Game");

        newGameButton.addActionListener(e -> {

            openGame(false);
        });

        loadGameButton.addActionListener(e -> {

            openGame(true);
        });

        panel.add(newGameButton);

        panel.add(loadGameButton);

        panel.add(rankingButton);

        add(panel);

        setVisible(true);
    }

    private void openGame(boolean loadGame) {

        JFrame frame = new JFrame("Tetris");

        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);

        frame.pack();

        frame.setResizable(false);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        if (loadGame) {

            gamePanel.loadGame();
        } else {

            gamePanel.startGame();
        }

        dispose();
    }
}