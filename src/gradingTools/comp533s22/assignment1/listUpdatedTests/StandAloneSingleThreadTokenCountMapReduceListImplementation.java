package gradingTools.comp533s22.assignment1.listUpdatedTests;

import gradingTools.comp533s19.assignment0.testcases.counts.standalone.multithread.mvc.StandAloneMultiThreadTokenCountMVC;
import gradingTools.comp533s20.assignment1.singleThread.StandAloneSingleThreadTokenCountResult;
import gradingTools.shared.testcases.SubstringSequenceChecker;
import util.annotations.Explanation;
import util.annotations.MaxValue;
@MaxValue(30)
@Explanation("Checks that token counting application follows the required MapReduce pattern")
public class StandAloneSingleThreadTokenCountMapReduceListImplementation extends StandAloneMultiThreadTokenCountMVC {
	protected SubstringSequenceChecker checker() {
		return new AStandAloneSingleThreadTokenCounterMapReduceCheckerListImplementation();
	}
	protected Class outputgeneratingTestCaseClass() {
		return StandAloneSingleThreadTokenCountResult.class;
	}
}
