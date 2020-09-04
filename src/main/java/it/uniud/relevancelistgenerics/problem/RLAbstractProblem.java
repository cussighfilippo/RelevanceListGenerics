package it.uniud.relevancelistgenerics.problem;

import org.uma.jmetal.problem.AbstractGenericProblem;

import it.uniud.relevancelistgenerics.metric.MetricEvaluator;
import it.uniud.relevancelistgenerics.program.EvaluationFunction;
import it.uniud.relevancelistgenerics.solution.RLAbstractSolution;
import it.uniud.relevancelistgenerics.solution.factory.RLAbstractSolutionFactory;


@SuppressWarnings("serial")
public abstract class RLAbstractProblem<T extends RLAbstractSolution<?, V>,  V> extends AbstractGenericProblem<T> {
	
	public static final int nVariables = 1;
	public static final int nObjectives = 1;
	public static final int nCostraints = 1;

	double targetValue;
	MetricEvaluator evaluator;
	int relevantDocs;
	int listLength;
	RLAbstractSolutionFactory<T ,V> factory;

	public RLAbstractProblem(double targetValue, MetricEvaluator evaluator, RLAbstractSolutionFactory<T, V> fac) {
		this.targetValue = targetValue;
		this.evaluator = evaluator;
		this.relevantDocs = fac.getRelevantDocs();
		this.listLength = fac.getListLength();
		this.factory = fac;

	}
	
	public void evaluate(T solution) {
		evaluateObjectives(solution);
		evaluateConstraints(solution);	
	}

 	abstract void evaluateObjectives(T solution);

	abstract void evaluateConstraints(T sol);

	
	public T createSolution() {
		return factory.generateNewSolution();
	}
	
	public RLAbstractSolutionFactory<T, V> getFactory() {
		return factory;
	}
	
	public int getRelevantDocs() {
		return relevantDocs;
	}

	
}