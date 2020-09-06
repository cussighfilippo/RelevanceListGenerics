package it.uniud.relevancelistgenerics.operator;import java.util.List;

import it.uniud.relevancelistgenerics.problem.RLAbstractProblem;
import it.uniud.relevancelistgenerics.solution.RLIntegerSolution;

@SuppressWarnings("serial")
public class RLIntegerCrossover extends RLAbstractCrossover<RLIntegerSolution, Integer>{

	public RLIntegerCrossover(double crossoverProbability,
			RLAbstractProblem<RLIntegerSolution, Integer> problem) {
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
