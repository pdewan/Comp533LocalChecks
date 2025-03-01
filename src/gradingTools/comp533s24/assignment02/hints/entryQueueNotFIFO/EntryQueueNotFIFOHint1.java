package gradingTools.comp533s24.assignment02.hints.entryQueueNotFIFO;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("The concept of releasing in-turn threads")
public class EntryQueueNotFIFOHint1 extends AbstractHint{
	
	

	
	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return noPreviousHints();
	}



	@Override
	protected String hint() {
		String aLine1 = "Make sure the entry queue has 5 threads whose turn it is after the previous one";
		String aLine2 = "Release each of them in turn";	
		String aLine3 = "Use the history command to check the entry and exit orders";
		return "\n" + aLine1 + "\n" + aLine2 + "\n" +  aLine3;
	}

}
