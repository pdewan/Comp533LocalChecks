package gradingTools.comp533s24.assignment02.hints.entryQueueNotFIFO;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.comp533s24.assignment02.hints.conditionQueueHas4.ConditionQueueHas4Hint1;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("How to create in-turn producers and consumers")
public class EntryQueueNotFIFOHint2 extends AbstractHint{
	static Class[] PREVIOUS_HINTS = {
			EntryQueueNotFIFOHint1.class	
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
//			String aLine1 = "Use the threads command to see which threads are ready\n";
//			String aLine2 = "Use the enter comand to make threads enter the entry queue\n";	
//			String aLine3 = "Use the queue comand to see the status of queues\n";		
//
//			System.out.println(aLine1 + aLine2 + aLine3);
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
		String aLine1 = "Put alternating consumers and producers in the entry queue using the enter commands\n";
		String aLine2 = "Make the first thread a producer\n";	
		String aLine3 = "Do not release the producer until the entry queue has been filled with 4 other alternating threads\n";		
		return "\n" + aLine1 + aLine2 + aLine3;

	}

}
