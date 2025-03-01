package gradingTools.comp533s24.assignment02.hints.conditionQueueEntered;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("The initial out of turn thread")
public class ConditionQueueEnteredHint3 extends AbstractHint{
	static Class[] PREVIOUS_HINTS = {
			ConditionQueueEnteredHint2.class	
			};
	


	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return PREVIOUS_HINTS;
	}

	@Override
	protected String hint() {
		String aLine1 = "Initially a consumer is out of turn and a producer is in turn";
		return "\n" + aLine1 + "\n";

	}

}
