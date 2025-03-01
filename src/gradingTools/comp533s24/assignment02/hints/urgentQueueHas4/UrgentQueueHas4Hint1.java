package gradingTools.comp533s24.assignment02.hints.urgentQueueHas4;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("First Hint on Urgent Queue Has 4")
public class UrgentQueueHas4Hint1 extends AbstractHint{
	
	@Override
	protected Class[] previousHints() {
		return noPreviousHints();
	}

	@Override
	protected String hint() {
		String aLine1 = "Create a situation in which similatenously there are 4 threads in the condition queue and a thread in the monitor (which will notifyall)\n";
		String aLine2 = "See the hints on conditionaQueueHas4 on how to do this \n";
		String aLine3 = "Release the monitor occupant\n";
		String aLine4 = "Look at the history\n";
		String aLine5 = "You should see that 4 threads entered the urgent queue and one has left it\n";
		return "\n"+  aLine1 + aLine2 + aLine3 + aLine4 + aLine5;
	}

}
