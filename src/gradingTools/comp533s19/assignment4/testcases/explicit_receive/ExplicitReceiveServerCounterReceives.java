package gradingTools.comp533s19.assignment4.testcases.explicit_receive;

import grader.basics.junit.JUnitTestsEnvironment;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.basics.testcase.JUnitTestCase;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterServerChecker;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterClientChecker;
import gradingTools.comp533s19.assignment1.testcases.SingleClassTagListTestCase;
import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.comp533s19.assignment4.testcases.SubstringSequenceChecker;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@MaxValue(10)
@Explanation("Checks for explicit receives in server counter output ")
public class ExplicitReceiveServerCounterReceives extends ExplicitReceiveClient1CounterRegularOutput {
	@Override
	protected SubstringSequenceChecker checker() {
		return new  AnExplicitReceiveServerReceivesChecker(); 
	}
	protected String processName() {
		return DistributedCounterProgramRunningTestCase.SERVER_NAME;
	}
	
	
	
	
}
