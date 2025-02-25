package gradingTools.comp533s24.assignment02.hints.conditionQueueEntered;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("How to create an out of turn thread")
public class ConditionQueueWasEnteredHint3 extends AbstractHint{
	static Class[] PREVIOUS_HINTS = {
			ConditionQueueWasEnteredHint2.class	
			};
	
//	@Override
//	public TestCaseResult test(Project aProject, boolean autoGrade)
//			throws NotAutomatableException, NotGradableException {
//		try {
//			String aLine1 = "Make four consumer threads enter the monitor queue at the start of the program to create four out of turn threads\n";
//			System.out.println(aLine1);
//			return pass ("Please see console output for hints");			
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return fail(e.getMessage());
//		}
//	}

	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return PREVIOUS_HINTS;
	}

	@Override
	protected String hint() {
		String aLine1 = "Make a consumer threads enter the monitor queue at the start of the program to create an out of turn thread\n";
		return "\n" + aLine1;

	}

}
