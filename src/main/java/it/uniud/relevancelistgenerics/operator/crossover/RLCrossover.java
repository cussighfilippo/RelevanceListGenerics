package it.uniud.relevancelistgenerics.operator.crossover;

import org.uma.jmetal.operator.crossover.CrossoverOperator;
import it.uniud.relevancelistgenerics.solution.RLAbstractSolution;

public interface RLCrossover<T extends RLAbstractSolution<?,?>> extends CrossoverOperator<T> {

	
}
