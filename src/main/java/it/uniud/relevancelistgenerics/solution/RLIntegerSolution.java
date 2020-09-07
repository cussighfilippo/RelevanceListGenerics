package it.uniud.relevancelistgenerics.solution;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.solution.Solution;

@SuppressWarnings("serial")
public class RLIntegerSolution extends RLAbstractSolution<List<Integer>, Integer> {

	public RLIntegerSolution(int nVariables, int nObjectives, int nConstraints, Integer[] docsStatus) {
		super(nVariables, nObjectives, nConstraints, docsStatus);
		numberOfRelevantDocs = 0;
		for(int i=0; i<docsStatus.length; i++) if (docsStatus[i]>0) numberOfRelevantDocs++;
		setVariable(0, createNewSet(docsStatus.length, docsStatus));
	}

	RLIntegerSolution(RLIntegerSolution solution) {
		super(solution);
		numberOfRelevantDocs = solution.numberOfRelevantDocs;
		List<Integer> newList = new ArrayList<Integer>();
		newList.stream().forEach(s -> newList.add(s));
		for (int i=0; i < this.getNumberOfVariables(); i++) this.setVariable(i, newList);
		for (int i=0; i < this.getNumberOfObjectives(); i++) this.setObjective(i, solution.getObjective(i));
	}

	public Solution<List<Integer>> copy() {
		return new RLIntegerSolution(this);
	}
	
	public List<Integer> createNewSet(int numberOfElements, Integer[] values) {
		List<Integer> set = new ArrayList<Integer>(numberOfElements) ;
    	for (int i=0; i < numberOfElements; i++) {
    		set.add(values[i]);
    	}
        return set;
	}


	public int getVariableLength(int index) {
		return this.getVariable(0).size();
	}

	public void setSingleValue(int variableIndex, int index, Integer value) {
        List<Integer> docsStatusValues = getVariable(variableIndex);
        Integer old = docsStatusValues.get(index);
        if (value == 0 && old > 0) numberOfRelevantDocs--;
        if (value > 0 && old == 0) numberOfRelevantDocs++;
        docsStatusValues.set(index, value);
	}

	public Integer[] retrieveDocsStatus() {
		 Integer[] docsStatusValues = new Integer[(getVariable(0).size())];
	        for (int i=0; i<getVariable(0).size(); i++) docsStatusValues[i] = getVariable(0).get(i);
	        return docsStatusValues;
	}

	public int getNumberOfElements(int index) {
		return getVariable(index).size();
	}

}
