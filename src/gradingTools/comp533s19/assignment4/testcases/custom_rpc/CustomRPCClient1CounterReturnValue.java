package gradingTools.comp533s19.assignment4.testcases.custom_rpc;

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
import gradingTools.shared.testcases.SubstringSequenceChecker;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@MaxValue(10)
@Explanation("Checks for dequeues in custom rpc  client2 counter output ")
public class CustomRPCClient1CounterReturnValue extends CustomRPCClient1CounterRegularOutput {

	@Override
	protected SubstringSequenceChecker checker() {
		return new  ACustomRPCClientReceivesChecker(); 
	}
	
	
	
	
}
