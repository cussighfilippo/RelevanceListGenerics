package it.uniud.relevancelistgenerics.operator.mutation;
import org.uma.jmetal.operator.mutation.MutationOperator;
import it.uniud.relevancelistgenerics.solution.RLAbstractSolution;

public interface RLMutation<T extends RLAbstractSolution<?,?>> extends MutationOperator<T>{
	@Override
	public T execute(T solution);
}
