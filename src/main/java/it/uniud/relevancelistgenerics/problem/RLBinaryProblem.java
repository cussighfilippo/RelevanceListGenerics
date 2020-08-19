package it.uniud.relevancelistgenerics.problem;

import org.uma.jmetal.util.binarySet.BinarySet;

import it.uniud.relevancelistgenerics.solution.RLBinarySolution;
import it.uniud.relevancelistgenerics.solution.factory.RLAbstractSolutionFactory;

@SuppressWarnings("serial")
public class RLBinaryProblem extends RLAbstractProblem<RLBinarySolution, Boolean>{

	
	public RLBinaryProblem(double targetValue, EvaluationFunction evalFun,
			RLAbstractSolutionFactory<RLBinarySolution, Boolean> fac) {
		super(targetValue, evalFun, fac);	
	}


	void evaluateObjectives(RLBinarySolution solution) {
		double actualValue = 100;

		switch (evalFun) {
		case avgPrecision:
			actualValue = avgPrecision(solution);
			break;
		default:
			System.err.println("evaluation function makes no sense for this solution type");
		}

		solution.setObjective(0, Math.abs(actualValue - targetValue));
	}


	// returns the avgPrecision of the solution
	// the solution's objective values are not modified by the method
	private double avgPrecision(RLBinarySolution solution) {
		BinarySet bitSet = solution.getVariable(0);
		double returnValue = 0;
		int nOnes = 0;
		for (int i = 0; i < bitSet.getBinarySetLength(); i++) {
			if (bitSet.get(i)) {
				nOnes++;
				returnValue = returnValue + ((double) nOnes / (i + 1));
			}
		} 
		return returnValue / relevantDocs; 
	}
	


	void evaluateConstraints(RLBinarySolution solution) {
		double constraint;
		int numberOfRelevantDocs = solution.getNumberOfRelevantDocs();
		constraint = relevantDocs - numberOfRelevantDocs;
		solution.setConstraint(0, constraint);
	}

	
	
	
}
