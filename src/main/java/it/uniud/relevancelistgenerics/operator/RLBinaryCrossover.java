package it.uniud.relevancelistgenerics.operator;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.util.binarySet.BinarySet;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import it.uniud.relevancelistgenerics.problem.RLAbstractProblem;
import it.uniud.relevancelistgenerics.solution.RLBinarySolution;

@SuppressWarnings("serial")
public class RLBinaryCrossover extends RLAbstractCrossover<RLBinarySolution, Boolean>{

	public RLBinaryCrossover(double crossoverProbability,
			RLAbstractProblem<RLBinarySolution,  Boolean> problem) {
		super(crossoverProbability, problem);
	}

	@Override
	public List<RLBinarySolution> execute(List<RLBinarySolution> source) {
		
        RLBinarySolution firstSolution =  source.get(0);
        RLBinarySolution secondSolution = source.get(1);
        Boolean[] firstDocsStatus = firstSolution.retrieveDocsStatus();
        Boolean[] secondDocsStatus = secondSolution.retrieveDocsStatus();

        List<RLBinarySolution> childrenSolution = new ArrayList<RLBinarySolution>();

        RLBinarySolution firstChild = problem.getFactory().generateNewSolution();
        RLBinarySolution secondChild = problem.getFactory().generateNewSolution();
        childrenSolution.add(firstChild);
        childrenSolution.add(secondChild);
        
        if (JMetalRandom.getInstance().nextDouble() < crossoverProbability) {

            for (int i=0; i<firstDocsStatus.length; i++) {
                firstChild.setSingleValue(0, i, firstDocsStatus[i] && secondDocsStatus[i]);
                secondChild.setSingleValue(0, i, firstDocsStatus[i] || secondDocsStatus[i]);
            }

            if (firstChild.getNumberOfRelevantDocs() == 0) {
                int flipIndex =(int) Math.floor(JMetalRandom.getInstance().nextDouble() * firstChild.getNumberOfBits(0));
                if (flipIndex == firstChild.getNumberOfBits(0)) flipIndex -= 1;
                firstChild.setSingleValue(0, flipIndex, true);
            }

        }
    
		return childrenSolution;
	}
	
}
