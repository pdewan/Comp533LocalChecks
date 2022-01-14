package gradingTools.comp533s22.assignment1.listUpdatedTests;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.shared.testcases.ASubstringSequenceChecker;

public class AStandAloneSingleThreadTokenCounterMapReduceCheckerListImplementation extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public  final String[] MY_SUBSTRINGS = {
				".*"+AMapReduceTracer.MAP+".*Hogwarts.*muggles.*wizards.*"+mappedToOneRegex+".*",
				".*"+AMapReduceTracer.REDUCE+".*"+mappedToOneRegex+".*Hogwarts=5.*",
	};
	
	
	private static final String mappedToOneRegex="(\\([Hmw].*, ?1\\).*){10}";
			
	public AStandAloneSingleThreadTokenCounterMapReduceCheckerListImplementation() {
		init( MY_SUBSTRINGS);
	}
	

}
