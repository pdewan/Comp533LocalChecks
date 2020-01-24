package gradingTools.comp533s20.assignment1.testcases.counts.standalone.singlethread;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.MultiThreadTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.multithread.mvc.AStandAloneTokenCounterMVCChecker;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.multithread.mvc.StandAloneMultiThreadTokenCountMVC;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterClientChecker;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.comp533s20.assignment1.singleThread.StandAloneSingleThreadTokenCountResult;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@MaxValue(30)
@Explanation("Checks that token counting application follows the required MapReduce pattern")

public class StandAloneSingleThreadTokenCountMapReduce extends StandAloneMultiThreadTokenCountMVC {

	protected SubstringSequenceChecker checker() {
		return new AStandAloneSingleThreadTokenCounterMapReduceChecker();
	}
	protected Class outputgeneratingTestCaseClass() {
		return StandAloneSingleThreadTokenCountResult.class;
	}
	
}
