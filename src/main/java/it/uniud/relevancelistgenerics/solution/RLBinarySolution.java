package it.uniud.relevancelistgenerics.solution;

import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.binarySet.BinarySet;


@SuppressWarnings("serial")
public class RLBinarySolution extends RLAbstractSolution<BinarySet, Boolean> {

	public RLBinarySolution(int nVariables, int nObjectives, int nConstraints, Boolean[] docsStatus) {
		super(nVariables, nObjectives, nConstraints, docsStatus);
		numberOfRelevantDocs = 0;
		for(int i=0; i<docsStatus.length; i++) if (docsStatus[i]) numberOfRelevantDocs++;
		setVariable(0, createNewSet(docsStatus.length, docsStatus));
	}

	RLBinarySolution(RLBinarySolution solution) {
		super(solution);
		numberOfRelevantDocs = solution.numberOfRelevantDocs;
		for (int i=0; i < this.getNumberOfVariables(); i++) this.setVariable(i,(BinarySet) solution.getVariable(i).clone());
		for (int i=0; i < this.getNumberOfObjectives(); i++) this.setObjective(i, solution.getObjective(i));
	}

	public Solution<BinarySet> copy() {
		return new RLBinarySolution(this);
	}


	public int getVariableLength(int index) {
		return this.getVariable(0).getBinarySetLength();
	}

	public BinarySet createNewSet(int numberOfBits, Boolean[] values) {
		BinarySet bitSet = new BinarySet(numberOfBits) ;
    	for (int i=0; i < numberOfBits; i++) {
    		if (values[i]) bitSet.set(i); 
    		else bitSet.clear(i);
    	}
        return bitSet;
	}

	public void setSingleValue(int variableIndex, int index, Boolean value) {
        BinarySet docsStatusValues = getVariable(variableIndex);
        if (docsStatusValues.get(index) != value) {
            docsStatusValues.set(index, value);
            if (value) numberOfRelevantDocs++; else numberOfRelevantDocs--;
        }
        setVariable(variableIndex, docsStatusValues);
	}

	public Boolean[] retrieveDocsStatus() {
		 Boolean[] docsStatusValues = new Boolean[(getVariable(0).getBinarySetLength())];
	        for (int i=0; i<getVariable(0).getBinarySetLength(); i++) docsStatusValues[i] = getVariable(0).get(i);
	        return docsStatusValues;
	}

	public int getNumberOfBits(int index) {
		return getVariable(index).getBinarySetLength();
	}

	@Override
	public void setVariable(int i, BinarySet set) {
		super.setVariable(i, set);
		if(i == 0) numberOfRelevantDocs = set.cardinality();
	}
}
