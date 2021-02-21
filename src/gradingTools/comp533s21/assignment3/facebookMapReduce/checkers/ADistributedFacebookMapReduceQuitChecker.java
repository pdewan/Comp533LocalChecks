package gradingTools.comp533s21.assignment3.facebookMapReduce.checkers;

import gradingTools.comp533s19.assignment0.AMapReduceTracer;
import gradingTools.comp533s19.assignment0.testcases.counts.distributed.DistributedTokenCountResult;
import gradingTools.shared.testcases.ASubstringSequenceChecker;


public class ADistributedFacebookMapReduceQuitChecker extends ASubstringSequenceChecker{
	//make sure receives from both clients take place, need to ensure alternatibg, cannot with regular expressions

	String clientName;
	
	public ADistributedFacebookMapReduceQuitChecker() {
		init( makeMySubstrings());
	}
	String[]  makeMySubstrings() {
		final String[] MY_SUBSTRINGS = {
				".*" + DistributedTokenCountResult.MAP_REDUCE_CLIENT + ".*" + "main.*" + AMapReduceTracer.EXIT + ".*",
				".*" + DistributedTokenCountResult.MAP_REDUCE_CLIENT + ".*" + "main.*" + AMapReduceTracer.EXIT + ".*",	
		};
		return MY_SUBSTRINGS;
	}
}
