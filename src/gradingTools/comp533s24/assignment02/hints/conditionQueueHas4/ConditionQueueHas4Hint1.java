package gradingTools.comp533s24.assignment02.hints.conditionQueueHas4;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("The idea of multiple out of turn threads")
public class ConditionQueueHas4Hint1 extends AbstractHint{
	
//	
//	@Override
//	public TestCaseResult test(Project aProject, boolean autoGrade)
//			throws NotAutomatableException, NotGradableException {
//		try {
//			String aLine1 = "Create a situation in which similatenously there are 4 out of turn threads in the entry queue and no thread whose turn it is\n";
//			String aLine2 = "If the monitor is occupied release it\n";
//			String aLine3 = "Look at the history\n";
//			String aLine4 = "You should see that 4 threads entered the condition queue\n";
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
	protected Class[] previousHints() {
		return noPreviousHints();
	}

	@Override
	protected String hint() {
		String aLine1 = "Try to make four out of turn threads enter the monitor";
		String aLine2 = "Make any monitor occupant leave the monitor using the release command";
		String aLine3 = "Look at the history";
		String aLine4 = "You should see that 4 threads entered the condition queue";
		return "\n" + aLine1 + "\n" + aLine2 + "\n" + aLine3 + "\n" + aLine4 + "\n";
	}

}
