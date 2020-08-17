package it.uniud.relevancelistgenerics.problem;

import java.util.List;

import it.uniud.relevancelistgenerics.program.Program.EvaluationFunction;

import it.uniud.relevancelistgenerics.solution.RLIntegerSolution;
import it.uniud.relevancelistgenerics.solution.factory.RLAbstractSolutionFactory;

@SuppressWarnings("serial")
public class RLIntegerProblem extends RLAbstractProblem<RLIntegerSolution,  Integer>{

	
	public RLIntegerProblem(double targetValue, EvaluationFunction evalFun,
			RLAbstractSolutionFactory<RLIntegerSolution, Integer> fac) {
		super(targetValue, evalFun, fac);	
	}


	void evaluateObjectives(RLIntegerSolution solution) {
		double actualValue = 100;

		switch (evalFun) {
		case avgPrecision:
			if(factory.getMaxValue() > 1) {
				System.err.print("cant use avgPrecision with maxCellValue > 1");
				System.exit(1);
			}
			int setLength = solution.getVariable(0).size();
			int[] solutionArray = new int[setLength];
			for (int i = 0; i < setLength; i++)
				solutionArray[i] = solution.getVariable(0).get(i);
			actualValue = avgPrecisionMetric(solution.getVariable(0));
			break;
		default:
			System.err.println("evaluation function makes no sense for this solution type");
		}

		solution.setObjective(0, Math.abs(actualValue - targetValue));
	}

	
	private double avgPrecisionMetric(List<Integer> set) {
		double returnValue = 0;
		int nOnes = 0;
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i)>0) {
				nOnes++;
				returnValue = returnValue + ((double) nOnes / (i + 1));
			}
		}
		double returnVal = returnValue / relevantDocs;
		return returnVal;
	}


	void evaluateConstraints(RLIntegerSolution solution) {
		double constraint;
		List<Integer> docs = solution.getVariable(0);
		int numberOfRelevantDocs = 0;
		for (int i = 0; i < docs.size(); i++)  if (docs.get(i) > 0) numberOfRelevantDocs++;
		constraint = relevantDocs - numberOfRelevantDocs;
		solution.setConstraint(0, constraint);
	}

	
	
	
}