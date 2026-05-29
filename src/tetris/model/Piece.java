package tetris.model;

import java.awt.Color;

public class Piece {

    public int[][] shape;

    public int x;
    public int y;

    public Color color;

    public Piece(int[][] shape, Color color) {

        this.shape = shape;
        this.color = color;
    }
}