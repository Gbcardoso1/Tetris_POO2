
package tetris;

/**
 * Fábrica responsável pelas peças padrão do Tetris.
 */
public class TetrominoFactory {

    public static Tetromino criarI() {
        return new Tetromino(new int[][]{
                {1, 1, 1, 1}
        }, 1);
    }

    public static Tetromino criarO() {
        return new Tetromino(new int[][]{
                {1, 1},
                {1, 1}
        }, 2);
    }

    public static Tetromino criarT() {
        return new Tetromino(new int[][]{
                {0, 1, 0},
                {1, 1, 1}
        }, 3);
    }

    public static Tetromino criarL() {
        return new Tetromino(new int[][]{
                {1, 0},
                {1, 0},
                {1, 1}
        }, 4);
    }

    public static Tetromino criarJ() {
        return new Tetromino(new int[][]{
                {0, 1},
                {0, 1},
                {1, 1}
        }, 5);
    }

    public static Tetromino criarS() {
        return new Tetromino(new int[][]{
                {0, 1, 1},
                {1, 1, 0}
        }, 6);
    }

    public static Tetromino criarZ() {
        return new Tetromino(new int[][]{
                {1, 1, 0},
                {0, 1, 1}
        }, 7);
    }
}
