package tetris.service;

import org.junit.jupiter.api.Test;

import tetris.model.GameState;
import tetris.model.Piece;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class SaveServiceTest {

    @Test
    public void shouldSaveAndLoadGame() {

        int[][] board = new int[20][10];

        Piece current = new Piece(
                new int[][]{
                        {1, 1},
                        {1, 1}
                },
                Color.YELLOW
        );

        Piece next = new Piece(
                new int[][]{
                        {1, 1, 1, 1}
                },
                Color.CYAN
        );

        GameState gameState = new GameState(
                board,
                current,
                next,
                500,
                2
        );

        SaveService service = new SaveService();

        service.saveGame(gameState);

        GameState loaded = service.loadGame();

        assertNotNull(loaded);

        assertEquals(500, loaded.getScore());

        assertEquals(2, loaded.getLevel());
    }
}