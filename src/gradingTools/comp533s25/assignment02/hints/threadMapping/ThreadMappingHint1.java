package gradingTools.comp533s25.assignment02.hints.threadMapping;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("Timning or order determines thread assignment")
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
		String aLine1 = "Try to test the following two theories";
		String aLine2 = "Theory 1: A client thread is assigned a released server thread ";
		String aLine3 = "Theory 2: A server thread that is idle for some time is garbage collected";
		String aLine4 = "You will have to use main threds to test these theories";		
		String aLine5 = "A main thread waits for user return before making the next remote call";
		String aLine6 = "When released, a non-main thread immediately makes a remote call again, getting its previous server thread not garbage collected";
		String aLine7 = "The second index of a main thread is 0, e.g. c00, p00\n";
		
		return "\n" + aLine1 + "\n" + aLine2 + "\n" + aLine3 + "\n" + aLine4 + "\n" + aLine5 + "\n" + aLine6 + "\n" + aLine7 + "\n";
	}

}
