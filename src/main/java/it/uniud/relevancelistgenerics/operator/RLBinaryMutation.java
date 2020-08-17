package it.uniud.relevancelistgenerics.operator;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import org.uma.jmetal.util.binarySet.BinarySet;

import it.uniud.relevancelistgenerics.solution.RLBinarySolution;

@SuppressWarnings("serial")
public class RLBinaryMutation extends RLAbstractMutation<RLBinarySolution,  Boolean>{

	public RLBinaryMutation(double mutationProbability, EnumeratedIntegerDistribution dist) {
		super(mutationProbability, dist);
	}

	@Override
	void swapMutation(RLBinarySolution solution) {
		BinarySet variable =  solution.getVariable(0);
		int swapIndex1 = distribution.sample();
		int swapIndex2 = distribution.sample();
		boolean value1 = variable.get(swapIndex1);
		boolean value2 = variable.get(swapIndex2);
		solution.setSingleValue(0, swapIndex1, value2);
		solution.setSingleValue(0, swapIndex2, value1);
	}

	@Override
	void sumMutation(RLBinarySolution solution) {
		int sumIndex = distribution.sample();
		solution.setSingleValue(0, sumIndex, true);
	}

}
