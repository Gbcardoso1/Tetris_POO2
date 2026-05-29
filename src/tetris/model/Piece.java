package tetris.model;

import java.awt.Color;

public class Piece {

    public int[][] shape;

    public int x;

    public int y;

    private int colorRGB;

    public Piece(int[][] shape, Color color) {

        this.shape = shape;

        this.colorRGB = color.getRGB();
    }

    public Color getColor() {

        return new Color(colorRGB);
    }

    public int getColorRGB() {

        return colorRGB;
    }
}