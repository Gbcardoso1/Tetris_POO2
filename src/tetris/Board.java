
package tetris;

/**
 * Representa o tabuleiro principal do jogo.
 */
public class Board {

    public static final int LINHAS = 20;
    public static final int COLUNAS = 10;

    private final int[][] grid;

    public Board() {
        grid = new int[LINHAS][COLUNAS];
    }

    public int[][] getGrid() {
        return grid;
    }

    /**
     * Verifica colisão da peça.
     */
    public boolean colisao(Tetromino peca, int x, int y) {
        int[][] forma = peca.getForma();

        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {

                if (forma[i][j] != 0) {
                    int novoX = x + j;
                    int novoY = y + i;

                    if (novoX < 0 || novoX >= COLUNAS || novoY >= LINHAS) {
                        return true;
                    }

                    if (novoY >= 0 && grid[novoY][novoX] != 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Fixa a peça no tabuleiro.
     */
    public void fixarPeca(Tetromino peca, int x, int y) {
        int[][] forma = peca.getForma();

        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {

                if (forma[i][j] != 0) {
                    grid[y + i][x + j] = peca.getTipo();
                }
            }
        }
    }

    /**
     * Remove linhas completas e retorna quantidade removida.
     */
    public int removerLinhasCompletas() {
        int removidas = 0;

        for (int i = LINHAS - 1; i >= 0; i--) {

            boolean completa = true;

            for (int j = 0; j < COLUNAS; j++) {
                if (grid[i][j] == 0) {
                    completa = false;
                    break;
                }
            }

            if (completa) {
                removidas++;

                for (int linha = i; linha > 0; linha--) {
                    System.arraycopy(grid[linha - 1], 0, grid[linha], 0, COLUNAS);
                }

                for (int j = 0; j < COLUNAS; j++) {
                    grid[0][j] = 0;
                }

                i++;
            }
        }

        return removidas;
    }
}
