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
@Explanation("Third Hint on Thread Assignment")
public class ThreadMappingHint3 extends AbstractHint{
	static Class[] PREVIOUS_HINTS = {
			ThreadMappingHint2.class	
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
//			String aLine1 = "Start the producer0 and consumer0 clients\n";
//			String aLine2 = "Make the main thread of the producer client process enter and leave monitor \n";
//			String aLine3 = "Do not hit return to ensure that this main thread does not immediately make a remote call \n";
//			String aLine4 = "Make the main thread of the consumer client prcoess enter and leave monitor \n";
//			String aLine5 = "Look at the mapping between client and server threads\n";
//			String aLine6 = "Enter return in the console of the producer0 process so it makes a new remote call \n";
//			String aLine7 = "Make the main thread of the producer client process enter and leave monitor \n";
//			String aLine8 = "Display the history of queue entries and exits, you should see a separation of 2 between entries of the main producer thread in the queue\n";
//			String aLine9 = "Display the thread mapping\n";
//			String aLine10 = "You will probably see that the server thread assigned first to the consumer main thread is now asigned to the producer main thread";
//			System.out.println (aLine1 + aLine2 + aLine3 + aLine4 + aLine5 + aLine6 + aLine7 + aLine8 + aLine9 + aLine10);			
//			return pass ("See console output");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return fail(e.getMessage());
//		}
//	}

	@Override
	protected String hint() {
		String aLine1 = "Start the producer0 and consumer0 clients\n";
		String aLine2 = "Make the main thread of the producer client process enter and leave monitor \n";
		String aLine3 = "Do not hit return to ensure that this main thread does not immediately make a remote call \n";
		String aLine4 = "Make the main thread of the consumer client prcoess enter and leave monitor \n";
		String aLine5 = "Look at the mapping between client and server threads\n";
		String aLine6 = "Enter return in the console of the producer0 process so it makes a new remote call \n";
		String aLine7 = "Make the main thread of the producer client process enter and leave monitor \n";
		String aLine8 = "Display the history of queue entries and exits, you should see a separation of 2 between entries of the main producer thread in the queue\n";
		String aLine9 = "Display the thread mapping\n";
		String aLine10 = "You will probably see that the server thread assigned first to the consumer main thread is now asigned to the producer main thread";
		return "\n" + aLine1 + aLine2 + aLine3 + aLine4 + aLine5 + aLine6 + aLine7 + aLine8 + aLine9 + aLine10;
	}

}
