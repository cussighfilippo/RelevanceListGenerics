package it.uniud.relevancelistgenerics.solution.factory;

import java.util.Arrays;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import it.uniud.relevancelistgenerics.solution.RLIntegerSolution;

public class RLIntegerSolutionFactory extends RLAbstractSolutionFactory<RLIntegerSolution, Integer>{

	public RLIntegerSolutionFactory(int maxValue, int size, int relDocs, EnumeratedIntegerDistribution distribution,
			double fractNonZero) {
		super( maxValue, size, relDocs, distribution, fractNonZero);
	}

	public RLIntegerSolution generateNewSolution() {
		RLIntegerSolution newSolution = new RLIntegerSolution(nVariables, nObjectives, nConstraints,createRelevanceSet());
		return newSolution;
	}

	Integer[] createRelevanceSet() {
		int[] array = createDiscreteDistribution();
		Integer[] integerArray = new Integer[array.length];
		for (int i=0; i<array.length; i++) integerArray[i] = array[i]; 
		return integerArray;
	}


	public RLIntegerSolution generateNewSolution(Integer[] docs) {
		if (docs.length != listLength) {
			System.err.println(Arrays.toString(docs) + " incompatible with factory initialization");
			System.err.println("docs length must match declared number of documents listLength of the factory");
			System.exit(1);
		}
		RLIntegerSolution newSolution = new RLIntegerSolution(nVariables, nObjectives, nConstraints,docs);
		return newSolution;
	}
	

}
