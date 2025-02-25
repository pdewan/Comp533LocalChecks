package gradingTools.comp533s24.assignment02.hints.entryQueueHas4;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("The concept of requests to enter being granted one at a time")
public class EntryQueueHas4Hint1 extends AbstractHint{
	
	

	
	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return noPreviousHints();
	}
//	@Override
//	public TestCaseResult test(Project aProject, boolean autoGrade)
//			throws NotAutomatableException, NotGradableException {
//		try {
//			String aLine1 = "Make 5 different ready threads enter the monitor entery queue\n";
//			String aLine2 = "Four of them should be in the entry queue\n";		
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
		String aLine1 = "Try make 5 different ready threads enter the monitor\n";
		String aLine2 = "Four of them should be in the entry queue\n";		
		return "\n" + aLine1 + aLine2;
	}

}
