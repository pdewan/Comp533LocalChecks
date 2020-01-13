package gradingTools.comp533s19.assignment0.testcases.sum.standalone.barrier;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.StandAloneMultiThreadTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.StandAloneMultiThreadSumResult;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterClientChecker;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.shared.testcases.SubstringSequenceChecker;
//@MaxValue(5)
//@Explanation("Checks for expected client1 counter output when explicit receive is implemented.")
public class StandAloneSumBarrier extends AStringCheckBasedDependentTestCase {

//	@Override
//	protected SubstringSequenceChecker outputChecker() {
//		return new  ARegularCounterClientChecker(); 
//	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
	NotGradableException {
		return dependentTest(project, autoGrade);
	}
//	protected String processName() {
//		return DistributedCounterProgramRunningTestCase.CLIENT_1_NAME;
//	}
//	protected boolean checkTrue() {
//		return true;
//	}
	protected SubstringSequenceChecker checker() {
		return new AStandAloneSumBarrierChecker();
	}
	protected Class outputgeneratingTestCaseClass() {
		return StandAloneMultiThreadSumResult.class;
	}
//	@Override
//	protected JUnitTestCase outputGeneratingTestCase() {
//		return JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest( outputgeneratingTestCaseClass());
////		return null;
//	}
//	public void defaultTest() {
//    	passfailDefaultTest(); // do not want super test's default test which we want to bypass
//    }
	
}
