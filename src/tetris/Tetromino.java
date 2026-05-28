
package tetris;

/**
 * Representa uma peça do Tetris utilizando matriz.
 */
public class Tetromino {

    private final int[][] forma;
    private final int tipo;

    public Tetromino(int[][] forma, int tipo) {
        this.forma = forma;
        this.tipo = tipo;
    }

    public int[][] getForma() {
        return forma;
    }

    public int getTipo() {
        return tipo;
    }

    public int getLargura() {
        return forma[0].length;
    }

    public int getAltura() {
        return forma.length;
    }

    /**
     * Rotaciona a peça 90 graus no sentido horário.
     */
    public Tetromino rotacionar() {
        int linhas = forma.length;
        int colunas = forma[0].length;

        int[][] novaForma = new int[colunas][linhas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                novaForma[j][linhas - 1 - i] = forma[i][j];
            }
        }

        return new Tetromino(novaForma, tipo);
    }
}
