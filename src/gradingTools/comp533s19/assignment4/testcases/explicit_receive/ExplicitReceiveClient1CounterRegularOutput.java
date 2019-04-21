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
@MaxValue(5)
@Explanation("Checks for expected client1 counter output when explicit receive is implemented.")
public class ExplicitReceiveClient1CounterRegularOutput extends AStringCheckBasedDependentTestCase {

	
	protected SubstringSequenceChecker outputChecker() {
		return new  ARegularCounterClientChecker(); 
	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
	NotGradableException {
		return dependentTest(project, autoGrade);
	}
	protected String processName() {
		return DistributedCounterProgramRunningTestCase.CLIENT_1_NAME;
	}
	protected boolean checkTrue() {
		return true;
	}
	protected SubstringSequenceChecker checker() {
		return new ARegularCounterClientChecker();
	}
	protected Class outputgeneratingTestCaseClass() {
		return ExplicitReceiveServerCounterRegularOutput.class;
	}
	protected JUnitTestCase outputGeneratingTestCase() {
		return JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest( outputgeneratingTestCaseClass());
//		return null;
	}
//	public void defaultTest() {
//    	passfailDefaultTest(); // do not want super test's default test which we want to bypass
//    }
	
}
