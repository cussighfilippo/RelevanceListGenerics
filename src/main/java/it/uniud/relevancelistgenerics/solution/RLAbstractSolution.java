package it.uniud.relevancelistgenerics.solution;

import org.uma.jmetal.solution.AbstractSolution;


@SuppressWarnings("serial")
public abstract class RLAbstractSolution<S, V> extends AbstractSolution<S>{


		int numberOfRelevantDocs;
		
		RLAbstractSolution(int nVariables, int nObjectives, int nConstraints, V[] docsStatus) {
			super(nVariables, nObjectives, nConstraints);
		}
		
		RLAbstractSolution(RLAbstractSolution<S, V> solution) {
			super(solution.getNumberOfVariables(), solution.getNumberOfObjectives(), solution.getNumberOfConstraints());
		}
		
		abstract public int getVariableLength(int index);

	    abstract public S createNewSet(int numberOfBits, V[] values);
	    
	    abstract public void setSingleValue(int variable, int index, V value);


		public int getNumberOfRelevantDocs() {
			return numberOfRelevantDocs;
		}

		public void setNumberOfRelevantDocs(int n) {
			this.numberOfRelevantDocs = n;
		}
		
	    public abstract V[] retrieveDocsStatus();
	    

	}

