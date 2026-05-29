package tetris.model;

public class GameState {

    private int[][] board;

    private Piece currentPiece;

    private Piece nextPiece;

    private int score;

    private int level;

    public GameState(
            int[][] board,
            Piece currentPiece,
            Piece nextPiece,
            int score,
            int level
    ) {

        this.board = board;
        this.currentPiece = currentPiece;
        this.nextPiece = nextPiece;
        this.score = score;
        this.level = level;
    }

    public int[][] getBoard() {
        return board;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public Piece getNextPiece() {
        return nextPiece;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }
}