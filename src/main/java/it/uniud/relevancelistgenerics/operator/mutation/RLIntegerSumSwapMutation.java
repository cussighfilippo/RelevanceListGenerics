package it.uniud.relevancelistgenerics.operator.mutation;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;

import it.uniud.relevancelistgenerics.solution.RLIntegerSolution;

@SuppressWarnings("serial")
public class RLIntegerSumSwapMutation extends RLAbstractSumSwapMutation<RLIntegerSolution, Integer>{

	public RLIntegerSumSwapMutation(double mutationProbability, EnumeratedIntegerDistribution dist) {
		super(mutationProbability, dist);
	}

	@Override
	void swapMutation(RLIntegerSolution solution) {
		
		// to complete
		System.err.print("IntegerMutation not yet implemented");
		System.exit(1);
	}

	@Override
	void sumMutation(RLIntegerSolution solution) {
		
		// to complete
		System.err.print("IntegerMutation not yet implemented");
		System.exit(1);
	}

}
