package it.uniud.relevancelistgenerics.metric;

import org.uma.jmetal.util.binarySet.BinarySet;
import it.uniud.relevancelistgenerics.solution.RLBinarySolution;
import it.uniud.relevancelistgenerics.solution.RLIntegerSolution;

public class AveragePrecisionEvaluator extends MetricEvaluator{
	
	int relevantDocs;

	public AveragePrecisionEvaluator (int relDocs) {
		relevantDocs = relDocs;
	}

	//returns average precision of the given solution without changing it's objective value
	public double evaluate(RLBinarySolution solution) {
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
	
	public double evaluate(RLIntegerSolution sol) {		
		System.err.print("average precision is not compatible with integer Solutions");
		System.exit(1);
		return 0;
	}
	
	
}
