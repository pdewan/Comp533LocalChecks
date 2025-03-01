package gradingTools.comp533s24.assignment02.hints.threadMapping;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("Main threads are the key")
public class ThreadMappingHint1 extends AbstractHint{
//	static Class[] PREVIOUS_HINTS = {
//			???.class	
//			};

	
	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return noPreviousHints();
	}
	


	@Override
	protected String hint() {
		String aLine1 = "The second index of a main thread is 0, e.g. c00, p00";
		String aLine2 = "When released, a non-main thread immediately makes a remote call again\n";
		String aLine3 = "So it is assigned the last released server thread\n";
		String aLine4 = "A main thread waits for user return before making the next remote call\n";
		String aLine5 = "You have to use main hreads to perform ths experiment";

		return "\n" + aLine1 + aLine2 + aLine3 + aLine4;
	}

}
