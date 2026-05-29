package tetris.ui;

import javax.swing.JButton;
import tetris.model.Score;
import tetris.service.ScoreService;
import javax.swing.JPanel;

import tetris.model.Piece;
import tetris.model.Score;
import tetris.model.Tetromino;
import tetris.service.ScoreService;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    private Piece nextPiece;

    private int score = 0;

    private int level = 1;

    private int gameSpeed = 500;

    private final int ROWS = 20;
    private final int COLS = 10;
    private final int BLOCK_SIZE = 30;

    private final int WIDTH = (COLS * BLOCK_SIZE) + 150;
    private final int HEIGHT = ROWS * BLOCK_SIZE;

    private int[][] board = new int[ROWS][COLS];

    private Thread gameThread;

    private boolean running = false;
    private Piece currentPiece;

    private boolean gameOver = false;
    private JButton restartButton;

    public GamePanel() {

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);

        restartButton = new JButton("Restart");

        restartButton.setBounds(320, 250, 120, 40);

        restartButton.addActionListener(e -> resetGame());

        add(restartButton);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (gameOver)
                    return;

                switch (e.getKeyCode()) {

                    case KeyEvent.VK_LEFT:
                        movePiece(-1, 0);
                        break;

                    case KeyEvent.VK_RIGHT:
                        movePiece(1, 0);
                        break;

                    case KeyEvent.VK_DOWN:
                        movePiece(0, 1);
                        break;

                    case KeyEvent.VK_UP:
                        rotatePiece();
                        break;
                }

                repaint();
            }
        });
    }

    public void startGame() {

        nextPiece = Tetromino.getRandomPiece();

        spawnPiece();

        running = true;

        gameThread = new Thread(() -> {

            while (running) {

                try {

                    Thread.sleep(gameSpeed);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                if (!movePiece(0, 1)) {

                    fixPiece();

                    int cleared = clearLines();

                    updateScore(cleared);

                    spawnPiece();
                }

                repaint();
            }
        });

        gameThread.start();
    }

    private void spawnPiece() {

        currentPiece = nextPiece;

        nextPiece = Tetromino.getRandomPiece();

        currentPiece.x = 3;
        currentPiece.y = 0;

        if (collision(currentPiece.x, currentPiece.y, currentPiece.shape)) {

            gameOver = true;

            running = false;
            String player = javax.swing.JOptionPane.showInputDialog("Player name:");

            Score scoreObj = new Score(player, score);

            new ScoreService().saveScore(scoreObj);
        }
    }

    private boolean movePiece(int dx, int dy) {

        if (!collision(currentPiece.x + dx, currentPiece.y + dy, currentPiece.shape)) {

            currentPiece.x += dx;
            currentPiece.y += dy;
            return true;
        }

        return false;
    }

    private void rotatePiece() {

        int[][] rotated = rotateMatrix(currentPiece.shape);

        if (!collision(currentPiece.x, currentPiece.y, rotated)) {

            currentPiece.shape = rotated;
        }
    }

    private int[][] rotateMatrix(int[][] matrix) {

        int size = matrix.length;

        int[][] rotated = new int[size][size];

        for (int row = 0; row < size; row++) {

            for (int col = 0; col < size; col++) {

                rotated[col][size - 1 - row] = matrix[row][col];
            }
        }

        return rotated;
    }

    private boolean collision(int x, int y, int[][] shape) {

        for (int row = 0; row < shape.length; row++) {

            for (int col = 0; col < shape[row].length; col++) {

                if (shape[row][col] != 0) {

                    int newX = x + col;
                    int newY = y + row;

                    if (newX < 0 || newX >= COLS || newY >= ROWS) {
                        return true;
                    }

                    if (newY >= 0 && board[newY][newX] != 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void fixPiece() {

        for (int row = 0; row < currentPiece.shape.length; row++) {

            for (int col = 0; col < currentPiece.shape[row].length; col++) {

                if (currentPiece.shape[row][col] != 0) {

                    int boardX = currentPiece.x + col;
                    int boardY = currentPiece.y + row;

                    if (boardY >= 0) {
                        board[boardY][boardX] = currentPiece.color.getRGB();
                    }
                }
            }
        }
    }

    private int clearLines() {

        int cleared = 0;

        for (int row = ROWS - 1; row >= 0; row--) {

            boolean fullLine = true;

            for (int col = 0; col < COLS; col++) {

                if (board[row][col] == 0) {

                    fullLine = false;
                    break;
                }
            }

            if (fullLine) {

                cleared++;

                for (int r = row; r > 0; r--) {

                    board[r] = board[r - 1].clone();
                }

                board[0] = new int[COLS];

                row++;
            }
        }

        return cleared;
    }

    private void updateScore(int lines) {

        score += lines * 100;

        level = (score / 500) + 1;

        gameSpeed = Math.max(100, 500 - ((level - 1) * 50));

    }

    @Override
protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    g.setColor(Color.WHITE);

    g.setFont(new Font("Arial", Font.PLAIN, 18));

    g.drawString("Score: " + score, 10, 20);

    g.drawString("Level: " + level, 10, 45);

    drawNextPiece(g);

    drawBoard(g);

    drawPiece(g);

    if (gameOver) {

        g.setColor(Color.WHITE);

        g.setFont(new Font("Arial", Font.BOLD, 40));

        g.drawString("GAME OVER", 25, HEIGHT / 2);
    }
}
    private void drawNextPiece(Graphics g) {

        g.setColor(Color.WHITE);

        g.drawString("Next:", 330, 50);

        g.setColor(nextPiece.color);

        for (int row = 0; row < nextPiece.shape.length; row++) {

            for (int col = 0; col < nextPiece.shape[row].length; col++) {

                if (nextPiece.shape[row][col] != 0) {

                    int x = 330 + (col * BLOCK_SIZE);
                    int y = 70 + (row * BLOCK_SIZE);

                    g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

                    g.setColor(Color.BLACK);

                    g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

                    g.setColor(nextPiece.color);
                }
            }
        }
    }

    private void drawBoard(Graphics g) {

        for (int row = 0; row < ROWS; row++) {

            for (int col = 0; col < COLS; col++) {

                if (board[row][col] != 0) {

                    g.setColor(new Color(board[row][col]));

                    g.fillRect(
                            col * BLOCK_SIZE,
                            row * BLOCK_SIZE,
                            BLOCK_SIZE,
                            BLOCK_SIZE);

                    g.setColor(Color.BLACK);

                    g.drawRect(
                            col * BLOCK_SIZE,
                            row * BLOCK_SIZE,
                            BLOCK_SIZE,
                            BLOCK_SIZE);
                }
            }
        }
    }

    private void drawPiece(Graphics g) {

        g.setColor(currentPiece.color);

        for (int row = 0; row < currentPiece.shape.length; row++) {

            for (int col = 0; col < currentPiece.shape[row].length; col++) {

                if (currentPiece.shape[row][col] != 0) {

                    int x = (currentPiece.x + col) * BLOCK_SIZE;
                    int y = (currentPiece.y + row) * BLOCK_SIZE;

                    g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

                    g.setColor(currentPiece.color);
                }
            }
        }
    }

    private void resetGame() {

        board = new int[ROWS][COLS];

        score = 0;

        level = 1;

        gameSpeed = 500;

        gameOver = false;

        nextPiece = Tetromino.getRandomPiece();

        spawnPiece();

        requestFocusInWindow();

        running = false;

        startGame();
        repaint();
    }
}