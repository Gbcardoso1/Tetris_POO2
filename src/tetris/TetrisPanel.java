package tetris;

import javax.swing.*;
import java.awt.*;

/**
 * Painel responsável pela renderização do tabuleiro.
 */
public class TetrisPanel extends JPanel {

    private final Board board;
    private TetrisGame jogo;

    private static final int TAMANHO_BLOCO = 30;

    private final Color[] cores = {
            Color.BLACK,
            Color.CYAN,
            Color.YELLOW,
            Color.MAGENTA,
            Color.ORANGE,
            Color.BLUE,
            Color.GREEN,
            Color.RED
    };

    public TetrisPanel(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(Board.COLUNAS * TAMANHO_BLOCO, Board.LINHAS * TAMANHO_BLOCO));
        setBackground(Color.BLACK);
    }

    public void setJogo(TetrisGame jogo) {
        this.jogo = jogo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[][] grid = board.getGrid();

        for (int i = 0; i < Board.LINHAS; i++) {
            for (int j = 0; j < Board.COLUNAS; j++) {
                desenharBloco(g, grid[i][j], j, i);
            }
        }

        if (jogo != null) {
            Tetromino peca = jogo.getPecaAtual();
            int[][] forma = peca.getForma();

            for (int i = 0; i < forma.length; i++) {
                for (int j = 0; j < forma[i].length; j++) {
                    if (forma[i][j] != 0) {
                        desenharBloco(g, peca.getTipo(), jogo.getX() + j, jogo.getY() + i);
                    }
                }
            }
        }
    }

    private void desenharBloco(Graphics g, int valor, int x, int y) {
        g.setColor(cores[valor]);
        g.fillRect(x * TAMANHO_BLOCO, y * TAMANHO_BLOCO, TAMANHO_BLOCO, TAMANHO_BLOCO);

        g.setColor(Color.DARK_GRAY);
        g.drawRect(x * TAMANHO_BLOCO, y * TAMANHO_BLOCO, TAMANHO_BLOCO, TAMANHO_BLOCO);
    }
}
