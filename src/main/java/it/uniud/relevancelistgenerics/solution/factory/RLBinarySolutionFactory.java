package it.uniud.relevancelistgenerics.solution.factory;
import java.util.Arrays;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import org.uma.jmetal.util.binarySet.BinarySet;

import it.uniud.relevancelistgenerics.solution.RLBinarySolution;


public class RLBinarySolutionFactory extends RLAbstractSolutionFactory<RLBinarySolution, Boolean>{

	public RLBinarySolutionFactory(int size, int relDocs, EnumeratedIntegerDistribution distribution,
			double fractNonZero) {
		super(1, size, relDocs, distribution, fractNonZero);
	}

	public RLBinarySolution generateNewSolution() {
		RLBinarySolution newSolution = new RLBinarySolution(createDocumentsSet());
		return newSolution;
	}

	Boolean[] createDocumentsSet() {
		int[] array = createDiscreteDocumentDistribution();
		Boolean[] booleanArray = new Boolean[array.length];
		for (int i=0; i<array.length; i++) if (array[i]==1) booleanArray[i] = true; else booleanArray[i] = false; 
		return booleanArray;
	}

	@Override
	public RLBinarySolution generateNewSolution(Boolean[] docs) {
		if (docs.length != listLength) {
			System.err.println(Arrays.toString(docs) + " incompatible with factory initialization");
			System.err.println("docs length must match declared number of documents listLength of the factory");
			System.exit(1);
		}
		RLBinarySolution newSolution = new RLBinarySolution(docs);
		return newSolution;
	}

}
