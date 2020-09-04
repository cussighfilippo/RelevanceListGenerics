package it.uniud.relevancelistgenerics.problem;

import it.uniud.relevancelistgenerics.metric.MetricEvaluator;
import it.uniud.relevancelistgenerics.solution.RLBinarySolution;
import it.uniud.relevancelistgenerics.solution.factory.RLAbstractSolutionFactory;

@SuppressWarnings("serial")
public class RLBinaryProblem extends RLAbstractProblem<RLBinarySolution, Boolean>{

	
	public RLBinaryProblem(double targetValue, MetricEvaluator evalFun,
			RLAbstractSolutionFactory<RLBinarySolution, Boolean> fac) {
		super(targetValue, evalFun, fac);	
	}


	void evaluateObjectives(RLBinarySolution solution) {
		double actualValue = 100;
		actualValue = evaluator.evaluate(solution);
		solution.setObjective(0, Math.abs(actualValue - targetValue));
	}


	void evaluateConstraints(RLBinarySolution solution) {
		double constraint;
		int numberOfRelevantDocs = solution.getNumberOfRelevantDocs();
		constraint = relevantDocs - numberOfRelevantDocs;
		solution.setConstraint(0, constraint);
	}
	
	
}
