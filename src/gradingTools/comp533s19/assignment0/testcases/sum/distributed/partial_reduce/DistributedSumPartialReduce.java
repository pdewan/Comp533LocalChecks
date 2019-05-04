package gradingTools.comp533s19.assignment0.testcases.sum.distributed.partial_reduce;

import grader.basics.execution.BasicRunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.DistributedTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.DistributedSumResult;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterClientChecker;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.shared.testcases.SubstringSequenceChecker;
//@MaxValue(5)
//@Explanation("Checks for expected client1 counter output when explicit receive is implemented.")
public class DistributedSumPartialReduce extends AStringCheckBasedDependentTestCase {

//	@Override
//	protected SubstringSequenceChecker outputChecker() {
//		return new  ARegularCounterClientChecker(); 
//	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
	NotGradableException {
		return dependentTest(project, autoGrade);
	}
	@Override
	protected String processName() {
		 return BasicRunningProject.ALL_PROCESSES;
	}
//	protected boolean checkTrue() {
//		return true;
//	}
	protected SubstringSequenceChecker checker() {
		return new ADistributedSumPartialReducePartialReduceChecker(DistributedTokenCountResult.MAP_REDUCE_CLIENT_1, "2");
	}
	protected Class outputgeneratingTestCaseClass() {
		return DistributedSumResult.class;
	}
//	@Override
//	// we will not get any output for ALL
//	protected void dependentSetOutputError() {
//		
//	}
//	@Override
//	protected JUnitTestCase outputGeneratingTestCase() {
//		return JUnitTestsEnvironment.getAndPossiblyRunGradableJUnitTest( outputgeneratingTestCaseClass());
////		return null;
//	}
//	public void defaultTest() {
//    	passfailDefaultTest(); // do not want super test's default test which we want to bypass
//    }
	
}
