package gradingTools.comp533s24.assignment02.hints.entryQueueHas4;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("In-turn requests to enter are granted one at a time")
public class EntryQueueHas4Hint1 extends AbstractHint{	
	@Override
	protected Class[] previousHints() {
		return noPreviousHints();
	}
	@Override
	protected String hint() {
		String aLine1 = "Try make an 1 in-turn thread request monitor entry using the e command";
		String aLine2 = "Now make four more threads request monitor entry using the e command";
		String aLine3 = "See ConditionQueueEnteredHints for in-turn vs out of turn threds";



		return "\n" +  aLine1 + "\n" + aLine2 + "\n" + aLine3 + "\n";
	}

}
