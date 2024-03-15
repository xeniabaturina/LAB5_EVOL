package lab4;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

class QueenFactory extends AbstractCandidateFactory<QueenSolution> {
    private final int boardSize;

    public QueenFactory(int boardSize) {
        this.boardSize = boardSize;
    }

    public QueenSolution generateRandomCandidate(Random random) {
        int[] board = new int[boardSize];

        for (int i = 0; i < boardSize; i++) {
            board[i] = random.nextInt(boardSize);
        }

        return new QueenSolution(board);
    }
}
