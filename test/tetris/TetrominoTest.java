
package tetris;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TetrominoTest {

    @Test
    public void deveRotacionarPecaI() {

        Tetromino peca = TetrominoFactory.criarI();
        Tetromino rotacionada = peca.rotacionar();

        assertEquals(1, rotacionada.getLargura());
        assertEquals(4, rotacionada.getAltura());
    }
}
