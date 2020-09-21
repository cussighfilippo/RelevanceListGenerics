package it.uniud.relevancelistgenerics.solution.factory;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.uniud.relevancelistgenerics.solution.RLAbstractSolution;


public abstract class RLAbstractSolutionFactory< T extends RLAbstractSolution<?, V>, V> {

	//solution's number of variables, objectives and constraints 
	int nVariables = 1;
	int nObjectives = 1;
	int nConstraints = 1;
	
	double maxValue; // max relevance value of a document
	int listLength; // length of a Solution's relevance list
	int relDocs; // number of relevant documents fixed for the problem
	EnumeratedIntegerDistribution initDistribution; // probability distribution used for solution's initialization
	double fractNonZero; // fraction of non-zero relevance documents in new solution generation
	JMetalRandom randomGenerator;
	

	public RLAbstractSolutionFactory(double maxValue, int size, int relDocs, EnumeratedIntegerDistribution distribution, double fractNonZero) {
		this.maxValue = maxValue;
		this.listLength = size;
		this.relDocs = relDocs;
		this.initDistribution = distribution;
		this.fractNonZero = fractNonZero;
		randomGenerator = JMetalRandom.getInstance();
		
	}
	
	
	public abstract T generateNewSolution();
	
	// returns a new array representing a relevance profile
	abstract  V[] createRelevanceSet();

	// generates a new solution based on the given relevance profile
	public abstract T generateNewSolution(V[] docs);
	
	// generates a  new relevance profile as a int array based on the given distribution
	int[] createDiscreteDistribution() {

	 	int[] array = new int[listLength];
	    for (int i = 0; i < listLength ; i++) {
	    	array[i] = 0;
	    }
	    
	    double fracNonZeroRelevant = fractNonZero; // percentuale dei relevant impostata in input
	    double shiftValue = randomGenerator.nextDouble()*fracNonZeroRelevant;
	    if (randomGenerator.nextDouble() > 0.5){
	    	// somma
	    	fracNonZeroRelevant = fracNonZeroRelevant + shiftValue;
	    }else{
	    	// sottrai
	    	fracNonZeroRelevant = fracNonZeroRelevant - shiftValue;
	    }
	    int howManyNotZero = (int) Math.round(fracNonZeroRelevant*relDocs);
	    if(howManyNotZero == 0){
	    	howManyNotZero++;
	    } else if(howManyNotZero > relDocs){
	    	howManyNotZero = relDocs;
	    }
	    
	    ArrayList<Integer> indiciNonZero = new ArrayList<Integer>();
	    int[] temp = initDistribution.sample(howManyNotZero);
	    for(int i=0; i<temp.length; i++){
	    	indiciNonZero.add(temp[i]);
	    }    
	    Set<Integer> indiciDistinti = new LinkedHashSet<Integer>();
	    for(int x : indiciNonZero) {
	    	indiciDistinti.add(x);
	    }
	    for(int  x : indiciDistinti){
	    	indiciNonZero.remove(Integer.valueOf(x));
	    }
	    if(indiciNonZero.size() > 0){
	    	for(int x : indiciNonZero){
	    		int nuovoX = x;
	    		while(indiciDistinti.contains(nuovoX)){
	    			nuovoX = (nuovoX + 1) % listLength;
	    		}
	    		indiciDistinti.add(nuovoX);
	    	}
	    }
	    indiciNonZero = new ArrayList(indiciDistinti);
	    
	   for(int cellIndex : indiciNonZero){
		   int cellValue = (int) Math.round(randomGenerator.nextDouble()*(maxValue-1)) + 1;
		   array[cellIndex] = cellValue;
	   }
	   
		return array;
	}
	

	public int getListLength() {
		return listLength;
	} 
	
	public int getRelevantDocs() {
		return relDocs;
	}

	public double getMaxValue() {
		return maxValue;
	}
	
	public int getNumberOfVariables() {
		return nVariables;
	}

	public void setNumberOfVariables(int nVariables) {
		this.nVariables = nVariables;
	}

	public int getNumberOfObjectives() {
		return nObjectives;
	}

	public void setNumberOfObjectives(int nObjectives) {
		this.nObjectives = nObjectives;
	}

	public int getNumberOfConstraints() {
		return nConstraints;
	}

	public void setNumberOfConstraints(int nConstraints) {
		this.nConstraints = nConstraints;
	}
}
