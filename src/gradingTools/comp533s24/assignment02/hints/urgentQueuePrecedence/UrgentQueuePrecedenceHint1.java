package gradingTools.comp533s24.assignment02.hints.urgentQueuePrecedence;

import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@MaxValue(5)
@Explanation("Relationship with condition queue and entry queue")
public class UrgentQueuePrecedenceHint1 extends AbstractHint{	
	@Override
	protected Class[] previousHints() {
		return noPreviousHints();
	}
	@Override
	protected String hint() {
		String aLine1 = "Create a situation in which similatenously there are 4 threads in the condition queue, four in the entry queue, and a thread in the o that can notifyall\n";
		String aLine2 = "Release the monitor occupying thread\n";
		String aLine3 = "Look at the queues\n";
		String aLine4 = "Release the first thread that enters the monitor from the urgent queue\n";
		String aLine5 = "The urgent queue should be empty and the entry queue unchanged \n";
		return "\n" + aLine1 + aLine2 + aLine3 + aLine4 + aLine5 ; 
	}
	
//	@Override
//	public TestCaseResult test(Project aProject, boolean autoGrade)
//			throws NotAutomatableException, NotGradableException {
//		try {
//			String aLine1 = "Create a situation in which similatenously there are 4 threads in the condition queue, four in the entry queue, and a thread in the o that can notifyall\n";
//			String aLine2 = "Release the monitor occupying thread\n";
//			String aLine3 = "Look at the queues\n";
//			String aLine4 = "Release the first thread that enters the monitor from the urgent queue\n";
//			String aLine5 = "The urgent queue should be empty and the entry queue unchanged \n";
//
//			System.out.println(aLine1 + aLine2 + aLine3 + aLine4 + aLine5 );
//			return pass ("Please see console output for hints");			
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return fail(e.getMessage());
//		}
//	}

	

}
