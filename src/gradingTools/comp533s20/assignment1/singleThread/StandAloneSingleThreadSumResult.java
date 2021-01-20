package gradingTools.comp533s20.assignment1.singleThread;

import gradingTools.comp533s19.assignment0.testcases.sum.standalone.MultiThreadSumResult;
import gradingTools.comp533s19.assignment0.testcases.sum.standalone.SingleThreadSumResult;
import util.annotations.Explanation;
import util.annotations.IsExtra;
import util.annotations.MaxValue;
@Explanation("Checks that ints are correcetly input and summed")
//@IsExtra(true)
@MaxValue(10)
//public class StandAloneSingleThreadSumResult extends MultiThreadSumResult {
public class StandAloneSingleThreadSumResult extends SingleThreadSumResult {


	
	@Override 
	public String[] getInputLines() {
		return new String[] { 
				"1 2 3 4 5 6 7 8 9 0",
				"100 200 300 400 500 600 700 800 900 1000",
				"quit"
		};
	}
	

}
