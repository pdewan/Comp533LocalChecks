package gradingTools.comp533s20.assignment1.testcases.sum.standalone.singlethread;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.comp533s19.assignment0.testcases.counts.standalone.MultiThreadTokenCountResult;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.MultiThreadSumResult;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.multithread.mvc.StandAloneMultiThreadSumMVC;
import gradingTools.comp533s19.assignment4.testcases.ARegularCounterClientChecker;
import gradingTools.comp533s19.assignment4.testcases.AStringCheckBasedDependentTestCase;
import gradingTools.comp533s20.assignment1.singleThread.StandAloneSingleThreadSumResult;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import util.annotations.Explanation;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@MaxValue(10)
//@IsExtra(true)
@Explanation("Checks that summation application follows the required MVC pattern")
public class StandAloneSingleThreadSumMVC extends StandAloneMultiThreadSumMVC {


	protected SubstringSequenceChecker checker() {
		return new AStandAloneSingleThreadSumMVCChecker();
	}
	protected Class outputgeneratingTestCaseClass() {
		return StandAloneSingleThreadSumResult.class;
	}

	
}
