package it.uniud.relevancelistgenerics.operator;

import java.util.List;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;

import it.uniud.relevancelistgenerics.solution.RLIntegerSolution;

@SuppressWarnings("serial")
public class RLIntegerMutation extends RLAbstractMutation<RLIntegerSolution, Integer>{

	public RLIntegerMutation(double mutationProbability, EnumeratedIntegerDistribution dist) {
		super(mutationProbability, dist);
	}

	@Override
	void swapMutation(RLIntegerSolution solution) {
		
		// to complete
		
	}

	@Override
	void sumMutation(RLIntegerSolution solution) {
		
		// to complete
		
	}

}
