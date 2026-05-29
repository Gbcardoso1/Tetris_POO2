package tetris.model;

public class Board {

    private final int rows;
    private final int cols;

    private int[][] grid;

    public Board(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        this.grid = new int[rows][cols];
    }

    public int[][] getGrid() {
        return grid;
    }

    public boolean hasCollision(int x, int y, int[][] shape) {

        for (int row = 0; row < shape.length; row++) {

            for (int col = 0; col < shape[row].length; col++) {

                if (shape[row][col] != 0) {

                    int newX = x + col;
                    int newY = y + row;

                    if (newX < 0 || newX >= cols || newY >= rows) {
                        return true;
                    }

                    if (newY >= 0 && grid[newY][newX] != 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void fixPiece(Piece piece) {

        for (int row = 0; row < piece.shape.length; row++) {

            for (int col = 0; col < piece.shape[row].length; col++) {

                if (piece.shape[row][col] != 0) {

                    int boardX = piece.x + col;
                    int boardY = piece.y + row;

                    if (boardY >= 0) {
                        grid[boardY][boardX] = piece.getColorRGB();
                    }
                }
            }
        }
    }

    public int clearLines() {

        int cleared = 0;

        for (int row = rows - 1; row >= 0; row--) {

            boolean fullLine = true;

            for (int col = 0; col < cols; col++) {

                if (grid[row][col] == 0) {
                    fullLine = false;
                    break;
                }
            }

            if (fullLine) {

                cleared++;

                for (int r = row; r > 0; r--) {

                    grid[r] = grid[r - 1].clone();
                }

                grid[0] = new int[cols];

                row++;
            }
        }

        return cleared;
    }
}