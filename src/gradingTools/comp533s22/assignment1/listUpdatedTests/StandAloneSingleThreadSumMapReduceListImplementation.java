package gradingTools.comp533s22.assignment1.listUpdatedTests;

import gradingTools.comp533s19.assignment0.testcases.sum.standalone.multithread.mvc.StandAloneMultiThreadSumMVC;
import gradingTools.comp533s20.assignment1.singleThread.StandAloneSingleThreadSumResult;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@MaxValue(30)
@Explanation("Checks that summation application follows the required MapReduce pattern")
public class StandAloneSingleThreadSumMapReduceListImplementation extends StandAloneMultiThreadSumMVC {


	protected SubstringSequenceChecker checker() {
		return new AStandAloneSingleThreadSumMapReduceCheckerListImplementation();
	}
	protected Class outputgeneratingTestCaseClass() {
		return StandAloneSingleThreadSumResult.class;
	}
	
}
