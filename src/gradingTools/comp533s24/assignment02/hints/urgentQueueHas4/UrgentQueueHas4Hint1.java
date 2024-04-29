package gradingTools.comp533s24.assignment02.hints.urgentQueueHas4;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("First Hint on Urgent Queue Has 4")
public class UrgentQueueHas4Hint1 extends AbstractHint{
	
	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return noPreviousHints();
	}
//	@Override
//	public TestCaseResult test(Project aProject, boolean autoGrade)
//			throws NotAutomatableException, NotGradableException {
//		try {
//			String aLine1 = "Create a situation in which similatenously there are 4 threads in the condition queue and a thread in the monitor that can notifyall\n";
//			String aLine2 = "Release the monitor occupying thread\n";
//			String aLine3 = "Look at the history\n";
//			String aLine4 = "You should see that 4 threads entered the urgent queue and one has left it\n";
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
		String aLine1 = "Create a situation in which similatenously there are 4 threads in the condition queue and a thread in the monitor that can notifyall\n";
		String aLine2 = "Release the monitor occupying thread\n";
		String aLine3 = "Look at the history\n";
		String aLine4 = "You should see that 4 threads entered the urgent queue and one has left it\n";
		return "\n"+  aLine1 + aLine2 + aLine3 + aLine4;
	}

}
