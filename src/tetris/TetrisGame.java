package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Classe principal do jogo.
 */
public class TetrisGame {

    private final Board board;
    private Tetromino pecaAtual;

    private int x;
    private int y;

    private final TetrisPanel painel;
    private final JLabel pontuacao;

    private int pontos = 0;
    private final Random random = new Random();
    private Timer timer;

    public TetrisGame() {

        board = new Board();

        JFrame janela = new JFrame("Tetris - POO II");

        painel = new TetrisPanel(board);
        painel.setJogo(this);

        pontuacao = new JLabel("Pontuação: 0");

        janela.setLayout(new BorderLayout());
        janela.add(painel, BorderLayout.CENTER);
        janela.add(pontuacao, BorderLayout.SOUTH);

        janela.pack();
        janela.setResizable(false);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        janela.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                movimentar(e);
            }
        });

        gerarNovaPeca();

        timer = new Timer(500, e -> atualizarJogo());
        timer.start();

        janela.setVisible(true);
    }

    private void atualizarJogo() {
        if (!board.colisao(pecaAtual, x, y + 1)) {
            y++;
        } else {
            board.fixarPeca(pecaAtual, x, y);

            int removidas = board.removerLinhasCompletas();

            if (removidas > 0) {
                pontos += removidas * 100;
                pontuacao.setText("Pontuação: " + pontos);
            }

            gerarNovaPeca();

            if (board.colisao(pecaAtual, x, y)) {
                timer.stop();
                JOptionPane.showMessageDialog(painel,
                        "Game Over!\nPontuação final: " + pontos);
                System.exit(0);
            }
        }

        painel.repaint();
    }

    private void gerarNovaPeca() {
        Tetromino[] pecas = {
                TetrominoFactory.criarI(),
                TetrominoFactory.criarO(),
                TetrominoFactory.criarT(),
                TetrominoFactory.criarL(),
                TetrominoFactory.criarJ(),
                TetrominoFactory.criarS(),
                TetrominoFactory.criarZ()
        };

        pecaAtual = pecas[random.nextInt(pecas.length)];
        x = Board.COLUNAS / 2 - 1;
        y = 0;
    }

    private void movimentar(KeyEvent e) {

        int novoX = x;
        int novoY = y;
        Tetromino novaPeca = pecaAtual;

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                novoX--;
                break;

            case KeyEvent.VK_RIGHT:
                novoX++;
                break;

            case KeyEvent.VK_DOWN:
                novoY++;
                break;

            case KeyEvent.VK_UP:
                novaPeca = pecaAtual.rotacionar();
                break;

            case KeyEvent.VK_SPACE:
                while (!board.colisao(pecaAtual, novoX, novoY + 1)) {
                    novoY++;
                }
                break;

            default:
                return;
        }

        if (!board.colisao(novaPeca, novoX, novoY)) {
            x = novoX;
            y = novoY;
            pecaAtual = novaPeca;
        }

        painel.repaint();
    }

    public Tetromino getPecaAtual() {
        return pecaAtual;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TetrisGame::new);
    }
}
