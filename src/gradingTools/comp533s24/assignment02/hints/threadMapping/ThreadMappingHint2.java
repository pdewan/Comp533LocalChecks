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
@Explanation("try to make anothe thread take the main thread's server thread")
public class ThreadMappingHint2 extends AbstractHint{
	
	static Class[] PREVIOUS_HINTS = {
			ThreadMappingHint1.class	
			};

	
	@Override
	protected Class[] previousHints() {
		// TODO Auto-generated method stub
		return PREVIOUS_HINTS;
	}

	@Override
	protected String hint() {
		String aLine1 = "Try to put at least two non-main threads between two main threads in the entry queue\n";
		String aLine2 = "Use the thread mapping command to see if the second time a main thread makes a remote call, it is assigned a new server thread\n";
		return "\n" + aLine1 + aLine2;
	}

}
