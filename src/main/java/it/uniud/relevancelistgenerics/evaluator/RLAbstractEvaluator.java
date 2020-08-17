package it.uniud.relevancelistgenerics.evaluator;

import java.util.Arrays;

import it.uniud.relevancelistgenerics.problem.RLAbstractProblem;
import it.uniud.relevancelistgenerics.program.Program.EvaluationFunction;
import it.uniud.relevancelistgenerics.solution.RLAbstractSolution;

public abstract class RLAbstractEvaluator<T extends RLAbstractSolution<?,?>> {

	String name;
	EvaluationFunction evalFunction;
	RLAbstractProblem<T, ?> problem;
	
	abstract double evaluate(T solution);
	
	void setFunction(EvaluationFunction fun) {
		evalFunction = fun;
	}
	
	void setFunction(String fun) {
		if (!Arrays.stream(EvaluationFunction.values()).anyMatch((t) -> t.name().equals(fun))) 
			evalFunction = EvaluationFunction.valueOf(fun);
		else {
			System.err.println("Invalid evaluation function: " + fun);
			System.err.println("Valid functions: " + Arrays.toString(EvaluationFunction.values()));
		}
	}
}
