package lab4;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class QueenCrossover implements EvolutionaryOperator<QueenSolution> {
    public List<QueenSolution> apply(List<QueenSolution> selectedCandidates, Random random) {
        QueenSolution parent1 = selectedCandidates.get(0);
        QueenSolution parent2 = selectedCandidates.get(1);

        int[] board1 = parent1.getBoard();
        int[] board2 = parent2.getBoard();

        int crossoverPoint = random.nextInt(board1.length);

        int[] childBoard1 = new int[board1.length];
        int[] childBoard2 = new int[board1.length];

        System.arraycopy(board1, 0, childBoard1, 0, crossoverPoint);
        System.arraycopy(board2, crossoverPoint, childBoard1, crossoverPoint, board1.length - crossoverPoint);

        System.arraycopy(board2, 0, childBoard2, 0, crossoverPoint);
        System.arraycopy(board1, crossoverPoint, childBoard2, crossoverPoint, board1.length - crossoverPoint);

        List<QueenSolution> children = new ArrayList<QueenSolution>();
        children.add(new QueenSolution(childBoard1));
        children.add(new QueenSolution(childBoard2));

        return children;
    }
}
