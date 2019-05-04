package gradingTools.comp533s19.assignment0.testcases.counts.standalone;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterClientChecker;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.shared.testcases.SubstringSequenceChecker;
//@MaxValue(5)
//@Explanation("Checks for expected client1 counter output when explicit receive is implemented.")
public class StandAloneTokenCountPartition2Reduce extends StandAloneTokenCountPartition1Reduce {


	protected SubstringSequenceChecker checker() {
		return new AStandAloneBasicTokenCountPartition2ReduceChecker();
	}

	
}
