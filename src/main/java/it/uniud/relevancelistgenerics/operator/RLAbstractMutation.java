package it.uniud.relevancelistgenerics.operator;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;
import it.uniud.relevancelistgenerics.solution.RLAbstractSolution;

@SuppressWarnings("serial")
public abstract class RLAbstractMutation<T extends RLAbstractSolution<?, V>, V> implements MutationOperator<T>{
	
	double probability;
	JMetalRandom randomGenerator;
	EnumeratedIntegerDistribution distribution; 
	
	public RLAbstractMutation(double mutationProbability, EnumeratedIntegerDistribution dist ) {
		this.probability = mutationProbability;
		this.randomGenerator = JMetalRandom.getInstance();
		this.distribution = dist;
	}

	@Override
	public T execute(T solution) {
		if(randomGenerator.nextDouble() < probability){
			if(randomGenerator.nextDouble() > 0.5){
				swapMutation(solution);
				//sumMutation(solution);
			}else{
				//swapMutation(solution);
				sumMutation(solution);
			}
		}
		return null;
	}
	
	abstract void swapMutation(T solution);
	
	abstract void sumMutation(T solution);
	

	@Override
	public double getMutationProbability() {
		return probability;
	}
}
