package tetris.model;

import java.awt.Color;
import java.util.Random;

public class Tetromino {

    private static final Random random = new Random();

    private static final int[][][] SHAPES = {

            {
                    {1, 1, 1, 1}
            },

            {
                    {1, 1},
                    {1, 1}
            },

            {
                    {0, 1, 0},
                    {1, 1, 1}
            },

            {
                    {1, 0, 0},
                    {1, 1, 1}
            },

            {
                    {0, 0, 1},
                    {1, 1, 1}
            },

            {
                    {0, 1, 1},
                    {1, 1, 0}
            },

            {
                    {1, 1, 0},
                    {0, 1, 1}
            }
    };

    private static final Color[] COLORS = {

            Color.CYAN,
            Color.YELLOW,
            Color.MAGENTA,
            Color.ORANGE,
            Color.BLUE,
            Color.GREEN,
            Color.RED
    };

    public static Piece getRandomPiece() {

        int index = random.nextInt(SHAPES.length);

        return new Piece(SHAPES[index], COLORS[index]);
    }
}