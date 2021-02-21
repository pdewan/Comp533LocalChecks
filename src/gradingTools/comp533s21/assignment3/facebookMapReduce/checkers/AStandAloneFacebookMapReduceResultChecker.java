package gradingTools.comp533s21.assignment3.facebookMapReduce.checkers;

import gradingTools.comp533s19.assignment4.testcases.DistributedCounterProgramRunningTestCase;
import gradingTools.shared.testcases.ASubstringSequenceChecker;
import util.trace.port.rpc.RemoteCallWaitingForReturnValue;

public class AStandAloneFacebookMapReduceResultChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions
	public final String[] MY_SUBSTRINGS = {
			//Might need to be toned down a bit
//			".*View.*Result.*["
//			+ "(a_c=\\[b, d\\][ }])"
//			+ "(a_b=\\[c, d\\][ }])"
//			+ "(b_d=\\[a, c, e\\][ }])"
//			+ "(a_d=\\[b, c\\][ }])"
//			+ "(b_c=\\[a, d, e\\][ }])"
//			+ "(c_e=\\[b, d\\][ }])"
//			+ "(c_d=\\[a, b, e\\][ }])"
//			+ "(b_e=\\[c, d\\][ }])"
//			+ "(d_e=\\[b, c\\][ }])"
//			+ "]{9}.*",
			".*View.*Result.*b_c=\\[a, d, e\\].*",
//			".*View.*Result.*a_x=\\[y\\].*",
			".*View.*Result.*Dumbledore_Ron=\\[Hagrid, Harry, Herminone\\].*"

	};
	public AStandAloneFacebookMapReduceResultChecker() {
		init( MY_SUBSTRINGS);
	}
	

}
