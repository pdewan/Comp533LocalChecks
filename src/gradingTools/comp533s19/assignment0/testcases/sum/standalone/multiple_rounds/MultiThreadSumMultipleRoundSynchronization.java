package gradingTools.comp533s19.assignment0.testcases.sum.standalone.multiple_rounds;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.MultiThreadTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.MultiThreadSumResult;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterClientChecker;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.shared.testcases.SubstringSequenceChecker;
//@MaxValue(5)
//@Explanation("Checks for expected client1 counter output when explicit receive is implemented.")
public class MultiThreadSumMultipleRoundSynchronization extends AStringCheckBasedDependentTestCase {


	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
	NotGradableException {
		return dependentTest(project, autoGrade);
	}

	protected SubstringSequenceChecker checker() {
		return new AStandAloneSumMultipleRoundsSynchronizationChecker();
	}
	protected Class outputgeneratingTestCaseClass() {
		return MultiThreadSumResult.class;
	}

}
