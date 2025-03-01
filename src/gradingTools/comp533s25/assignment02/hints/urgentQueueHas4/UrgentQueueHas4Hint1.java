package gradingTools.comp533s25.assignment02.hints.urgentQueueHas4;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("The relationship between condition queue and urgent queue")
public class UrgentQueueHas4Hint1 extends AbstractHint{
	


	@Override
	protected Class[] previousHints() {
		return noPreviousHints();
	}

	@Override
	protected String hint() {
		String aLine1 = "Make five out-of-turn threads enter the condition queue";
		String aLine2 = "Make an in-turn thread enter and release the monitor";	
		String aLine3 = "Use the queue (q) command to see the urgent queue";
		String aLine4 = "You should see that 4 threads entered the urgent queue and one has left it\n";
		return "\n" + aLine1 + "\n" + aLine2 + "\n" + aLine3 + "\n" + aLine4;
	}

}
