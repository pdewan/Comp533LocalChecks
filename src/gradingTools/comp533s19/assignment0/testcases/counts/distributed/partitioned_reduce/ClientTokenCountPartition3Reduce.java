package gradingTools.comp533s19.assignment0.testcases.counts.distributed.partitioned_reduce;

import grader.basics.execution.BasicProjectExecution;
import grader.basics.execution.BasicRunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.DistributedTokenCountResult;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterClientChecker;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import port.old.PrintingReplyingObjectReceiver;
import util.annotations.MaxValue;
@MaxValue(30)
public class ClientTokenCountPartition3Reduce extends AStringCheckBasedDependentTestCase {


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
		return new AClientTokenCountPartition3ReduceChecker();
	}
	protected Class outputgeneratingTestCaseClass() {
		return DistributedTokenCountResult.class;
	}

	
}
