package it.uniud.relevancelistgenerics.solution;

import org.uma.jmetal.solution.AbstractSolution;


@SuppressWarnings("serial")
public abstract class RLAbstractSolution<S, T> extends AbstractSolution<S>{


		static final int nVariables = 1;
		static final int nObjectives = 1;
		static final int nCostraints = 1;
		
		int numberOfRelevantDocs;
		
		RLAbstractSolution(T[] docsStatus) {
			super(nVariables, nObjectives, nCostraints);
		}
		
		RLAbstractSolution(RLAbstractSolution<S, T> solution) {
			super(nVariables, nObjectives, nCostraints);
		}
		
		abstract public int getVariableLength(int index);

	    abstract public S createNewSet(int numberOfBits, T[] values);
	    
	    abstract public void setSingleValue(int variable, int index, T value);


		public int getNumberOfRelevantDocs() {
			return numberOfRelevantDocs;
		}

		public void setNumberOfRelevantDocs(int n) {
			this.numberOfRelevantDocs = n;
		}
		
	    public abstract T[] retrieveDocsStatus();

	}

