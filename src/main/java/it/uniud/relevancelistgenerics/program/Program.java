package it.uniud.relevancelistgenerics.program;

import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;

import it.uniud.relevancelistgenerics.algorithm.RLNSGAII;
import it.uniud.relevancelistgenerics.algorithm.RLNSGAIIBuilder;
import it.uniud.relevancelistgenerics.operator.RLAbstractCrossover;
import it.uniud.relevancelistgenerics.operator.RLAbstractMutation;
import it.uniud.relevancelistgenerics.operator.RLBinaryCrossover;
import it.uniud.relevancelistgenerics.operator.RLBinaryMutation;
import it.uniud.relevancelistgenerics.problem.RLAbstractProblem;
import it.uniud.relevancelistgenerics.problem.RLBinaryProblem;
import it.uniud.relevancelistgenerics.solution.RLAbstractSolution;
import it.uniud.relevancelistgenerics.solution.RLBinarySolution;
import it.uniud.relevancelistgenerics.solution.factory.RLAbstractSolutionFactory;
import it.uniud.relevancelistgenerics.solution.factory.RLBinarySolutionFactory;
import it.uniud.relevancelistgenerics.solution.factory.RLIntegerSolutionFactory;

import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.selection.*;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;

/**
 * Hello world!
 *
 */
public class Program
{
	public enum EvaluationFunction {avgPrecision, test};
	
	static int populationSize;
	static int maxEvaluations;
	static double crossoverProbability;
	static double mutationProbability;
	static int listLength;
	static int maxCellValue;
	static double targetValue;
	static int relevantDocs;
	static double maxErrTolerance;	
	static int numExperimentIterations;
	static EvaluationFunction evalFunction;
	static String fileName;
	static double fractNonZero;
	
    /**
     * @param args
     * @throws IOException 
     */
    public static void main( String[] args ) throws IOException
    {

    	
    	//cmdline  java -jar .\target\RelevanceList-1.0-SNAPSHOT-jar-with-dependencies.jar 50 50000 0.8 0.3 50 1 0.8957 6 0.00005 10 "avgPrecision" "risultati.csv" 0.1
		// Lettura dei parametri
		
		if(args.length != 13){
			System.err.println("Wrong number of parameters specified: " + args.length + " != 13");
			System.err.println("Parameters: populationSize maxEvaluations crossoverProbability mutationProbability "
								+ "listLength maxCellValue targetValue relevantDocs maxRelTolerance numExperimentIterations "
								+ "funzioneValutazione filePath fractNonZero");
			System.exit(1);
		}
		
		
			
		populationSize = Integer.parseInt(args[0]);
	    maxEvaluations = Integer.parseInt(args[1]);
		crossoverProbability = Double.parseDouble(args[2]);
		mutationProbability = Double.parseDouble(args[3]);
		listLength = Integer.parseInt(args[4]);
		maxCellValue = Integer.parseInt(args[5]);
		targetValue = Double.parseDouble(args[6]);
		relevantDocs = Integer.parseInt(args[7]);
		maxErrTolerance = Double.parseDouble(args[8]);	
		numExperimentIterations = Integer.parseInt(args[9]);
		// avg01Metric , avgPrecisionMetric , ndcgMetric
		
		if (!Arrays.stream(EvaluationFunction.values()).anyMatch((t) -> t.name().equals(args[10]))) {
			System.err.println("Invalid evaluation function: " + args[10]);
			System.err.println("Valid functions: " + Arrays.toString(EvaluationFunction.values()));
			System.exit(1);
		};
		evalFunction = EvaluationFunction.valueOf(args[10]);
		fileName = args[11];
		fractNonZero = Double.parseDouble(args[12]);
		
		
		// Fine lettura dei parametri
		
		//print parametri
		
        System.out.println( "pop size:\t" + populationSize  );
        System.out.println( "max eval:\t" + maxEvaluations  );
        System.out.println( "crossover prob:\t" + crossoverProbability  );
        System.out.println( "mutation prob:\t" + mutationProbability  );
        System.out.println( "list length:\t" + listLength  );
        System.out.println( "max cell value:\t" + maxCellValue  );
        System.out.println( "target value:\t" + targetValue  );
        System.out.println( "relevanct docs:\t" + relevantDocs  );
        System.out.println( "max error:\t" + maxErrTolerance  );
        System.out.println( "n iterations:\t" + numExperimentIterations  );
        System.out.println( "funzione val:\t" + evalFunction.toString()  );
        System.out.println( "filename:\t" + fileName  );
        System.out.println( "fractNonZero:\t" + fractNonZero  );
        System.out.println();
        
        
        // copiato dall'originale 
    	// DETERMINO UNA DISTRIBUZIONE DI PROBABILITA' PER LA LISTA DEI RELEVANT DOCUMENT (USATA PER INIZIALIZZAZIONE E MUTATION)
    	int[] indexValues = new int[listLength];
    	for(int i=0; i<listLength; i++){
    		indexValues[i] = i;
    	}
    	double[] probabilities = new double[listLength];
    	for(int i=0; i<listLength; i++){
    		//probabilities[i] = (float) 1.0/(i+1);
    		probabilities[i] = (float) 1.0/listLength;
    	}
    	EnumeratedIntegerDistribution distribution = new EnumeratedIntegerDistribution(indexValues, probabilities);
    	
    	RLAbstractSolutionFactory factory = new RLBinarySolutionFactory(listLength, relevantDocs, distribution, fractNonZero);
    	RLAbstractProblem problem = new RLBinaryProblem(targetValue, evalFunction, factory);
    	RLAbstractCrossover crossover = new RLBinaryCrossover(crossoverProbability, problem);
        RLAbstractMutation  mutation = new RLBinaryMutation(mutationProbability, distribution);
        SelectionOperator<List<RLAbstractSolution>, RLAbstractSolution> selection = 
        		new BinaryTournamentSelection<RLAbstractSolution>(new RankingAndCrowdingDistanceComparator<RLAbstractSolution>());
        RLNSGAIIBuilder<RLAbstractSolution> builder = new RLNSGAIIBuilder(problem, crossover, mutation, populationSize, maxErrTolerance);
        builder.setSelectionOperator(selection);
        builder.setMaxEvaluations(maxEvaluations);
        RLNSGAII<RLAbstractSolution> algorithm; 
    	
    	
  // evaluation
        
        
        RLAbstractSolution currentBestSolution;
        RLAbstractSolution bestSolution=null;
        double bestError = Double.MAX_VALUE;
        List<RLAbstractSolution> solutions = new ArrayList<RLAbstractSolution>();
        
        int seed =0;
        while( seed < numExperimentIterations && bestError > maxErrTolerance) { 
	        algorithm = builder.build();
	        algorithm.run();
	        currentBestSolution = algorithm.getBestSolution();
	        System.out.println((seed+1) + "° exp Solution" + "\t" + currentBestSolution.getVariable(0).toString() + "\t" + (currentBestSolution.getObjective(0)) + "\t" + currentBestSolution.getNumberOfRelevantDocs());
	        System.out.println();
	        if (bestSolution==null || currentBestSolution.getObjective(0)<bestSolution.getObjective(0) ) {
	        	bestSolution = currentBestSolution;
	        	bestError = bestSolution.getObjective(0);
	        }
	        solutions.add(currentBestSolution);
	        seed++;
        }
        

    }
	
}
