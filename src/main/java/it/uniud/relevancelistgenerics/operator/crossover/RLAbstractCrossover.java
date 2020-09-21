package it.uniud.relevancelistgenerics.operator.crossover;

import java.util.List;
import it.uniud.relevancelistgenerics.problem.RLAbstractProblem;
import it.uniud.relevancelistgenerics.solution.RLAbstractSolution;


@SuppressWarnings("serial")
public abstract class RLAbstractCrossover<T extends RLAbstractSolution<?, V>, V> implements RLCrossover<T>{
	
	double crossoverProbability;
	RLAbstractProblem<T, V> problem;
	
	public RLAbstractCrossover(double crossoverProbability, RLAbstractProblem<T, V> problem) {
		this.crossoverProbability = crossoverProbability;
		this.problem = problem;
	}

	@Override
	public abstract List<T> execute(List<T> source);

	@Override
	public double getCrossoverProbability() {
		return crossoverProbability;
	}

	@Override
	public int getNumberOfRequiredParents() {
		return 2;
	}

	@Override
	public int getNumberOfGeneratedChildren() {
		return 2;
	}


}