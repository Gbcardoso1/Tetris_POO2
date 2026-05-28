
package tetris;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void deveDetectarColisaoNaParede() {

        Board board = new Board();
        Tetromino peca = TetrominoFactory.criarO();

        assertTrue(board.colisao(peca, -1, 0));
    }

    @Test
    public void deveRemoverLinhaCompleta() {

        Board board = new Board();

        for (int i = 0; i < Board.COLUNAS; i++) {
            board.getGrid()[19][i] = 1;
        }

        int removidas = board.removerLinhasCompletas();

        assertEquals(1, removidas);
    }
}
