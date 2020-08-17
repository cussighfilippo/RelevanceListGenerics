package it.uniud.relevancelistgenerics.solution.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.uniud.relevancelistgenerics.solution.RLAbstractSolution;


public abstract class RLAbstractSolutionFactory< T extends RLAbstractSolution<?, V>, V> {

	
	int maxValue;
	int listLength;
	int relDocs;
	EnumeratedIntegerDistribution distribution; 
	double fractNonZero;
	JMetalRandom randomGenerator;
	

	public RLAbstractSolutionFactory(int maxValue, int size, int relDocs, EnumeratedIntegerDistribution distribution, double fractNonZero) {
		this.maxValue = maxValue;
		this.listLength = size;
		this.relDocs = relDocs;
		this.distribution = distribution;
		this.fractNonZero = fractNonZero;
		randomGenerator = JMetalRandom.getInstance();
		
	}
	
	public abstract T generateNewSolution();
	
	abstract  V[] createDocumentsSet();

	public abstract T generateNewSolution(V[] docs);
	
	int[] createDocumentDistribution() {

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
	    
	    // variante geometrica
	    
	    ArrayList<Integer> indiciNonZero = new ArrayList<Integer>();
	    int[] temp = distribution.sample(howManyNotZero);
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

	public int getMaxValue() {
		return maxValue;
	}
}
