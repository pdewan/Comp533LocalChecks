package gradingTools.comp533s25.assignment02.hints.threadMapping;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.PassFailJUnitTestCase;
import gradingTools.shared.testcases.concurrency.oddNumbers.hints.AbstractHint;
import util.annotations.Explanation;
import util.annotations.MaxValue;

@Explanation("Try to make main threads get server threads in different orders while trying garnage collection")
public class ThreadMappingHint3 extends AbstractHint{
	static Class[] PREVIOUS_HINTS = {
			ThreadMappingHint2.class	
			};

	
	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return PREVIOUS_HINTS;
	}
	


	@Override
	protected String hint() {
		String aLine1 = "Hit returns in the producer and consumer processes in a certain order";
		String aLine2 = "Use the thread mapping command to what server threads their main threads have been assigned";
		String aLine3 = "Use the enter and release commands to ensure that these calls return after production and consumption by them";
		String aLine4 = "Use the thread mapping commands to see what happened to the threads assigned to them";
		String aLine5 = "Wait for a a minute or two for thread-garbage collection";
		String aLine6 = "Hit returns in the opposite order in the producer and consumer processes";
		String aLine7 = "Look at the thread mapping command to see if the main client threads have been assigned different server threads";		
		return "\n" + aLine1 + "\n" + aLine2 + "\n" + aLine3 + "\n" + aLine4 + "\n" + aLine5 + "\n" + aLine6 + "\n" + aLine7 + "\n";
	}

}
