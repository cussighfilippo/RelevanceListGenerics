package it.uniud.relevancelistgenerics.metric;

import it.uniud.relevancelistgenerics.solution.RLBinarySolution;
import it.uniud.relevancelistgenerics.solution.RLIntegerSolution;

public abstract class MetricEvaluator {

	//returns the evaluation of the metric for the solution
	//doesn't modify solution status
	public abstract double evaluate(RLBinarySolution solution);

	public abstract double evaluate(RLIntegerSolution solution);
}
