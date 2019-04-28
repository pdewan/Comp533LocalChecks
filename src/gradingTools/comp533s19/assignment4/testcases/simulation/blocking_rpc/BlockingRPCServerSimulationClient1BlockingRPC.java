package gradingTools.comp533s19.assignment4.testcases.simulation.blocking_rpc;

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
import gradingTools.comp533s19.assignment4.testcases.blocking_rpc.ABlockingRPCClientReceivesChecker;
import gradingTools.comp533s19.assignment4.testcases.explicit_receive.ExplicitReceiveServerCounterRegularOutput;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@MaxValue(25)
@Explanation("Checks for dequeues in custom rpc server simulation output ")
public class BlockingRPCServerSimulationClient1BlockingRPC extends AStringCheckBasedDependentTestCase {

//	@Override
//	protected SubstringSequenceChecker outputChecker() {
//		return new  ABlockingRPCClientReceivesChecker(); 
//	}
//	protected SubstringSequenceChecker outputChecker() {
//		return new  ARegularCounterClientChecker(); 
//	}
//	@Override
//	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
//	NotGradableException {
//		return dependentTest(project, autoGrade);
//	}
	protected String processName() {
		return "Client_0"; // fix this!
	}
	protected boolean checkTrue() {
		return true;
	}
	protected SubstringSequenceChecker checker() {
		return new ABlockingRPCClientReceivesChecker();
	}
	protected Class outputgeneratingTestCaseClass() {
		return BlockingRPCServerSimulationRegularOutput.class;
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
