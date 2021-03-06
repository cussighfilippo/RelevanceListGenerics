package it.uniud.relevancelistgenerics.operator.crossover;import java.util.List;

import it.uniud.relevancelistgenerics.problem.RLIntegerProblem;
import it.uniud.relevancelistgenerics.solution.RLIntegerSolution;

@SuppressWarnings("serial")
public class RLIntegerCrossover extends RLAbstractCrossover<RLIntegerSolution, Integer>{

	public RLIntegerCrossover(double crossoverProbability,
			RLIntegerProblem problem) {
		super(crossoverProbability, problem);
	}

	@Override
	public List<RLIntegerSolution> execute(List<RLIntegerSolution> source) {
		
		// to complete
		System.err.print("IntegerCrossover not yet implemented");
		System.exit(1);
		return source;
	}

}
