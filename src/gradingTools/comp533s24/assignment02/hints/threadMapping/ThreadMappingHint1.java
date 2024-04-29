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
@Explanation("First Hint on Thread Assignment")
public class ThreadMappingHint1 extends AbstractHint{
//	static Class[] PREVIOUS_HINTS = {
//			???.class	
//			};

	
	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return noPreviousHints();
	}
	
//	@Override
//	public TestCaseResult test(Project aProject, boolean autoGrade)
//			throws NotAutomatableException, NotGradableException {
//		try {
//			String aLine1 = "The second index of a main thread is 0, e.g. c00, p00, c10\n";
//			String aLine2 = "When released a non-main thread immediately makes a remote call again\n";
//			String aLine3 = "So it is assigned the last released server thread\n";
//			String aLine4 = "A main thread waits for user return before making the next remote call\n";
//			System.out.println(aLine1 + aLine2 + aLine3 + aLine4);
//			return pass ("Please see console output for hints");			
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return fail(e.getMessage());
//		}
//	}

	@Override
	protected String hint() {
		String aLine1 = "The second index of a main thread is 0, e.g. c00, p00, c10\n";
		String aLine2 = "When released a non-main thread immediately makes a remote call again\n";
		String aLine3 = "So it is assigned the last released server thread\n";
		String aLine4 = "A main thread waits for user return before making the next remote call\n";
		return "\n" + aLine1 + aLine2 + aLine3 + aLine4;
	}

}
