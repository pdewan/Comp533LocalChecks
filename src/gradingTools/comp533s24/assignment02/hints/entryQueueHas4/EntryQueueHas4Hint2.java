package gradingTools.comp533s24.assignment02.hints.entryQueueHas4;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s24.assignment02.hints.conditionQueueHas4.ConditionQueueHas4Hint1;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("The concept of in-turn and out of turn threds")
public class EntryQueueHas4Hint2 extends AbstractHint{
	static Class[] PREVIOUS_HINTS = {
			EntryQueueHas4Hint1.class	
			};

	
	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return PREVIOUS_HINTS;
	}
	

	@Override
	protected String hint() {
		String aLine1 = "Initially producers are in-turn and consumers are out-of-turn";
		String aLine2 = "After a production the opposite is true";
		String aLine3 = "Use the thread (t), enter (e), and queue (q) commands";	

		return "\n" + aLine1 + "\n" + aLine2 + "\n" + aLine3 + "\n";
		

	}

}
