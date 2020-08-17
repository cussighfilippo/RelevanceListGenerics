package it.uniud.relevancelistgenerics;

import org.uma.jmetal.util.binarySet.BinarySet;

import it.uniud.relevancelistgenerics.problem.RLBinaryProblem;
import it.uniud.relevancelistgenerics.program.Program.EvaluationFunction;
import it.uniud.relevancelistgenerics.solution.RLBinarySolution;

public class RLBinaryEvaluator  extends RLAbstractEvaluator<RLBinarySolution> {
	
	RLBinaryEvaluator(RLBinaryProblem problem) {
		evalFunction = EvaluationFunction.avgPrecision;
		this.problem = problem;
	}

	RLBinaryEvaluator(RLBinaryProblem problem, EvaluationFunction eval) {
		evalFunction = eval;
		this.problem = problem;
	}
	
	double evaluate(RLBinarySolution solution) {
		switch(evalFunction) {
		case avgPrecision:
			return avgPrecision(solution);
		default:
			System.err.print("invalid function");
			return 0;
		}
	}

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
		return returnValue / problem.getRelevantDocs(); 
	}


}
