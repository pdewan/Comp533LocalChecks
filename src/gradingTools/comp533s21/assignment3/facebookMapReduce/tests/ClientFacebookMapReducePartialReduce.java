package gradingTools.comp533s21.assignment3.facebookMapReduce.tests;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.DistributedTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.DistributedSumResult;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.partial_reduce.AClientSumPartialReduceChecker;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterClientChecker;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.comp533s21.assignment3.facebookMapReduce.checkers.AClientFacebookMapReducePartialReduceChecker;
import gradingTools.shared.testcases.SubstringSequenceChecker;
//@MaxValue(5)
//@Explanation("Checks for expected client1 counter output when explicit receive is implemented.")
public class ClientFacebookMapReducePartialReduce extends AStringCheckBasedDependentTestCase {

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
	NotGradableException {
		return dependentTest(project, autoGrade);
	}
	@Override
	protected String processName() {
		return DistributedTokenCountResult.MAP_REDUCE_CLIENT_2;
	}

	protected SubstringSequenceChecker checker() {
		return new AClientFacebookMapReducePartialReduceChecker();
	}
	protected Class outputgeneratingTestCaseClass() {
		return DistributedFacebookMapReduceResult.class;
	}

	
}
