package gradingTools.comp533s24.assignment02.hints.urgentQueuePrecedence;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("Operational help")
public class UrgentQueuePrecedenceHint2 extends AbstractHint{
	
	static Class[] PREVIOUS_HINTS = {
			UrgentQueuePrecedenceHint1.class
			};

	
	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return PREVIOUS_HINTS;
	}
//	@Override
//	public TestCaseResult test(Project aProject, boolean autoGrade)
//			throws NotAutomatableException, NotGradableException {
//		try {
//			String aLine1 = "Create a condition queue with at least 4 elements\n";
//			String aLine2 = "Make a thread enter the monitor that can notify them all\n";
//			String aLine3 = "Make sure at least four threads enter the enter queue while the monitor is occupied\n";
//			String aLine4 = "Release the monitor\n";
//			String aLine5 = "A thread in the urgent queue should be occupying the monitor now\n";
//			String aLine6 = "Release the monitor - this should result in the other threads to enter the monitor and return to te condition queue\n";
//
//			
//			System.out.println(aLine1 + aLine2 + aLine3 + aLine4 + aLine5 + aLine6);
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
		String aLine1 = "Create a condition queue with at least 4 elements\n";
		String aLine2 = "Make a thread enter the monitor that can notify them all\n";
		String aLine3 = "Make sure at least four threads enter the enter queue while the monitor is occupied\n";
		String aLine4 = "Release the monitor\n";
		String aLine5 = "A thread in the urgent queue should be occupying the monitor now\n";
		String aLine6 = "Release the monitor - this should result in the other threads to enter the monitor and return to te condition queue\n";
		String aLine7 = "Use the history command to verify this\n";

		return "\n" + aLine1 + aLine2 + aLine3 + aLine4 + aLine5 + aLine6 + aLine7;

	}

}
