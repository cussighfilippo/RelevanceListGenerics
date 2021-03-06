package it.uniud.relevancelistgenerics.problem;


import it.uniud.relevancelistgenerics.metric.MetricEvaluator;
import it.uniud.relevancelistgenerics.solution.RLIntegerSolution;
import it.uniud.relevancelistgenerics.solution.factory.RLIntegerSolutionFactory;

@SuppressWarnings("serial")
public class RLIntegerProblem extends RLAbstractProblem<RLIntegerSolution,  Integer>{

	
	public RLIntegerProblem(double targetValue, MetricEvaluator evaluator,
			RLIntegerSolutionFactory fac) {
		super(targetValue, evaluator, fac);	
	}


	void evaluateObjectives(RLIntegerSolution solution) {
		double actualValue = 100;
		actualValue = evaluator.evaluate(solution);
		solution.setObjective(0, Math.abs(actualValue - targetValue));
	}

	
	void evaluateConstraints(RLIntegerSolution solution) {
		double constraint;
		int numberOfRelevantDocs = solution.getNumberOfRelevantDocs();
		constraint = relevantDocs - numberOfRelevantDocs;
		solution.setConstraint(0, constraint);
	}

	
}