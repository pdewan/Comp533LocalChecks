package gradingTools.comp533s21.assignment3.facebookMapReduce.tests;

import grader.basics.execution.BasicRunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment0.testcases.sum.distributed.quit.ADistributedCountQuitChecker;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.comp533s21.assignment3.facebookMapReduce.checkers.ADistributedFacebookMapReduceQuitChecker;
import gradingTools.shared.testcases.SubstringSequenceChecker;
//@MaxValue(5)
//@Explanation("Checks for expected client1 counter output when explicit receive is implemented.")
public class DistributedFacebookMapReduceQuit extends AStringCheckBasedDependentTestCase {

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
	NotGradableException {
		return dependentTest(project, autoGrade);
	}
	@Override
	protected String processName() {
		return BasicRunningProject.ALL_PROCESSES;
	}

	protected SubstringSequenceChecker checker() {
		return new ADistributedFacebookMapReduceQuitChecker();
	}
	protected Class outputgeneratingTestCaseClass() {
		return DistributedFacebookMapReduceResult.class;
	}
	
}
