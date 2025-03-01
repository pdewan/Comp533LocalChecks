package gradingTools.comp533s24.assignment02.hints.urgentNotFIFO;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("Emptying of urgent queue with release command")
public class UrgentQueueNotFIFOHint1 extends AbstractHint{
	
	

	
	@Override
	protected Class[] previousHints() {
		return noPreviousHints();
	}


	@Override
	protected String hint() {
		String aLine1 = "Make sure the urgent queue has at least 5 threads - see hints on how to do that if necessary\n";
		String aLine2 = "Execute one oe more release commands to see in what order they enter and leave the monitor and possibly go back to the condition queue\n";	
		String aLine3 = "Use the queue and history command to verify non FIFO behavior";
		return "\n" + aLine1 + "\n" + aLine2 + "\n" + aLine3;
	}

}
