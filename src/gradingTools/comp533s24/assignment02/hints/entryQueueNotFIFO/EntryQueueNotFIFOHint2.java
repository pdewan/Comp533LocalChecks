package gradingTools.comp533s24.assignment02.hints.entryQueueNotFIFO;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s24.assignment02.hints.conditionQueueHas4.ConditionQueueHas4Hint1;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("How to create in-turn producers and consumers")
public class EntryQueueNotFIFOHint2 extends AbstractHint{
	static Class[] PREVIOUS_HINTS = {
			EntryQueueNotFIFOHint1.class	
			};

	
	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return PREVIOUS_HINTS;
	}
	


	@Override
	protected String hint() {
		String aLine1 = "Make 5 alternating  producers and consumers request montor entry and then release them incementally";
		return "\n" + aLine1 + "\n";

	}

}
