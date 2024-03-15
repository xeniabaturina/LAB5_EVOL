package lab4;

import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import java.util.ArrayList;
import java.util.Random;

public class NQueens {

    private static final int boardSize = 64;

    public static void main(String[] args) {
        int populationSize = 2;
        final int generations = 10000; // Adjust the number of generations as needed
        final int experiments = 10;

        Random random = new Random();

        CandidateFactory<QueenSolution> factory = new QueenFactory(boardSize);

        ArrayList<EvolutionaryOperator<QueenSolution>> operators = new ArrayList<>();
        operators.add(new QueenCrossover());
        operators.add(new QueenMutation());
        EvolutionPipeline<QueenSolution> pipeline = new EvolutionPipeline<>(operators);

        SelectionStrategy<Object> selection = new RouletteWheelSelection();

        FitnessEvaluator<QueenSolution> evaluator = new QueenFitnessFunction();

        // Variables to store data for each experiment
        ArrayList<Integer> bestFitsList = new ArrayList<>();
        ArrayList<Integer> generationsList = new ArrayList<>();

        for (int experiment = 0; experiment < experiments; experiment++) {
            EvolutionEngine<QueenSolution> algorithm = new SteadyStateEvolutionEngine<>(
                    factory, pipeline, evaluator, selection, populationSize, false, random);

            int finalExperiment = experiment;
            final int[] bestGeneration = {0};
            final int[] bestBestFit = {1000};
            algorithm.addEvolutionObserver(new EvolutionObserver<QueenSolution>() {
                public void populationUpdate(PopulationData<? extends QueenSolution> populationData) {
                    int generationNumber = populationData.getGenerationNumber();
                    QueenSolution best = populationData.getBestCandidate();
                    double bestFit = populationData.getBestCandidateFitness();

                    System.out.println("Experiment " + (finalExperiment + 1) +
                            ", Generation " + generationNumber + ": " + bestFit);

                    if (bestFit < bestBestFit[0]) {
                        bestBestFit[0] = (int) bestFit;
                        bestGeneration[0] = generationNumber;
                    }

                    if (generationNumber == generations - 1) {
                        bestFitsList.add(bestBestFit[0]);
                        generationsList.add(bestGeneration[0]);

                        if (finalExperiment == experiments - 1) {
                            printBoard(best);
                        }
                    }
                }
            });

            TerminationCondition terminate = new GenerationCount(generations);
            algorithm.evolve(populationSize, 1, terminate);
        }

        System.out.println("\nBest Fits List: " + bestFitsList);
        System.out.println("Generations List: " + generationsList);

        double sumBestFit = 0.0;
        double sumGenerations = 0.0;

        for (Integer bestFit : bestFitsList) {
            sumBestFit += bestFit;
        }
        for (Integer generation : generationsList) {
            sumGenerations += generation;
        }

        double averageBestFit = sumBestFit / experiments;
        double averageGenerations = sumGenerations / experiments;

        System.out.println("Average Best Fit: " + averageBestFit);
        System.out.println("Average Generations: " + averageGenerations);
    }

    private static void printBoard(QueenSolution solution) {
        int[] board = solution.getBoard();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
