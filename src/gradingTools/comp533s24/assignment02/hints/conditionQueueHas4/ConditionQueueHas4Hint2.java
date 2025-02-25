package gradingTools.comp533s24.assignment02.hints.conditionQueueHas4;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("Definition of in-turn and out of turn threads")
public class ConditionQueueHas4Hint2 extends AbstractHint{
	static Class[] PREVIOUS_HINTS = {
			ConditionQueueHas4Hint1.class	
			};

	
	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return PREVIOUS_HINTS;
	}
//	
//	@Override
//	public TestCaseResult test(Project aProject, boolean autoGrade)
//			throws NotAutomatableException, NotGradableException {
//		try {
//			String aLine1 = "An out of turn thread is a producer if the last value printed by the monitor was a consumption\n";
//			String aLine2 = "An out of turn thread is a consumer if the monitor has not yet printed a value or has printed a production\n";
//			System.out.println(aLine1 + aLine2);
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
		String aLine1 = "\nAn out of turn thread is a producer if the last value printed by the monitor was a consumption\n";
		String aLine2 = "An out of turn thread is a consumer if the monitor has not yet printed a value or has printed a production\n";
		return "\n" + aLine1 + aLine2;
	}

}
