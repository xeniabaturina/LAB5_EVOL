package lab4;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.Collections;
import java.util.List;
import java.util.Random;

class QueenMutation implements EvolutionaryOperator<QueenSolution> {
    public List<QueenSolution> apply(List<QueenSolution> selectedCandidates, Random random) {
        QueenSolution solution = selectedCandidates.get(0);
        int[] board = solution.getBoard();

        int mutationPoint = random.nextInt(board.length);
        int newColumn = random.nextInt(board.length);

        board[mutationPoint] = newColumn;

        return Collections.singletonList(new QueenSolution(board));
    }
}
