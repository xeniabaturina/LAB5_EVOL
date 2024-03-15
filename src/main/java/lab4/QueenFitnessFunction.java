package lab4;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;

class QueenFitnessFunction implements FitnessEvaluator<QueenSolution> {
    public double getFitness(QueenSolution candidate, List<? extends QueenSolution> population) {
        int[] board = candidate.getBoard();
        int conflicts = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = i + 1; j < board.length; j++) {
                if (board[i] == board[j] || Math.abs(i - j) == Math.abs(board[i] - board[j])) {
                    conflicts++;
                }
            }
        }

        return conflicts;
    }

    public boolean isNatural() {
        return false;
    }
}
