package gradingTools.comp533s24.assignment02.hints.conditionQueueEntered;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("The idea of out of turn threads")
public class ConditionQueueEnteredHint1 extends AbstractHint{
	


	@Override
	protected Class[] previousHints() {
		return noPreviousHints();
	}

	@Override
	protected String hint() {
		String aLine1 = "Try to make one out-of-turn thread enter the monitor using the e command";
		String aLine2 = "If the monitor is occupied release it using the r command";
		String aLine3 = "Look at the queues using the q command";
		String aLine4 = "You should see that 1 thread entered the condition queue";
		
		return "\n" + aLine1 +  "\n" + aLine2 + "\n" + aLine3 + "\n" + aLine4;
	}

}
