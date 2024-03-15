package lab4;

import java.util.Arrays;

class QueenSolution {
    private final int[] board;

    public QueenSolution(int[] board) {
        this.board = board;
    }

    public int[] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "Board: " + Arrays.toString(board);
    }
}
