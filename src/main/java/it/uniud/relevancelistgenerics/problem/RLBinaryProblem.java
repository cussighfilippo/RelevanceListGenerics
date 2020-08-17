package it.uniud.relevancelistgenerics.problem;

import org.uma.jmetal.util.binarySet.BinarySet;

import it.uniud.relevancelistgenerics.program.Program.EvaluationFunction;
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
			int bitSetLength = solution.getVariable(0).getBinarySetLength();
			boolean[] solutionArray = new boolean[bitSetLength];
			for (int i = 0; i < bitSetLength; i++)
				solutionArray[i] = solution.getVariable(0).get(i);
			actualValue = avgPrecisionMetric(solution.getVariable(0));
			break;
		default:
			System.err.println("evaluation function makes no sense for this solution type");
		}

		solution.setObjective(0, Math.abs(actualValue - targetValue));
	}

	
	private double avgPrecisionMetric(BinarySet bitSet) {
		double returnValue = 0;
		int nOnes = 0;
		for (int i = 0; i < bitSet.getBinarySetLength(); i++) {
			if (bitSet.get(i)) {
				nOnes++;
				returnValue = returnValue + ((double) nOnes / (i + 1));
			}
		}
		double returnVal = returnValue / relevantDocs;
		return returnVal;
	}


	void evaluateConstraints(RLBinarySolution solution) {
		double constraint;
		BinarySet docs = solution.getVariable(0);
		int numberOfRelevantDocs = 0;
		for (int i = 0; i < docs.getBinarySetLength(); i++)  if (docs.get(i)) numberOfRelevantDocs++;
		constraint = relevantDocs - numberOfRelevantDocs;
		solution.setConstraint(0, constraint);
	}

	
	
	
}
