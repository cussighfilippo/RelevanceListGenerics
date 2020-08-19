package it.uniud.relevancelistgenerics.problem;

import java.util.List;

import org.uma.jmetal.util.binarySet.BinarySet;

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
			actualValue = avgPrecision(solution);
			break;
		default:
			System.err.println("evaluation function makes no sense for this solution type");
		}

		solution.setObjective(0, Math.abs(actualValue - targetValue));
	}

	
	

	// returns the avgPrecision of the solution
	// the solution's objective values are not modified by the method
	private double avgPrecision(RLIntegerSolution solution) {
		List<Integer> set = solution.getVariable(0);
		double returnValue = 0;
		int nOnes = 0;
		for (int i = 0; i < set.size(); i++) {
			if (set.get(i)>0) {
				nOnes++;
				returnValue = returnValue + ((double) nOnes / (i + 1));
			}
		} 
		return returnValue / relevantDocs; 
	}


	void evaluateConstraints(RLIntegerSolution solution) {
		double constraint;
		int numberOfRelevantDocs = solution.getNumberOfRelevantDocs();
		constraint = relevantDocs - numberOfRelevantDocs;
		solution.setConstraint(0, constraint);
	}

	
	
	
}