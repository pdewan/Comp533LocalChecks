package gradingTools.comp533s24.assignment02.hints.urgentNotFIFO;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("The relationship between condition queue and urgent queue")
public class UrgentQueueNotFIFOHint1 extends AbstractHint{
	
	

	
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
		String aLine1 = "Make sure the urgent queue has 4 threads - see hints on how to do that if necessary\n";
		String aLine2 = "Execute release commands to see in what order they enter the monitor\n";	
		String aLine3 = "Use the history command to verify non FIFO behavior\n";
		return "\n" + aLine1 + aLine2 + aLine3;
	}

}
